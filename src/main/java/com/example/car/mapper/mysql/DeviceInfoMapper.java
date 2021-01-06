/**
 * Copyright (C), 2020-2021, 众马科技有限公司
 * FileName: DeviceInfoMapper
 * Author:   冷酷的苹果
 * Date:     2021/1/4 16:32
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.example.car.mapper.mysql;

import com.example.car.entity.DeviceInfo;

import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author 冷酷的苹果
 * @create 2021/1/4
 * @since 1.0.0
 */
public interface DeviceInfoMapper {
   List<DeviceInfo> selectDeviceInfo();

}
