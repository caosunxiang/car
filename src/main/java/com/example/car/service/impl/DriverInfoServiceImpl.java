/**
 * Copyright (C), 2020-2020, 众马科技有限公司
 * FileName: DriverInfoServiceImpl
 * Author:   冷酷的苹果
 * Date:     2020/12/24 13:22
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.example.car.service.impl;

import com.example.car.common.utils.CutString;
import com.example.car.common.utils.DateUtil;
import com.example.car.common.utils.FileUploadUtils;
import com.example.car.common.utils.entity.DriverBean;
import com.example.car.common.utils.json.Body;
import com.example.car.entity.DriverHistorical;
import com.example.car.entity.DriverInfo;
import com.example.car.entity.M03;
import com.example.car.entity.OperationLog;
import com.example.car.mapper.sqlserver.DriverInfoMapper;
import com.example.car.mapper.sqlserver.M03Mapper;
import com.example.car.mapper.sqlserver.OperationLogMapper;
import com.example.car.service.DriverInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 冷酷的苹果
 * @create 2020/12/24
 * @since 1.0.0
 */
@Service
@RequiredArgsConstructor
@Transactional(transactionManager = "otherTransactionManager", propagation = Propagation.SUPPORTS, readOnly = true)
public class DriverInfoServiceImpl implements DriverInfoService {

    @Autowired
    private DriverInfoMapper driverInfoMapper;
    @Autowired
    private OperationLogMapper operationLogMapper;
    @Autowired
    private M03Mapper m03Mapper;


    @Override
    public Body selectDriverInfo(String driverId, String MustId, String name, String driverCardNo) {
        List<DriverInfo> driverInfos = driverInfoMapper.selectDriverInfo(driverId, MustId, name,driverCardNo);
        List<DriverBean>driverBeans = new ArrayList<>();
        for (int i = 0; i <driverInfos.size() ; i++) {
            List<String> list=new ArrayList<>();
            DriverBean driverBean=new DriverBean();
            driverBean.setDriver(driverInfos.get(i));
            for (DriverInfo driverInfo : driverInfos) {
                if (driverInfo.getDriverId().equals(driverInfos.get(i).getDriverId())) {
                    list.add(driverInfo.getCarNumber());
                }
            }
            driverBean.setCarnumber(list);
            driverBeans.add(driverBean);
        }
        return Body.newInstance(driverBeans);
    }

    @Override
    public Body insertDriver(DriverInfo driverInfo, String userid) {
        String url;
        String url1;
        if (!StringUtils.isEmpty(driverInfo.getLicenseUrl())) {
            MultipartFile file = FileUploadUtils.base64Convert(driverInfo.getLicenseUrl());
            url = FileUploadUtils.fileUpload(file, "img");
            driverInfo.setLicenseUrl(url);
        }
        if (!StringUtils.isEmpty(driverInfo.getDriverPhotoUrl())) {
            MultipartFile file1 = FileUploadUtils.base64Convert(driverInfo.getDriverPhotoUrl());
            url1 = FileUploadUtils.fileUpload(file1, "img");
            driverInfo.setLicenseUrl(url1);
        }
        driverInfoMapper.insertDriver(driverInfo);
        DriverHistorical driverHistorical = new DriverHistorical(null, driverInfo.getCarId(), driverInfo.getDriverId().toString(),
                DateUtil.getDateFormat(new Date(), DateUtil.FULL_TIME_SPLIT_PATTERN), null);
        driverInfoMapper.insertDriverHistorical(driverHistorical);
        OperationLog operationLog = new OperationLog(null, driverInfo.getCarId(), "添加", "添加驾驶员", DateUtil.getDateFormat(new Date(),
                DateUtil.FULL_TIME_SPLIT_PATTERN), userid, null, null);
        operationLogMapper.insertLog(operationLog);
        return Body.newInstance(driverInfo);
    }

