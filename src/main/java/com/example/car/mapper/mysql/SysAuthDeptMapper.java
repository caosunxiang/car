package com.example.car.mapper.mysql;

import com.example.car.entity.SysAuthDept;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;
import java.util.Map;

/**
 * 公司机构表 Mapper
 *
 * @author 冷酷的苹果
 * @date 2020-06-17 10:45:17
 */
public interface SysAuthDeptMapper {

    List<Map<String, Object>> selectSysAuthDept(@Param("name") String name,@Param("number")String number);
}
