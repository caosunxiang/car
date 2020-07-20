package com.example.car.service.impl;

import com.example.car.common.utils.DateUtil;
import com.example.car.common.utils.json.Body;
import com.example.car.entity.DeviceAlarm;
import com.example.car.mapper.mysql.CarInfoMapper;
import com.example.car.mapper.mysql.DeviceAlarmMapper;
import com.example.car.service.ICarInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.util.Date;
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
@Transactional(transactionManager = "masterTransactionManager", propagation = Propagation.SUPPORTS, readOnly = true)
public class CarInfoServiceImpl implements ICarInfoService {

    @Autowired
    private CarInfoMapper carInfoMapper;

    @Autowired
    private DeviceAlarmMapper deviceAlarmMapper;


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
        if (!StringUtils.isEmpty(deptid)) {
            for (Map<String, Object> objectMap : list) {
                if (!StringUtils.isEmpty(objectMap.get("terminal_id"))){
                    Map<String,Object> deviceAlarm = deviceAlarmMapper.selectGpsAlarm(objectMap.get("terminal_id").toString());
                    if (deviceAlarm != null) {
                        if (!StringUtils.isEmpty(deviceAlarm.get("end_time"))){
                            Long time = DateUtil.dateDiff(deviceAlarm.get("end_time").toString(),DateUtil.getDateFormat(new Date(),
                                    DateUtil.FULL_TIME_SPLIT_PATTERN), DateUtil.FULL_TIME_SPLIT_PATTERN, "h");
                            objectMap.put("time", "距离上次离线"+time+"小时");
                        }
                    }else{
                        objectMap.put("time", "没有离线记录");
                    }
                }
            }
        }
        return Body.newInstance(list);
    }
}
