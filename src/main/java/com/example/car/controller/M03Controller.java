/**
 * Copyright (C), 2020-2020, 众马科技有限公司
 * FileName: M03Controller
 * Author:   冷酷的苹果
 * Date:     2020/12/22 18:14
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.example.car.controller;

import com.example.car.common.utils.json.Body;
import com.example.car.entity.M03;
import com.example.car.entity.TerminalInfo;
import com.example.car.service.M03Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 冷酷的苹果
 * @create 2020/12/22
 * @since 1.0.0
 */
@Slf4j
@Validated
@RestController
@CrossOrigin
@RequiredArgsConstructor
public class M03Controller {

    @Autowired
    private M03Service m03Service;

    /**
     * @ Description: 查找车辆信息
     * @ Param: [carNumber, recId, phone]
     * @ return: com.example.car.common.utils.json.Body
     * @ Author: 冷酷的苹果
     * @ Date: 2020/12/23 14:13
     */
    @RequestMapping("selectM03")
    public Body selectM03(String carNumber, String recId, String phone, String MustId,String type)  {
        if (StringUtils.isEmpty(type)){
            return Body.BODY_451;
        }
        return m03Service.selectM03(carNumber, recId, phone, MustId,type);
    }

    /**
     * @ Description: 修改车辆信息
     * @ Param: [person, quality, dimensions, scrapTime, IssuanceDate, totalQuality, checkQuality, tractionQuality,
     * stopTransport, stopNumber, stopEndTime, RecId, userid]
     * @ return: com.example.car.common.utils.json.Body
     * @ Author: 冷酷的苹果
     * @ Date: 2020/12/23 14:14
     */
    @RequestMapping("updateM03")
    public Body updateM03(M03 m03, String userid) {
        return m03Service.updateM03(m03, userid);
    }

    /**
     * @ Description:停运
     * @ Param: [stopTransport, stopNumber, stopEndTime, recId]
     * @ return: com.example.car.common.utils.json.Body
     * @ Author: 冷酷的苹果
     * @ Date: 2020/12/28 10:42
     */
    @RequestMapping("updateStopTransport")
    public Body updateStopTransport(String stopTransport, Integer stopNumber, String recId, String stopRemark,
                                    String MustId, String userid) {
        if (StringUtils.isEmpty(MustId) && StringUtils.isEmpty(recId)) {
            return Body.BODY_451;
        }
        return m03Service.updateStopTransport(stopTransport, stopNumber, recId, stopRemark, MustId, userid);

    }

    /**
     * @ Description: 查询车辆状态
     * @ Param: [MustId]
     * @ return: com.example.car.common.utils.json.Body
     * @ Author: 冷酷的苹果
     * @ Date: 2021/1/7 9:44
     */
    @RequestMapping("selectM03Status")
    public Body selectM03Status(String MustId, String name,String type){
        if (StringUtils.isEmpty(type)){
            return Body.BODY_451;
        }
        return m03Service.selectM03Status(MustId, name ,type);
    }

    /**
     * @ Description: 添加车辆信息
     * @ Param: [m03, terminalInfo]
     * @ return: com.example.car.common.utils.json.Body
     * @ Author: 冷酷的苹果
     * @ Date: 2021/1/11 9:54
     */
    @RequestMapping("insertM03")
    public Body insertM03(M03 m03, TerminalInfo terminalInfo) {
        if (StringUtils.isEmpty(terminalInfo.getTerminal())) {
            return Body.BODY_451;
        }
        return this.m03Service.insertM03(m03, terminalInfo);
    }

    /**
     * @ Description: 删除车辆信息
     * @ Param: [RecId]
     * @ return: com.example.car.common.utils.json.Body
     * @ Author: 冷酷的苹果
     * @ Date: 2021/1/19 13:56
     */
    @RequestMapping("deleteM03")
    public Body deleteM03(String RecId) {
        if (StringUtils.isEmpty(RecId)) {
            return Body.BODY_451;
        }
        return this.m03Service.deleteM03(RecId);
    }

    /**
     * @ Description: 查找不同状态车辆的条数
     * @ Param: [auditStatus, MustId]
     * @ return: com.example.car.common.utils.json.Body
     * @ Author: 冷酷的苹果
     * @ Date: 2021/1/27 18:16
     */
    @RequestMapping("selectM03Count")
    public Body selectM03Count(String auditStatus, String MustId) {
        return this.m03Service.selectM03Count(auditStatus, MustId);
    }
}
