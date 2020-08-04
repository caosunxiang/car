package com.example.car.service.impl;

import com.example.car.common.utils.json.Body;
import com.example.car.entity.SysAuthDept;
import com.example.car.mapper.mysql.DeviceAlarmMapper;
import com.example.car.mapper.mysql.SysAuthDeptMapper;
import com.example.car.service.IDeviceAlarmService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 设备报警表 Service实现
 *
 * @author 冷酷的苹果
 * @date 2020-06-18 10:52:07
 */
@Service
@RequiredArgsConstructor
@Transactional(transactionManager = "masterTransactionManager",propagation = Propagation.SUPPORTS, readOnly = true)
public class DeviceAlarmServiceImpl implements IDeviceAlarmService {

    private final DeviceAlarmMapper deviceAlarmMapper;

    private  final SysAuthDeptMapper sysAuthDeptMapper;


    @Override
    public Body selectAlarm(String number,String startTime,String endTime,Integer size,Integer type) {
        List<Map<String, Object>> listMain=new ArrayList<>();
        if (StringUtils.isEmpty(size)){
            size=500;
        }
        Long id=new Long("722445496500748288");
        List<SysAuthDept> deptList=sysAuthDeptMapper.selectSysAuthDeptByParent(id);
        for (SysAuthDept sysAuthDept : deptList) {
            if (!StringUtils.isEmpty(type)&&type==2){
                List<Map<String, Object>> list1 = this.deviceAlarmMapper.selectAlarm(number,startTime,endTime,sysAuthDept.getDeptid(),size,2);
                List<Map<String, Object>> list2 = this.deviceAlarmMapper.selectAlarm(number,startTime,endTime,sysAuthDept.getDeptid(),size,14);
                listMain.addAll(list1);
                listMain.addAll(list2);

            }else {
                List<Map<String, Object>> list = this.deviceAlarmMapper.selectAlarm(number,startTime,endTime,sysAuthDept.getDeptid(),size,type);
                listMain.addAll(list);
            }
        }

        return Body.newInstance(listMain);
    }

    @Override
    public Body selectAlarmOther(String number, String startTime, String endTime, Integer size) {
        List<Map<String, Object>> listMain=new ArrayList<>();
        if (StringUtils.isEmpty(size)){
            size=500;
        }
        Long id=new Long("722445496500748288");
        List<SysAuthDept> deptList=sysAuthDeptMapper.selectSysAuthDeptByParent(id);
        for (SysAuthDept sysAuthDept : deptList) {
            List<Map<String, Object>> list = this.deviceAlarmMapper.selectAlarmOther(number,startTime,endTime,sysAuthDept.getDeptid(),size);
            listMain.addAll(list);
        }
        return  Body.newInstance(listMain);
    }
}
