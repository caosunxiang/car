/**
 * Copyright (C), 2020-2020, 众马科技有限公司
 * FileName: DeviceAlarmSeverityImpl
 * Author:   冷酷的苹果
 * Date:     2020/7/1 15:57
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.example.car.service.impl;

import com.example.car.common.utils.json.Body;
import com.example.car.entity.DeviceAlarmSeverity;
import com.example.car.mapper.mysql.DeviceAlarmSeverityMapper;
import com.example.car.service.DeviceAlarmSeverityService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author 冷酷的苹果
 * @create 2020/7/1
 * @since 1.0.0
 */
@Service
@RequiredArgsConstructor
@Transactional(transactionManager = "masterTransactionManager",propagation = Propagation.SUPPORTS, readOnly = true)
public class DeviceAlarmSeverityImpl implements DeviceAlarmSeverityService {

    @Autowired
    private DeviceAlarmSeverityMapper deviceAlarmSeverityMapper;

    @Override
    public Body selectAlarmSeverity(String startTime,String number, String endTime,String name,Integer size) {
        if (StringUtils.isEmpty(size)){
            size=500;
        }
        List<Map<String, Object>> deviceAlarmSeverities=deviceAlarmSeverityMapper.selectAlarmSeverity(startTime,number,endTime,name,size);
        return Body.newInstance(deviceAlarmSeverities);
    }
}
