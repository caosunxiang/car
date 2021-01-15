/**
 * Copyright (C), 2020-2021, 众马科技有限公司
 * FileName: TerminalInfoController
 * Author:   冷酷的苹果
 * Date:     2021/1/5 10:09
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.example.car.controller;

import com.example.car.common.utils.json.Body;
import com.example.car.service.TerminalInfoService;
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
 * @create 2021/1/5
 * @since 1.0.0
 */
@Slf4j
@Validated
@RestController
@CrossOrigin
@RequiredArgsConstructor
public class TerminalInfoController {

    private final TerminalInfoService terminalInfoService;


    /**
     * @ Description: 同步数据
     * @ Param: []
     * @ return: com.example.car.common.utils.json.Body
     * @ Author: 冷酷的苹果
     * @ Date: 2021/1/5 10:15
     */
    @RequestMapping("insertTerminalInfo")
    public Body insertTerminalInfo() {
        return terminalInfoService.insertTerminalInfo();
    }


    /**
     * @ Description: 查询设备
     * @ Param: [deptid, carId, carNumber]
     * @ return: com.example.car.common.utils.json.Body
     * @ Author: 冷酷的苹果
     * @ Date: 2021/1/5 14:20
     */
    @RequestMapping("selectTerminal")
    public Body selectTerminal(String deptid, String carId, String carNumber) {
        return terminalInfoService.selectTerminal(deptid, carId, carNumber);
    }


    /**
     * @ Description: 添加设备信息
     * @ Param: [terminal, terminalType, createUser, carId, deptid, installstatus, installtime, carNumber]
     * @ return: com.example.car.common.utils.json.Body
     * @ Author: 冷酷的苹果
     * @ Date: 2021/1/5 14:22
     */
    @RequestMapping("insertTerminal")
    public Body insertTerminalInfo(String terminal, String terminalType, String createUser, String carId,
                                   String deptid, String installstatus, String installtime, String carNumber) {
        if (StringUtils.isEmpty(terminal) || StringUtils.isEmpty(createUser) || StringUtils.isEmpty(deptid)) {
            return Body.BODY_451;
        }
        return terminalInfoService.insertTerminalInfo(terminal, terminalType, createUser, carId, deptid,
                installstatus, installtime, carNumber);
    }

    /**
     * @ Description: 修改设备信息
     * @ Param: [modifyUser, isDelete, terminal, terminalType, carNumber, terminalId]
     * @ return: com.example.car.common.utils.json.Body
     * @ Author: 冷酷的苹果
     * @ Date: 2021/1/5 18:21
     */
    @RequestMapping("updateTerminal")
    public Body updateTerminal(String modifyUser, String isDelete, String terminal, String terminalType,
                               String carNumber, Integer terminalId) {
        if (StringUtils.isEmpty(terminalId) || StringUtils.isEmpty(modifyUser)) {
            return Body.BODY_451;
        }
        return terminalInfoService.updateTerminal(modifyUser, isDelete, terminal, terminalType, carNumber, terminalId);
    }

    /**
     * @ Description: 删除设备
     * @ Param: [terminalId]
     * @ return: com.example.car.common.utils.json.Body
     * @ Author: 冷酷的苹果
     * @ Date: 2021/1/11 10:08
     */
    @RequestMapping("deleteTerminal")
    public Body deleteTerminal(Integer terminalId) {
        if (StringUtils.isEmpty(terminalId)) {
            return Body.BODY_451;
        }
        return terminalInfoService.deleteTerminal(terminalId);
    }
}
