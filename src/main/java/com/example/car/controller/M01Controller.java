/**
 * Copyright (C), 2020-2020, 众马科技有限公司
 * FileName: M01Controller
 * Author:   冷酷的苹果
 * Date:     2020/12/30 16:46
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.example.car.controller;

import com.example.car.common.utils.json.Body;
import com.example.car.service.M01Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 冷酷的苹果
 * @create 2020/12/30
 * @since 1.0.0
 */
@Slf4j
@Validated
@RestController
@CrossOrigin
@RequiredArgsConstructor
public class M01Controller {

    private final M01Service m01Service;

    /**
     * @ Description: 条件查询公司详情
     * @ Param: [MustId, name, phone]
     * @ return: com.example.car.common.utils.json.Body
     * @ Author: 冷酷的苹果
     * @ Date: 2020/12/30 17:22
     */
    @RequestMapping("selectM01Details")
    public Body selectM01Details(String MustId, String name, String phone) {
        return m01Service.selectM01Details(MustId, name, phone);
    }

    /**
     * @ Description: 修改公司详情
     * @ Param: [Status, ShortSpell, M0109, M0104, M0108, M0105, M0107, M0106, M0102, M0103, M0101, representative,
     * registeredCapital, dateEstablishment, address, MustId]
     * @ return: com.example.car.common.utils.json.Body
     * @ Author: 冷酷的苹果
     * @ Date: 2020/12/30 17:22
     */
    @RequestMapping("updateM01")
    public Body updateM01(String Status, String ShortSpell, String M0109, String M0104, String M0108, String M0105,
                          String M0107, String M0106, String M0102, String M0103, String M0101, String representative,
                          String registeredCapital, String dateEstablishment, String address, String MustId,
                          String QRCode,String represenPhone) {
        if (StringUtils.isEmpty(Status)) {
            return Body.newInstance(201, "Status字段不能为空");
        }
        return m01Service.updateM01(Status, ShortSpell, M0109, M0104, M0108, M0105, M0107, M0106, M0102, M0103, M0101
                , representative, registeredCapital, dateEstablishment, address, MustId, QRCode,represenPhone);
    }

    
    /** 
    * @ Description: 添加公司详情
    * @ Param: [Creator, M0101, M0102, M0103, M0104, M0105, M0106, M0107, M0108, M0109, ShortSpell]
    * @ return: com.example.car.common.utils.json.Body
    * @ Author: 冷酷的苹果
    * @ Date: 2020/12/31 10:07
    */
    @RequestMapping("insertM01")
    public Body insertM01(String Creator, String M0101, String M0102, String M0103, String M0104, String M0105,
                          String M0106, String M0107, String M0108, String M0109, String ShortSpell) {
        return m01Service.insertM01(Creator, M0101, M0102, M0103, M0104, M0105, M0106, M0107, M0108, M0109, ShortSpell);
    }

    /** 
    * @ Description: 上传二维码
    * @ Param: [files, Status, MustId]
    * @ return: com.example.car.common.utils.json.Body
    * @ Author: 冷酷的苹果
    * @ Date: 2021/1/19 14:41
    */
    @RequestMapping("uploadQRCode")
    public Body uploadQRCode(String files, String Status, String MustId) {
        if (StringUtils.isEmpty(files)){
            return Body.BODY_451;
        }
        return  this.m01Service.uploadQRCode(files, Status, MustId);
    }
}
