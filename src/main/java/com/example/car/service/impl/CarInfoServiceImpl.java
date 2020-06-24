package com.example.car.service.impl;

import com.example.car.common.utils.json.Body;
import com.example.car.mapper.mysql.CarInfoMapper;
import com.example.car.service.ICarInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 车辆信息表 Service实现
 *
 * @author 冷酷的苹果
 * @date 2020-06-18 08:54:02
 */
@Service
@RequiredArgsConstructor
@Transactional(transactionManager = "masterTransactionManager",propagation = Propagation.SUPPORTS, readOnly = true)
public class CarInfoServiceImpl implements ICarInfoService {

    @Autowired
    private  CarInfoMapper carInfoMapper;


    @Override
    public Body selectCar(String carnumber, String terminalId, String sim) {
        List<Map<String, Object>> list = this.carInfoMapper.selectCar(carnumber, terminalId, sim);
        return Body.newInstance(list);
    }

    @Override
    public Body selectCarByDeptName(String deptname) {
        List<Map<String, Object>> list = this.carInfoMapper.selectCarByDeptName(deptname);
        return Body.newInstance(list);
    }

    @Override
    public Body selectCarDetail(String deptid, String carnumber, String terminalid, String status) {
        List<Map<String, Object>> list = this.carInfoMapper.selectCarDetail(deptid, carnumber, terminalid, status);
        return Body.newInstance(list);
    }
}
