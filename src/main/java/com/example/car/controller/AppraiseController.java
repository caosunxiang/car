/**
 * Copyright (C), 2020-2021, 众马科技有限公司
 * FileName: AppraiseController
 * Author:   冷酷的苹果
 * Date:     2021/1/12 11:28
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.example.car.controller;

import com.example.car.common.utils.json.Body;
import com.example.car.entity.Appraise;
import com.example.car.service.AppraiseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 冷酷的苹果
 * @create 2021/1/12
 * @since 1.0.0
 */
@Slf4j
@Validated
@RestController
@CrossOrigin
@RequiredArgsConstructor
public class AppraiseController {

    private final AppraiseService appraiseService;

    /** 
    * @ Description: 查找评价
    * @ Param: [deptid, appraiseLevel, id]
    * @ return: com.example.car.common.utils.json.Body
    * @ Author: 冷酷的苹果
    * @ Date: 2021/1/12 13:06
    */
    @RequestMapping("selectAppraise")
    public Body selectAppraise(String deptid, String appraiseLevel, Integer id) {
        return appraiseService.selectAppraise(deptid, appraiseLevel, id);
    }

    /** 
    * @ Description: 添加评价
    * @ Param: [appraise]
    * @ return: com.example.car.common.utils.json.Body
    * @ Author: 冷酷的苹果
    * @ Date: 2021/1/12 13:06
    */
    @RequestMapping("insertAppraise")
    public Body insertAppraise(Appraise appraise) {
        return appraiseService.insertAppraise(appraise);
    }

    /** 
    * @ Description: 修改评价
    * @ Param: [appraise]
    * @ return: com.example.car.common.utils.json.Body
    * @ Author: 冷酷的苹果
    * @ Date: 2021/1/12 13:06
    */
    @RequestMapping("updateAppraise")
    public Body updateAppraise(Appraise appraise) {
        return appraiseService.updateAppraise(appraise);
    }

    /** 
    * @ Description: 删除评价
    * @ Param: [id]
    * @ return: com.example.car.common.utils.json.Body
    * @ Author: 冷酷的苹果
    * @ Date: 2021/1/12 13:06
    */
    @RequestMapping("deleteAppraise")
    public  Body deleteAppraise(Integer id) {
        return appraiseService.deleteAppraise(id);
    }
}
