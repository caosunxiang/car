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

import com.example.car.common.utils.FileUploadUtils;
import com.example.car.common.utils.Md5Util;
import com.example.car.common.utils.json.Body;
import com.example.car.entity.M01;
import com.example.car.mapper.mysql.DeviceLaspositionMapper;
import com.example.car.mapper.sqlserver.M01Mapper;
import com.example.car.mapper.sqlserver.MuckMapper;
import com.example.car.mapper.sqlserver.TerminalInfoMapper;
import com.example.car.service.M01Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

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


    @Override
    public Body selectM01Details(String MustId, String name, String phone) {
        List<M01> m01s = m01Mapper.selectM01Details(MustId, name, phone);
        return Body.newInstance(m01s);
    }

    @Override
    public Body updateM01(String Status, String ShortSpell, String M0109, String M0104, String M0108, String M0105,
                          String M0107, String M0106,
                          String M0102, String M0103, String M0101, String representative, String registeredCapital,
                          String dateEstablishment, String address, String MustId,String QRCode,String represenPhone) {
        M01 m01 = new M01(null, null, M0101, M0102, M0103, M0104, M0105, M0106, M0107, M0108, M0109, MustId,
                ShortSpell,QRCode, Status, representative, registeredCapital, dateEstablishment, address, represenPhone,null);
        m01Mapper.updateM01(m01);
        return Body.BODY_200;
    }

    @Override
    public Body insertM01(String Creator, String M0101, String M0102, String M0103, String M0104, String M0105,
                          String M0106, String M0107, String M0108, String M0109, String ShortSpell) {
        M01 m01 = new M01(Creator, null, M0101, M0102, M0103, M0104, M0105, M0106, M0107, M0108, M0109, UUID.randomUUID().toString(),
                ShortSpell, null, null, null, null, null,null,null,null);
        m01Mapper.insertM01(m01);
        Integer id=muckMapper.selectUserId();
        muckMapper.insertUser(++id,ShortSpell, Md5Util.MD5EncodeUtf8("123456"));
        muckMapper.insertUserRole(++id);
        return Body.BODY_200;
    }

    @Override
    public Body uploadQRCode(String files, String Status, String MustId) {
        MultipartFile file = FileUploadUtils.base64Convert(files);
        String url = FileUploadUtils.fileUpload(file, "img");
        M01 m01=new M01();
        m01.setQRCode(url);
        m01.setMustId(MustId);
        m01.setStatus(Status);
        this.m01Mapper.updateM01(m01);
        return  Body.BODY_200;
    }

}
