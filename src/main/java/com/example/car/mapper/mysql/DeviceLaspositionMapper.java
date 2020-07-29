package com.example.car.mapper.mysql;

import com.example.car.entity.DeviceLasposition;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;

/**
* 设备最后位置表 Mapper
*
* @author 冷酷的苹果
* @date 2020-07-29 10:31:06
*/
public interface DeviceLaspositionMapper {
   List<DeviceLasposition> selectLasposition(@Param("pid")String pid);

   DeviceLasposition selectLaspositionByCarNo(@Param("number")String number);
}
