package com.example.car.service.impl;

import com.example.car.common.utils.json.Body;
import com.example.car.entity.CarStatusChangeRecord;
import com.example.car.mapper.mysql.CarStatusChangeRecordMapper;
import com.example.car.service.ICarStatusChangeRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 车辆报备登记 Service实现
 *
 * @author 冷酷的苹果
 * @date 2020-07-21 17:28:17
 */
@Service
@RequiredArgsConstructor
@Transactional(transactionManager = "masterTransactionManager",propagation = Propagation.SUPPORTS, readOnly = true)
public class CarStatusChangeRecordServiceImpl implements ICarStatusChangeRecordService {

    private final CarStatusChangeRecordMapper carStatusChangeRecordMapper;


    @Override
    public Body selectCarStatusRecord(String number, Integer time) {
        List<CarStatusChangeRecord> carStatusChangeRecords = carStatusChangeRecordMapper.selectCarStatusRecord(number
                , time);
        return Body.newInstance(carStatusChangeRecords);
    }
}
