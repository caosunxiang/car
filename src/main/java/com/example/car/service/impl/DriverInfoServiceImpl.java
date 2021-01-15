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
import com.example.car.common.utils.json.Body;
import com.example.car.entity.DriverHistorical;
import com.example.car.entity.DriverInfo;
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
    public Body selectDriverInfo(String driverId, String MustId, String name) {
        List<DriverInfo> driverInfos = driverInfoMapper.selectDriverInfo(driverId, MustId, name);
        return Body.newInstance(driverInfos);
    }

    @Override
    public Body insertDriver(String driverName, String driverMobile, String driverCardNo, String driverReviewTime,
                             String driverStatus, String driverFile, String driverSex, String driverAddress,
                             String files, String carId, String userid,String deptid) {
        String url = null;
        String url1 = null;
        if (!StringUtils.isEmpty(files)) {
            MultipartFile file = FileUploadUtils.base64Convert(files);
            url = FileUploadUtils.fileUpload(file, "img");
        }
        if (!StringUtils.isEmpty(driverFile)) {
            MultipartFile file1 = FileUploadUtils.base64Convert(driverFile);
            url1 = FileUploadUtils.fileUpload(file1, "img");
        }
        DriverInfo driverInfo = new DriverInfo(null, driverName, driverMobile, driverCardNo, driverReviewTime,
                driverStatus, url1, driverSex, driverAddress, url,carId,deptid, null, null, null);
        driverInfoMapper.insertDriver(driverInfo);
        DriverHistorical driverHistorical = new DriverHistorical(null, carId, driverInfo.getDriverId().toString(),
                DateUtil.getDateFormat(new Date(), DateUtil.FULL_TIME_SPLIT_PATTERN), null);
        driverInfoMapper.insertDriverHistorical(driverHistorical);
        OperationLog operationLog = new OperationLog(null, carId, "添加", "添加驾驶员", DateUtil.getDateFormat(new Date(),
                DateUtil.FULL_TIME_SPLIT_PATTERN), userid, null, null);
        operationLogMapper.insertLog(operationLog);
        return Body.newInstance(driverInfo);
    }

    @Override
    public Body updateDriver(Integer driverId,String driverName,String driverMobile,String driverCardNo,String driverReviewTime,String driverStatus,
                             String driverFile,String driverSex,String driverAddress,String files,String carId,String userid,String deptid) {
        String url = null;
        String url1 = null;
        if (!StringUtils.isEmpty(files)) {
            MultipartFile file = FileUploadUtils.base64Convert(files);
            url = FileUploadUtils.fileUpload(file, "img");
        }
        if (!StringUtils.isEmpty(driverFile)) {
            MultipartFile file1 = FileUploadUtils.base64Convert(driverFile);
            url1 = FileUploadUtils.fileUpload(file1, "img");
        }
        DriverInfo driverInfo = new DriverInfo(driverId, driverName, driverMobile, driverCardNo, driverReviewTime,
                driverStatus, url1, driverSex, driverAddress, url,carId, deptid, null, null, null);
        driverInfoMapper.updateDriver(driverInfo);
        OperationLog operationLog = new OperationLog(null, carId, "修改", "修改驾驶员信息", DateUtil.getDateFormat(new Date(),
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
    public Body insertDrivers(String driverName, String driverMobile, String driverCardNo, String driverReviewTime,
                              String driverStatus, String driverFile, String driverSex, String driverAddress,
                              String carNumber, String userid,String deptid) {
        String url = null;
        if (!StringUtils.isEmpty(driverFile)) {
            MultipartFile file = FileUploadUtils.base64Convert(driverFile);
            url = FileUploadUtils.fileUpload(file, "img");
        }
        List<String> list = CutString.divide(carNumber);
        for (String s : list) {
            if (m03Mapper.selectM03(s, null, null, null).size() > 0) {
                DriverInfo driverInfo = new DriverInfo(null, driverName, driverMobile, driverCardNo, driverReviewTime,
                        driverStatus, null, driverSex, driverAddress, url,m03Mapper.selectM03(s, null, null,
                        null).get(0).getRecId(),deptid, null, null, null);
                driverInfoMapper.insertDriver(driverInfo);
                DriverHistorical driverHistorical = new DriverHistorical(null, m03Mapper.selectM03(s, null, null,
                        null).get(0).getRecId(), driverInfo.getDriverId().toString(),
                        DateUtil.getDateFormat(new Date(), DateUtil.FULL_TIME_SPLIT_PATTERN), null);
                driverInfoMapper.insertDriverHistorical(driverHistorical);
                OperationLog operationLog = new OperationLog(null,
                        m03Mapper.selectM03(s, null, null, null).get(0).getRecId(), "添加", "添加驾驶员",
                        DateUtil.getDateFormat(new Date(),
                                DateUtil.FULL_TIME_SPLIT_PATTERN), userid, null, null);
                operationLogMapper.insertLog(operationLog);
            }
        }
        return Body.BODY_200;
    }

    @Override
    public Body delectDriver(Integer driverId) {
        driverInfoMapper.delectDriver(driverId);
        return Body.BODY_200;
    }
}

