/**
 * Copyright (C), 2020-2020, 众马科技有限公司
 * FileName: OperationLogController
 * Author:   冷酷的苹果
 * Date:     2020/12/22 13:29
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.example.car.controller;

import com.example.car.common.utils.json.Body;
import com.example.car.service.OperationLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
public class OperationLogController {

    @Autowired
    private OperationLogService operationLogService;

    /**
     *
     *
    * @ Description: 查看操作日志
    * @ Param: [carid, userid, id]
    * @ return: com.example.car.common.utils.json.Body
    * @ Author: 冷酷的苹果
    * @ Date: 2020/12/22 13:33
    */
    @RequestMapping("selectLog")
    public Body selectLog(String carid, String userid, Integer id) {
        return operationLogService.selectLog(carid, userid, id);
    }
}
