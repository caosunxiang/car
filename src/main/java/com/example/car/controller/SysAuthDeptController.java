package com.example.car.controller;

import com.example.car.common.utils.json.Body;
import com.example.car.service.ISysAuthDeptService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 公司机构表 Controller
 *
 * @author 冷酷的苹果
 * @date 2020-06-17 10:45:17
 */
@Slf4j
@Validated
@RestController
@CrossOrigin
@RequiredArgsConstructor
public class SysAuthDeptController {

    private final ISysAuthDeptService sysAuthDeptService;

    @RequestMapping("selectSysAuthDept")
    public Body selectSysAuthDept(String name) {
        return this.sysAuthDeptService.selectSysAuthDept(name);
    }
}
