package com.example.car.service;

import com.example.car.common.utils.json.Body;

/**
 * 公司机构表 Service接口
 *
 * @author 冷酷的苹果
 * @date 2020-06-17 10:45:17
 */
public interface ISysAuthDeptService {
    /**
     * @Description: 机构名称模糊查询
     * @Param: [name]
     * @return: com.example.car.common.utils.json.Body
     * @Author: 冷酷的苹果
     * @Date: 2020/6/17 14:00
     */
    Body selectSysAuthDept(String name);
}
