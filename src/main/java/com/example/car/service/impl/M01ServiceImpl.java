/**
 * Copyright (C), 2020-2020, 众马科技有限公司
 * FileName: M01ServiceImpl
 * Author:   冷酷的苹果
 * Date:     2020/12/30 16:23
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.example.car.service.impl;

import com.example.car.common.utils.DateUtil;
import com.example.car.common.utils.FileUploadUtils;
import com.example.car.common.utils.Md5Util;
import com.example.car.common.utils.json.Body;
import com.example.car.entity.M01;
import com.example.car.entity.M03;
import com.example.car.mapper.mysql.DeviceLaspositionMapper;
import com.example.car.mapper.sqlserver.*;
import com.example.car.service.M01Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 冷酷的苹果
 * @create 2020/12/30
 * @since 1.0.0
 */
@Service
@RequiredArgsConstructor
@Transactional(transactionManager = "otherTransactionManager", propagation = Propagation.SUPPORTS, readOnly = true)
public class M01ServiceImpl implements M01Service {

    private final M01Mapper m01Mapper;

    private final MuckMapper muckMapper;

    private final AppraiseMapper appraiseMapper;

    private final M03Mapper m03Mapper;

    private final DriverInfoMapper driverInfoMapper;

    private final CarInsuranceMapper carInsuranceMapper;


    @Override
    public Body selectM01Details(String MustId, String name, String phone) {
        List<M01> m01s = m01Mapper.selectM01Details(MustId, name, phone);
        for (M01 m01 : m01s) {
            m01.setScore(appraiseMapper.selectAppraiseAMG(m01.getMustId()));
            if (!StringUtils.isEmpty(m01.getDateEstablishment())) {
                m01.setDateEstablishment(DateUtil.changeTime(m01.getDateEstablishment(), "yyyy年MM月dd日"));
            }
            if (!StringUtils.isEmpty(m01.getM0108())) {
                m01.setM0108(DateUtil.changeTime(m01.getM0108(), "yyyy年MM月dd日"));
            }
            if (!StringUtils.isEmpty(m01.getM0109())) {
                System.out.println(m01);
                m01.setM0109(DateUtil.changeTime(m01.getM0109(), "yyyy年MM月dd日"));
            }
        }
        return Body.newInstance(m01s);
    }

    @Override
    public Body updateM01(String Status, String ShortSpell, String M0109, String M0104, String M0108, String M0105,
                          String M0107, String M0106,
                          String M0102, String M0103, String M0101, String representative, String registeredCapital,
                          String dateEstablishment, String address, String MustId, String QRCode,
                          String businessScope, String license) {
        String url = null;
        if (!StringUtils.isEmpty(license)) {
            MultipartFile file = FileUploadUtils.base64Convert(license);
            url = FileUploadUtils.fileUpload(file, "img");
        }
        M01 m01 = new M01(null, null, M0101, M0102, M0103, M0104, M0105, M0106, M0107, M0108, M0109, MustId,
                ShortSpell, QRCode, Status, representative, registeredCapital, dateEstablishment, address, null,
                businessScope, url, null);
        m01Mapper.updateM01(m01);
        return Body.BODY_200;
    }

    @Override
    public Body insertM01(String Creator, String M0101, String M0102, String M0103, String M0104, String M0105,
                          String M0106, String M0107, String M0108, String M0109, String ShortSpell) {
        Integer count = muckMapper.selectUserName(ShortSpell);
        if (count > 0) {
            return Body.newInstance(201, "简称已被注册");
        } else {
            M01 m01 = new M01(Creator, null, M0101, M0102, M0103, M0104, M0105, M0106, M0107, M0108, M0109,
                    UUID.randomUUID().toString(),
                    ShortSpell, null, null, null, null, null, null, null, null, null, null);
            m01Mapper.insertM01(m01);
            Integer id = muckMapper.selectUserId();
            muckMapper.insertUser(++id, ShortSpell, Md5Util.MD5EncodeUtf8("123456"), M0101);
            muckMapper.insertUserRole(id);
            return Body.BODY_200;
        }

    }

    @Override
    public Body uploadQRCode(String files, String Status, String MustId) {
        MultipartFile file = FileUploadUtils.base64Convert(files);
        String url = FileUploadUtils.fileUpload(file, "img");
        M01 m01 = new M01();
        m01.setQRCode(url);
        m01.setMustId(MustId);
        m01.setStatus(Status);
        this.m01Mapper.updateM01(m01);
        return Body.BODY_200;
    }

    @Override
    public Body M01Check(String MustId,String Status) {
        Integer m03BackLog=m03Mapper.selectM03Count("2",MustId);
        Integer driverBackLog=driverInfoMapper.selectDriverCount("2",MustId);
        Integer insuranceBackLog=carInsuranceMapper.selectInsuranceCount("2",MustId);
        if (m03BackLog==0&&driverBackLog==0&&insuranceBackLog==0){
            M01 m01=new M01();
            m01.setMustId(MustId);
            m01.setStatus(Status);
            m01.setM0108(DateUtil.getDateFormat(new Date(),DateUtil.FULL_TIME_SPLIT_PATTERN));
            m01.setM0109(DateUtil.severalDaysAgo(DateUtil.FULL_TIME_SPLIT_PATTERN,-365));
            m01Mapper.updateM01(m01);
            M03 m03=new M03();
            m03.setM0329(DateUtil.getDateFormat(new Date(),DateUtil.FULL_TIME_SPLIT_PATTERN));
            m03.setMustId(MustId);
            m03Mapper.updateM0329(m03);
            return Body.BODY_200;
        }else {
            return Body.newInstance(201,"还有未处理的审核申请,本次无法提交");
        }
    }

}
