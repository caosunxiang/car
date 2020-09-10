package com.example.car.service.impl;

import com.example.car.common.utils.DateUtil;
import com.example.car.common.utils.json.Body;
import com.example.car.entity.CarInfo;
import com.example.car.entity.SysAuthDept;
import com.example.car.mapper.mysql.CarInfoMapper;
import com.example.car.mapper.mysql.CarStatusChangeRecordMapper;
import com.example.car.mapper.mysql.DeviceOnlineRecordMapper;
import com.example.car.mapper.mysql.SysAuthDeptMapper;
import com.example.car.service.ICarInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
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
    private DeviceOnlineRecordMapper deviceOnlineRecordMapper;

    @Autowired
    private CarStatusChangeRecordMapper carStatusChangeRecordMapper;

    @Autowired
    private SysAuthDeptMapper sysAuthDeptMapper;

    @Override
    public Body selectCar(String carnumber, String terminalId, String sim) {
        List<Map<String, Object>> list=new ArrayList<>();
        List<SysAuthDept> deptList = sysAuthDeptMapper.selectSysAuthDeptByParent(new Long("722445496500748288"));
        for (SysAuthDept sysAuthDept : deptList) {
             list.addAll(this.carInfoMapper.selectCar(carnumber, terminalId, sim,sysAuthDept.getDeptid()));
        }
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
                if (!StringUtils.isEmpty(status) && status.equals("A")) {
                    //车辆上线时间
                    List<Map<String, Object>> maps = deviceOnlineRecordMapper.selectDeviceOnlineRecord(objectMap.get(
                            "terminal_id").toString(), 1, 1);
                    if (maps.size() > 0) {
                        for (Map<String, Object> map : maps) {
                            String time = DateUtil.dateDiff(map.get("create_date").toString(),
                                    DateUtil.getDateFormat(new Date(),
                                            DateUtil.FULL_TIME_SPLIT_PATTERN), DateUtil.FULL_TIME_SPLIT_PATTERN, "h");
                            objectMap.put("time", time);
                            objectMap.put("startTime", map.get("create_date"));
                        }
                    }
                } else if (!StringUtils.isEmpty(status) && status.equals("X")) {
                    //车辆下线时间
                    List<Map<String, Object>> maps = deviceOnlineRecordMapper.selectDeviceOnlineRecord(objectMap.get(
                            "terminal_id").toString(), 2, 1);
                    if (maps.size() > 0) {
                        for (Map<String, Object> map : maps) {
                            String time = DateUtil.dateDiff(map.get("create_date").toString(),
                                    DateUtil.getDateFormat(new Date(),
                                            DateUtil.FULL_TIME_SPLIT_PATTERN), DateUtil.FULL_TIME_SPLIT_PATTERN, "h");
                            objectMap.put("time", time);
                            objectMap.put("startTime", map.get("create_date"));
                        }
                    }
                } else if (!StringUtils.isEmpty(status) && status.equals("C")) {
                    //车辆报备时间
                    List<Map<String, Object>> maps =
                            carStatusChangeRecordMapper.selectCarStatusChangeRecord(objectMap.get(
                                    "terminal_id").toString(), 1);
                    if (maps.size() > 0) {
                        for (Map<String, Object> map : maps) {
                            String time = DateUtil.dateDiff(map.get("modify_date").toString(),
                                    DateUtil.getDateFormat(new Date(),
                                            DateUtil.FULL_TIME_SPLIT_PATTERN), DateUtil.FULL_TIME_SPLIT_PATTERN, "h");
                            objectMap.put("time", time);
                            objectMap.put("startTime", map.get("modify_date"));
                        }
                    }
                } else {
                    if (objectMap.get("car_service_status").equals(0) && (!objectMap.get("carstatus").equals(1) && !objectMap.get("carstatus").equals(2))) {
                        //车辆上线时间
                        List<Map<String, Object>> mapsUp =
                                deviceOnlineRecordMapper.selectDeviceOnlineRecord(objectMap.get(
                                        "terminal_id").toString(), 1, 1);
                        if (mapsUp.size() > 0) {
                            for (Map<String, Object> map : mapsUp) {
                                String time = DateUtil.dateDiff(map.get("create_date").toString(),
                                        DateUtil.getDateFormat(new Date(),
                                                DateUtil.FULL_TIME_SPLIT_PATTERN), DateUtil.FULL_TIME_SPLIT_PATTERN,
                                        "h");
                                objectMap.put("time", time);
                                objectMap.put("startTime", map.get("create_date"));
                            }
                        } else {
                            CarInfo carInfo = carInfoMapper.selectCarOnly(objectMap.get("carnumber").toString());
                            objectMap.put("time", DateUtil.dateDiff(carInfo.getCreateDate(),
                                    DateUtil.getDateFormat(new Date(),
                                            DateUtil.FULL_TIME_SPLIT_PATTERN), DateUtil.FULL_TIME_SPLIT_PATTERN,
                                    "h"));
                            objectMap.put("startTime", carInfo.getCreateDate());
                        }
                    } else if (objectMap.get("car_service_status").equals(0) && (objectMap.get("carstatus").equals(1) || objectMap.get("carstatus").equals(2))) {
                        //车辆下线时间
                        List<Map<String, Object>> mapsDown =
                                deviceOnlineRecordMapper.selectDeviceOnlineRecord(objectMap.get(
                                        "terminal_id").toString(), 2, 1);
                        if (mapsDown.size() > 0) {
                            for (Map<String, Object> map : mapsDown) {
                                String time = DateUtil.dateDiff(map.get("create_date").toString(),
                                        DateUtil.getDateFormat(new Date(),
                                                DateUtil.FULL_TIME_SPLIT_PATTERN), DateUtil.FULL_TIME_SPLIT_PATTERN,
                                        "h");
                                objectMap.put("time", time);
                                objectMap.put("startTime", map.get("create_date"));
                            }
                        } else {
                            CarInfo carInfo = carInfoMapper.selectCarOnly(objectMap.get("carnumber").toString());
                            objectMap.put("time", DateUtil.dateDiff(carInfo.getCreateDate(),
                                    DateUtil.getDateFormat(new Date(),
                                            DateUtil.FULL_TIME_SPLIT_PATTERN), DateUtil.FULL_TIME_SPLIT_PATTERN,
                                    "h"));
                            objectMap.put("startTime", carInfo.getCreateDate());
                        }
                    } else {
                        //车辆报备时间
                        List<Map<String, Object>> mapsC =
                                carStatusChangeRecordMapper.selectCarStatusChangeRecord(objectMap.get(
                                        "terminal_id").toString(), 1);
                        if (mapsC.size() > 0) {
                            for (Map<String, Object> map : mapsC) {
                                String time = DateUtil.dateDiff(map.get("modify_date").toString(),
                                        DateUtil.getDateFormat(new Date(),
                                                DateUtil.FULL_TIME_SPLIT_PATTERN), DateUtil.FULL_TIME_SPLIT_PATTERN,
                                        "h");
                                objectMap.put("time", time);
                                objectMap.put("startTime", map.get("modify_date"));
                            }
                        }else {
                            CarInfo carInfo = carInfoMapper.selectCarOnly(objectMap.get("carnumber").toString());
                            objectMap.put("time", DateUtil.dateDiff(carInfo.getCreateDate(),
                                    DateUtil.getDateFormat(new Date(),
                                            DateUtil.FULL_TIME_SPLIT_PATTERN), DateUtil.FULL_TIME_SPLIT_PATTERN,
                                    "h"));
                            objectMap.put("startTime", carInfo.getCreateDate());
                        }
                    }


                }
            }
        }
        return Body.newInstance(list);
    }

    @Override
    public Body selectCarDetailPage(String deptid, String carnumber, String terminalid, String status, Integer index,
                                    Integer size) {
        List<Map<String, Object>> list = this.carInfoMapper.selectCarDetailPage(deptid, carnumber, terminalid, status
                , (index - 1) * size, size);
        System.out.println(list.toString());
        if (!StringUtils.isEmpty(deptid)) {
            for (Map<String, Object> objectMap : list) {
                if (!StringUtils.isEmpty(status) && status.equals("A")) {
                    //车辆上线时间
                    List<Map<String, Object>> maps = deviceOnlineRecordMapper.selectDeviceOnlineRecord(objectMap.get(
                            "terminal_id").toString(), 1, 1);
                    if (maps.size() > 0) {
                        for (Map<String, Object> map : maps) {
                            String time = DateUtil.dateDiff(map.get("create_date").toString(),
                                    DateUtil.getDateFormat(new Date(),
                                            DateUtil.FULL_TIME_SPLIT_PATTERN), DateUtil.FULL_TIME_SPLIT_PATTERN, "h");
                            objectMap.put("time", time);
                            objectMap.put("startTime", map.get("create_date"));
                        }
                    } else {
                        CarInfo carInfo = carInfoMapper.selectCarOnly(objectMap.get("carnumber").toString());
                        objectMap.put("time", DateUtil.dateDiff(carInfo.getCreateDate(),
                                DateUtil.getDateFormat(new Date(),
                                        DateUtil.FULL_TIME_SPLIT_PATTERN), DateUtil.FULL_TIME_SPLIT_PATTERN,
                                "h"));
                        objectMap.put("startTime", carInfo.getCreateDate());
                    }
                } else if (!StringUtils.isEmpty(status) && status.equals("X")) {
                    //车辆下线时间
                    List<Map<String, Object>> maps = deviceOnlineRecordMapper.selectDeviceOnlineRecord(objectMap.get(
                            "terminal_id").toString(), 2, 1);
                    if (maps.size() > 0) {
                        for (Map<String, Object> map : maps) {
                            String time = DateUtil.dateDiff(map.get("create_date").toString(),
                                    DateUtil.getDateFormat(new Date(),
                                            DateUtil.FULL_TIME_SPLIT_PATTERN), DateUtil.FULL_TIME_SPLIT_PATTERN, "h");
                            objectMap.put("time", time);
                            objectMap.put("startTime", map.get("create_date"));
                        }
                    } else {
                        CarInfo carInfo = carInfoMapper.selectCarOnly(objectMap.get("carnumber").toString());
                        objectMap.put("time", DateUtil.dateDiff(carInfo.getCreateDate(),
                                DateUtil.getDateFormat(new Date(),
                                        DateUtil.FULL_TIME_SPLIT_PATTERN), DateUtil.FULL_TIME_SPLIT_PATTERN,
                                "h"));
                        objectMap.put("startTime", carInfo.getCreateDate());
                    }
                } else if (!StringUtils.isEmpty(status) && status.equals("C")) {
                    //车辆报备时间
                    List<Map<String, Object>> maps =
                            carStatusChangeRecordMapper.selectCarStatusChangeRecord(objectMap.get(
                                    "terminal_id").toString(), 1);
                    if (maps.size() > 0) {
                        for (Map<String, Object> map : maps) {
                            String time = DateUtil.dateDiff(map.get("modify_date").toString(),
                                    DateUtil.getDateFormat(new Date(),
                                            DateUtil.FULL_TIME_SPLIT_PATTERN), DateUtil.FULL_TIME_SPLIT_PATTERN, "h");
                            objectMap.put("time", time);
                            objectMap.put("startTime", map.get("modify_date"));
                        }
                    } else {
                        CarInfo carInfo = carInfoMapper.selectCarOnly(objectMap.get("carnumber").toString());
                        objectMap.put("time", DateUtil.dateDiff(carInfo.getCreateDate(),
                                DateUtil.getDateFormat(new Date(),
                                        DateUtil.FULL_TIME_SPLIT_PATTERN), DateUtil.FULL_TIME_SPLIT_PATTERN,
                                "h"));
                        objectMap.put("startTime", carInfo.getCreateDate());
                    }
                } else {
                    if (objectMap.get("car_service_status").equals(0) && (!objectMap.get("carstatus").equals(1) && !objectMap.get("carstatus").equals(2))) {
                        //车辆上线时间
                        List<Map<String, Object>> mapsUp =
                                deviceOnlineRecordMapper.selectDeviceOnlineRecord(objectMap.get(
                                        "terminal_id").toString(), 1, 1);
                        if (mapsUp.size() > 0) {
                            for (Map<String, Object> map : mapsUp) {
                                String time = DateUtil.dateDiff(map.get("create_date").toString(),
                                        DateUtil.getDateFormat(new Date(),
                                                DateUtil.FULL_TIME_SPLIT_PATTERN), DateUtil.FULL_TIME_SPLIT_PATTERN,
                                        "h");
                                objectMap.put("time", time);
                                objectMap.put("startTime", map.get("create_date"));
                            }
                        } else {
                            CarInfo carInfo = carInfoMapper.selectCarOnly(objectMap.get("carnumber").toString());
                            objectMap.put("time", DateUtil.dateDiff(carInfo.getCreateDate(),
                                    DateUtil.getDateFormat(new Date(),
                                            DateUtil.FULL_TIME_SPLIT_PATTERN), DateUtil.FULL_TIME_SPLIT_PATTERN,
                                    "h"));
                            objectMap.put("startTime", carInfo.getCreateDate());
                        }
                    } else if (objectMap.get("car_service_status").equals(0) && (objectMap.get("carstatus").equals(1) || objectMap.get("carstatus").equals(2))) {
                        //车辆下线时间
                        List<Map<String, Object>> mapsDown =
                                deviceOnlineRecordMapper.selectDeviceOnlineRecord(objectMap.get(
                                        "terminal_id").toString(), 2, 1);
                        if (mapsDown.size() > 0) {
                            for (Map<String, Object> map : mapsDown) {
                                String time = DateUtil.dateDiff(map.get("create_date").toString(),
                                        DateUtil.getDateFormat(new Date(),
                                                DateUtil.FULL_TIME_SPLIT_PATTERN), DateUtil.FULL_TIME_SPLIT_PATTERN,
                                        "h");
                                objectMap.put("time", time);
                                objectMap.put("startTime", map.get("create_date"));
                            }
                        } else {
                            CarInfo carInfo = carInfoMapper.selectCarOnly(objectMap.get("carnumber").toString());
                            objectMap.put("time", DateUtil.dateDiff(carInfo.getCreateDate(),
                                    DateUtil.getDateFormat(new Date(),
                                            DateUtil.FULL_TIME_SPLIT_PATTERN), DateUtil.FULL_TIME_SPLIT_PATTERN,
                                    "h"));
                            objectMap.put("startTime", carInfo.getCreateDate());
                        }
                    } else {
                        //车辆报备时间
                        List<Map<String, Object>> mapsC =
                                carStatusChangeRecordMapper.selectCarStatusChangeRecord(objectMap.get(
                                        "terminal_id").toString(), 1);
                        if (mapsC.size() > 0) {
                            for (Map<String, Object> map : mapsC) {
                                String time = DateUtil.dateDiff(map.get("modify_date").toString(),
                                        DateUtil.getDateFormat(new Date(),
                                                DateUtil.FULL_TIME_SPLIT_PATTERN), DateUtil.FULL_TIME_SPLIT_PATTERN,
                                        "h");
                                objectMap.put("time", time);
                                objectMap.put("startTime", map.get("modify_date"));
                            }
                        } else {
                            CarInfo carInfo = carInfoMapper.selectCarOnly(objectMap.get("carnumber").toString());
                            objectMap.put("time", DateUtil.dateDiff(carInfo.getCreateDate(),
                                    DateUtil.getDateFormat(new Date(),
                                            DateUtil.FULL_TIME_SPLIT_PATTERN), DateUtil.FULL_TIME_SPLIT_PATTERN,
                                    "h"));
                            objectMap.put("startTime", carInfo.getCreateDate());
                        }
                    }
                }
            }
        }
        return Body.newInstance(list);
    }
}