    @Override
    public Body updateDriver(DriverInfo driverInfo, String userid) {
        String url;
        String url1;
        if (!StringUtils.isEmpty(driverInfo.getLicenseUrl())) {
            MultipartFile file = FileUploadUtils.base64Convert(driverInfo.getLicenseUrl());
            url = FileUploadUtils.fileUpload(file, "img");
            driverInfo.setLicenseUrl(url);
        }
        if (!StringUtils.isEmpty(driverInfo.getDriverPhotoUrl())) {
            MultipartFile file1 = FileUploadUtils.base64Convert(driverInfo.getDriverPhotoUrl());
            url1 = FileUploadUtils.fileUpload(file1, "img");
            driverInfo.setDriverPhotoUrl(url1);
        }
        if (!StringUtils.isEmpty(driverInfo.getDriverCardNo1())) {
            MultipartFile file1 = FileUploadUtils.base64Convert(driverInfo.getDriverCardNo1());
            driverInfo.setDriverCardNo1( FileUploadUtils.fileUpload(file1, "img"));
        }
        if (!StringUtils.isEmpty(driverInfo.getDriverCardNo2())) {
            MultipartFile file1 = FileUploadUtils.base64Convert(driverInfo.getDriverCardNo2());
            driverInfo.setDriverCardNo2(FileUploadUtils.fileUpload(file1, "img"));
        }
        driverInfoMapper.updateDriver(driverInfo);
        OperationLog operationLog = new OperationLog(null, driverInfo.getCarId(), "修改", "修改驾驶员信息", DateUtil.getDateFormat(new Date(),
                DateUtil.FULL_TIME_SPLIT_PATTERN), userid, null, null);
        operationLogMapper.insertLog(operationLog);
        return Body.newInstance(driverInfo);
    }

    @Override
    public Body selectDriverHistorical(String carId) {
        List<DriverInfo> driverInfos = driverInfoMapper.selectDriverHistorical(carId);
        return Body.newInstance(driverInfos);
    }

    @Override
    public Body insertDrivers(DriverInfo driverInfo, String carNumber, String userid) {
        String url;
        if (!StringUtils.isEmpty(driverInfo.getLicenseUrl())) {
            MultipartFile file = FileUploadUtils.base64Convert(driverInfo.getLicenseUrl());
            url = FileUploadUtils.fileUpload(file, "img");
            driverInfo.setLicenseUrl(url);
        }
        List<String> list = CutString.divide(carNumber);
        for (String s : list) {
            if (driverInfoMapper.selectDriverInfo(null,null,s, driverInfo.getDriverCardNo()).size()>0){
                return Body.newInstance(201,"车辆已经有驾驶员了");
            }
            List<M03>m03s=m03Mapper.selectM03(s, null, null, null);
            if (m03s.size() > 0) {
                driverInfo.setCarId(m03s.get(0).getRecId());
                driverInfoMapper.insertDriver(driverInfo);
                DriverHistorical driverHistorical = new DriverHistorical(null,m03s.get(0).getRecId(), driverInfo.getDriverId().toString(),
                        DateUtil.getDateFormat(new Date(), DateUtil.FULL_TIME_SPLIT_PATTERN), null);
                driverInfoMapper.insertDriverHistorical(driverHistorical);
                OperationLog operationLog = new OperationLog(null,
                        m03s.get(0).getRecId(), "添加", "添加驾驶员",
                        DateUtil.getDateFormat(new Date(),
                                DateUtil.FULL_TIME_SPLIT_PATTERN), userid, null, null);
                operationLogMapper.insertLog(operationLog);
            }else {
                return Body.newInstance(201,"没有找到这辆车");
            }
        }
        return Body.BODY_200;
    }

    @Override
    public Body delectDriver(Integer driverId) {
        driverInfoMapper.delectDriver(driverId);
        driverInfoMapper.delectDriverHistorical(driverId);
        return Body.BODY_200;
    }

    @Override
    public Body synDriver() {
        List<M03> m03s = m03Mapper.selectM03(null, null, null, null);
        for (M03 m03 : m03s) {
            DriverInfo driverInfo = new DriverInfo(null, m03.getM0308(), m03.getM0310(), m03.getM0309(),
                    m03.getM0334(), "0", null, null, null, null,
                    m03.getRecId(), m03.getMustId(), null, null,null,null, null,null);
            driverInfoMapper.insertDriver(driverInfo);
            DriverHistorical driverHistorical=new DriverHistorical(null,m03.getRecId(),driverInfo.getDriverId().toString(),null,null);
            driverInfoMapper.insertDriverHistorical(driverHistorical);
        }
        return Body.BODY_200;
    }

    @Override
    public Body selectDriverCount(String driverStatus, String deptid) {
        return Body.newInstance(driverInfoMapper.selectDriverCount(driverStatus,deptid));
    }
}

