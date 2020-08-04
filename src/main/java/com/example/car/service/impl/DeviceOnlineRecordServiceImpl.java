package com.example.car.service.impl;

import com.example.car.common.utils.json.Body;
import com.example.car.entity.DeviceOnlineRecord;
import com.example.car.mapper.mysql.DeviceOnlineRecordMapper;
import com.example.car.service.IDeviceOnlineRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* 设备上下线表 Service实现
*
* @author 冷酷的苹果
* @date 2020-07-21 16:52:53
*/
@Service
@RequiredArgsConstructor
@Transactional(transactionManager = "masterTransactionManager",propagation = Propagation.SUPPORTS, readOnly = true)
public class DeviceOnlineRecordServiceImpl implements IDeviceOnlineRecordService {

private final DeviceOnlineRecordMapper deviceOnlineRecordMapper;


    @Override
    public Body selectOnlineRecord(String number, Integer time) {
        List<DeviceOnlineRecord> deviceOnlineRecords=deviceOnlineRecordMapper.selectOnlineRecord(number,time);
        return Body.newInstance(deviceOnlineRecords);
    }
}
