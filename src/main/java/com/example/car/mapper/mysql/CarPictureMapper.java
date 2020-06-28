/**
 * Copyright (C), 2020-2020, 众马科技有限公司
 * FileName: CarTypeMapper
 * Author:   冷酷的苹果
 * Date:     2020/6/28 15:59
 * Description: 车辆照片
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.example.car.mapper.mysql;

import io.lettuce.core.dynamic.annotation.Param;

/**
 * 〈一句话功能简述〉<br> 
 * 〈车辆照片〉
 *
 * @author 冷酷的苹果
 * @create 2020/6/28
 * @since 1.0.0
 */
public interface CarPictureMapper {
   String selectCarPicture(@Param("type")String type);
}
