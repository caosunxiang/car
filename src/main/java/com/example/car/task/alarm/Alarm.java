///**
// * Copyright (C), 2020-2020, 众马科技有限公司
// * FileName: Task
// * Author:   冷酷的苹果
// * Date:     2020/7/3 13:59
// * Description: 定时任务
// * History:
// * <author>          <time>          <version>          <desc>
// * 作者姓名           修改时间           版本号              描述
// */
//package com.example.car.task.alarm;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.example.car.common.utils.*;
//import com.example.car.common.utils.entity.EChatBean3;
//import com.example.car.entity.*;
//import com.example.car.mapper.mysql.*;
//import com.example.car.mapper.sqlserver.M03Mapper;
//import com.example.car.mapper.sqlserver.MuckMapper;
//import com.example.car.mapper.sqlserver.OperationLogMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Configurable;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//
//import java.io.IOException;
//import java.text.SimpleDateFormat;
//import java.util.*;
//
///**
// * 〈一句话功能简述〉<br>
// * 〈定时任务〉
// *
// * @author 冷酷的苹果
// * @create 2020/7/3
// * @since 1.0.0
// */
//@Component                //实例化
//@Configurable             //注入bean
//@EnableScheduling         //开启计划任务
//public class Alarm {
//
//    @Autowired
//    private SysAuthDeptMapper sysAuthDeptMapper;
//
//    @Autowired
//    private MuckMapper muckMapper;
//
//    @Autowired
//    private DeviceAlarmSeverityMapper deviceAlarmSeverityMapper;
//
//    @Autowired
//    private M03Mapper m03Mapper;
//
//    @Autowired
//    private OperationLogMapper operationLogMapper;
//
//    @Autowired
//    private DeviceLaspositionMapper deviceLaspositionMapper;
//
//    @Autowired
//    private CarTargetMapper carTargetMapper;
//
//    @Autowired
//    private DeviceAlarmMapper deviceAlarmMapper;
//
//    @Autowired
//    private DeviceOnlineRecordMapper deviceOnlineRecordMapper;
//
//
//    @Scheduled(cron = " * 0/5 * * * ? ")//无证运输存数据库
//    public void noMuckIn() throws IOException {
//        System.out.println("开始查询无证运输");
//        List<CarTarget> carTargets = carTargetMapper.selectCarTarget();
//        for (CarTarget carTarget : carTargets) {
//            DeviceLasposition deviceLasposition =
//                    deviceLaspositionMapper.selectLaspositionByCarNo(carTarget.getCarNumber());
//            DeviceAlarmSeverity deviceAlarmSeverity = deviceAlarmSeverityMapper.selectAlarmSeverityTask(null,
//                    carTarget.getCarNumber(), null, "无准运证行驶", "A");
//            if (Double.parseDouble(deviceLasposition.getSpeed()) > 35.00) {//分行驶和停止两个情况
//                if (StringUtils.isEmpty(deviceAlarmSeverity)) {//无准运证情况并且没有无证运输的当天记录
//                    DeviceAlarmSeverity deviceAlarmSeverity1 = new DeviceAlarmSeverity();
//                    deviceAlarmSeverity1.setAlarmLng(deviceLasposition.getLng().toString());
//                    deviceAlarmSeverity1.setAlarmLat(deviceLasposition.getLat().toString());
//                    deviceAlarmSeverity1.setAlarmName("无准运证行驶");
//                    deviceAlarmSeverity1.setAlarmStartSpeed(deviceLasposition.getSpeed());
//                    deviceAlarmSeverity1.setCarNumber(deviceLasposition.getCarnumber());
//                    deviceAlarmSeverity1.setAlarmMileage(deviceLasposition.getMileage());
//                    deviceAlarmSeverity1.setAlarmStartTime(DateUtil.getDateFormat(new Date(),
//                            DateUtil.FULL_TIME_SPLIT_PATTERN));
//                    deviceAlarmSeverity1.setDeptid(Long.valueOf(deviceLasposition.getDeptid()));
//                    deviceAlarmSeverityMapper.insertAlarmSeverity(deviceAlarmSeverity1);
//                    List<M03> m03 = m03Mapper.selectM03(deviceLasposition.getCarnumber(), null, null,null);
//                    if (m03.size()>0){
//                        OperationLog operationLog = new OperationLog(null, m03.get(0).getRecId(), "报警", "无准运证行驶",
//                                DateUtil.getDateFormat(new Date(),
//                                        DateUtil.FULL_TIME_SPLIT_PATTERN),"0",null,null);
//                        operationLogMapper.insertLog(operationLog);
//                    }
//                    System.out.println("不好啦！报警了，这个人没有准运证");
//                } else {//无准运证情况但有无证运输的当天记录
//                    deviceAlarmSeverity.setAlarmEndSpeed(deviceLasposition.getSpeed());
//                    deviceAlarmSeverity.setAlarmMileage(deviceLasposition.getMileage());
//                    deviceAlarmSeverity.setAlarmEndTime(DateUtil.getDateFormat(new Date(),
//                            DateUtil.FULL_TIME_SPLIT_PATTERN));
//                    deviceAlarmSeverity.setAlarmEndLat(deviceLasposition.getLat().toString());
//                    deviceAlarmSeverity.setAlarmEndLng(deviceLasposition.getLng().toString());
//                    deviceAlarmSeverity.setDeptid(Long.valueOf(deviceLasposition.getDeptid()));
//                    deviceAlarmSeverityMapper.updateAlarmSeverity(deviceAlarmSeverity);
//                }
//                // }
//            }
//        }
//    }
//
//
//    @Scheduled(cron = " * 0/5 * * * ? ")
//    public void GPSDownIn() throws IOException {//gps不在线报警存数据库
//        System.out.println("开始查询不在线");
//        List<SysAuthDept> deptList = sysAuthDeptMapper.selectSysAuthDeptByParent(new Long("722445496500748288"));
//        List<DeviceLasposition> deviceLaspositions = new ArrayList<>();
//        for (SysAuthDept sysAuthDept : deptList) {
//            List<DeviceLasposition> deviceLasposition =
//                    deviceLaspositionMapper.selectLaspositionAlarm(sysAuthDept.getDeptid().toString());
//            deviceLaspositions.addAll(deviceLasposition);
//        }
//        for (DeviceLasposition resultDatum : deviceLaspositions) {
//            DeviceAlarmSeverity deviceAlarmSeverity = deviceAlarmSeverityMapper.selectAlarmSeverityTask(null,
//                    resultDatum.getCarnumber(), null, "离线告警", "A");
//            if (resultDatum.getCarstatus() == 1 || resultDatum.getCarstatus() == 2) {//分离线状态，在线状态
//                if (StringUtils.isEmpty(deviceAlarmSeverity)) {//离线状态下没有未完成的报警记录
//                    //车辆下线时间
//                    Long time = 0L;
//                    List<Map<String, Object>> maps =
//                            deviceOnlineRecordMapper.selectDeviceOnlineRecord(resultDatum.getTerminalId().toString(),
//                                    2, 1);
//                    if (maps.size() > 0) {
//                        for (Map<String, Object> map : maps) {
//                            time = DateUtil.datetime(map.get("create_date").toString(),
//                                    DateUtil.getDateFormat(new Date(),
//                                            DateUtil.FULL_TIME_SPLIT_PATTERN), DateUtil.FULL_TIME_SPLIT_PATTERN, "h");
//                        }
//                    }
//                    if (time > 15) {
//                        DeviceAlarmSeverity deviceAlarmSeverity1 = new DeviceAlarmSeverity();
//                        deviceAlarmSeverity1.setAlarmLng(resultDatum.getLng().toString());
//                        deviceAlarmSeverity1.setAlarmLat(resultDatum.getLat().toString());
//                        deviceAlarmSeverity1.setAlarmName("离线告警");
//                        deviceAlarmSeverity1.setAlarmStartSpeed(resultDatum.getSpeed());
//                        deviceAlarmSeverity1.setCarNumber(resultDatum.getCarnumber());
//                        deviceAlarmSeverity1.setAlarmStartTime(DateUtil.getDateFormat(new Date(),
//                                DateUtil.FULL_TIME_SPLIT_PATTERN));
//                        deviceAlarmSeverity1.setDeptid(Long.valueOf(resultDatum.getDeptid()));
//                        deviceAlarmSeverityMapper.insertAlarmSeverity(deviceAlarmSeverity1);
//                        System.out.println("不好啦！报警了，离线告警");
//                        deviceAlarmSeverityMapper.insertAlarmSeverity(deviceAlarmSeverity1);
//                        List<M03> m03 = m03Mapper.selectM03(resultDatum.getCarnumber(), null, null,null);
//                        if (m03.size()>0) {
//                            OperationLog operationLog = new OperationLog(null, m03.get(0).getRecId(), "报警", "离线告警",
//                                    DateUtil.getDateFormat(new Date(),
//                                            DateUtil.FULL_TIME_SPLIT_PATTERN),"0",null,null);
//                            operationLogMapper.insertLog(operationLog);
//                        }
//                    }
//                } else {
//                    deviceAlarmSeverity.setAlarmEndSpeed(resultDatum.getSpeed());
//                    deviceAlarmSeverity.setAlarmEndTime(DateUtil.getDateFormat(new Date(),
//                            DateUtil.FULL_TIME_SPLIT_PATTERN));
//                    deviceAlarmSeverity.setAlarmEndLat(resultDatum.getLat().toString());
//                    deviceAlarmSeverity.setAlarmEndLng(resultDatum.getLng().toString());
//                    deviceAlarmSeverity.setDeptid(Long.valueOf(resultDatum.getDeptid()));
//                    deviceAlarmSeverityMapper.updateAlarmSeverity(deviceAlarmSeverity);
//                }
//            }
//        }
//    }
//
//    /**
//     * @ Description: 超速报警
//     * @ Param: []
//     * @ return: void
//     * @ Author: 冷酷的苹果
//     * @ Date: 2020/7/24 17:50
//     */
//    @Scheduled(cron = " * 0/5 * * * ? ")
//    public void overspeed() {
//        System.out.println("开始查询超速");
//        List<SysAuthDept> deptList = sysAuthDeptMapper.selectSysAuthDeptByParent(new Long("722445496500748288"));
//        List<DeviceLasposition> deviceLaspositions = new ArrayList<>();
//        List<DeviceLasposition> overspeed = new ArrayList<>();
//        for (SysAuthDept sysAuthDept : deptList) {
//            List<DeviceLasposition> deviceLasposition =
//                    deviceLaspositionMapper.selectLaspositionAlarm(sysAuthDept.getDeptid().toString());
//            deviceLaspositions.addAll(deviceLasposition);
//        }
//        for (DeviceLasposition deviceLasposition : deviceLaspositions) {
//            if (Double.parseDouble(deviceLasposition.getSpeed()) > 65) {
//                overspeed.add(deviceLasposition);
//            }
//        }
//        if (overspeed.size() > 0) {
//            for (DeviceLasposition deviceLasposition : overspeed) {
//                List<EChatBean3> deviceAlarms = deviceAlarmMapper.selectTask(deviceLasposition.getCarnumber());
//                DeviceAlarmSeverity deviceAlarmSeverity = deviceAlarmSeverityMapper.selectAlarmSeverityTask(null,
//                        deviceLasposition.getCarnumber(), null, "超速告警", "A");
//                if (StringUtils.isEmpty(deviceAlarmSeverity) && deviceAlarms.size() > 0) {
//                    DeviceAlarmSeverity deviceAlarmSeverity1 = new DeviceAlarmSeverity();
//                    deviceAlarmSeverity1.setAlarmLng(deviceLasposition.getLng().toString());
//                    deviceAlarmSeverity1.setAlarmLat(deviceLasposition.getLat().toString());
//                    deviceAlarmSeverity1.setAlarmName("超速告警");
//                    deviceAlarmSeverity1.setAlarmStartSpeed(ListUtils.getLastElement(deviceAlarms).getStart_speed());
//                    deviceAlarmSeverity1.setCarNumber(deviceLasposition.getCarnumber());
//                    deviceAlarmSeverity1.setAlarmStartTime(ListUtils.getLastElement(deviceAlarms).getStart_time());
//                    deviceAlarmSeverity1.setAlarmEndTime(deviceAlarms.get(0).getEnd_time());
//                    deviceAlarmSeverity1.setAlarmEndSpeed(deviceAlarms.get(0).getEnd_speed());
//                    deviceAlarmSeverity1.setDeptid(Long.valueOf(deviceLasposition.getDeptid()));
//                    deviceAlarmSeverityMapper.insertAlarmSeverity(deviceAlarmSeverity1);
//                    System.out.println("不好啦！报警了，超速告警");
//                    deviceAlarmSeverityMapper.insertAlarmSeverity(deviceAlarmSeverity1);
//                    List<M03> m03 = m03Mapper.selectM03(deviceLasposition.getCarnumber(), null, null,null);
//                    if (m03.size()>0) {
//                        OperationLog operationLog = new OperationLog(null, m03.get(0).getRecId(), "报警", "超速告警",
//                                DateUtil.getDateFormat(new Date(),
//                                        DateUtil.FULL_TIME_SPLIT_PATTERN),"0",null,null);
//                        operationLogMapper.insertLog(operationLog);
//                    }
//                } else if (!StringUtils.isEmpty(deviceAlarmSeverity) && deviceAlarms.size() > 0) {
//                    deviceAlarmSeverity.setAlarmEndSpeed(deviceAlarms.get(0).getEnd_speed());
//                    deviceAlarmSeverity.setAlarmEndTime(deviceAlarms.get(0).getEnd_time());
//                    deviceAlarmSeverity.setAlarmEndLat(deviceLasposition.getLat().toString());
//                    deviceAlarmSeverity.setAlarmEndLng(deviceLasposition.getLng().toString());
//                    deviceAlarmSeverity.setDeptid(Long.valueOf(deviceLasposition.getDeptid()));
//                    deviceAlarmSeverityMapper.updateAlarmSeverity(deviceAlarmSeverity);
//                }
//            }
//        }
//    }
//
//
//    @Scheduled(cron = " 0 0 1,13 * * ? ")
//    public void targetCar() {//查询当天没有准运证的车辆
//        carTargetMapper.deleteCarTarget();
//        List<SysAuthDept> deptList = sysAuthDeptMapper.selectSysAuthDeptByParent(new Long("722445496500748288"));
//        List<DeviceLasposition> list = new ArrayList<>();
//        for (SysAuthDept sysAuthDept : deptList) {
//            List<DeviceLasposition> deviceLasposition =
//                    deviceLaspositionMapper.selectLaspositionAlarm(sysAuthDept.getDeptid().toString());
//            list.addAll(deviceLasposition);
//        }
//        for (DeviceLasposition deviceLasposition : list) {
//            List<Map<String, String>> list1 = muckMapper.selectMuck(deviceLasposition.getCarnumber(), null,
//                    DateUtil.timeVariousTypes(1));
//            if (list1.size() <= 0) {
//                CarTarget carTarget = new CarTarget();
//                carTarget.setCarNumber(deviceLasposition.getCarnumber());
//                carTargetMapper.insertCarTarget(carTarget);
//            }
//        }
//
//    }
//
//
////    @Scheduled(cron = " 0 30 7,19 * * ?")
////    public void timeOut() throws IOException {//运输超时
////        List<DeviceAlarmSeverity> list = new ArrayList<>();
////        String terminals = Task.getCarTerminal();
////        String address = url + "cmsapi/getTerminalGpsStatus";
////        String sign = Md5Util.MD5EncodeUtf8(username + "admin12320180908180001");
////        System.out.println(sign);
////        Map<String, String> map = new HashMap<>();
////        map.put("sign", sign);
////        map.put("tradeno", tradeno);
////        map.put("username", username);
////        map.put("terminal", terminals);
////        String json = JSON.toJSONString(map);
////        String result = HttpUtils.doJsonPost(address, json);
////        JSONObject jsonObject = JSONObject.parseObject(result);
////        List<Map<String, Object>> resultData = (List<Map<String, Object>>) jsonObject.get("resultData");
////        for (Map<String, Object> resultDatum : resultData) {
////            List<Map<String, String>> muck = muckMapper.selectMuck(resultDatum.get("carnumber").toString(), null,
////                    DateUtil.getDateFormat(new Date(), DateUtil.FULL_TIME_SPLIT_PATTERN));
////            if (muck.size() > 0 && !StringUtils.isEmpty(resultDatum.get("speed")) && Double.parseDouble(resultDatum
////            .get("speed").toString()) > 5) {
////                int type = 0;
////                for (Map<String, String> map1 : muck) {//判断是不是在准允时间内
////                    if (map1.get("PermitType").equals("日间准运") && type == 0) {
////                        type = 1;
////                    } else if (map1.get("PermitType").equals("夜间准运") && type == 0) {
////                        type = 2;
////                    } else {
////                        type = 3;
////                    }
////                    if (type == 1) {//白天准允时间
////                        SimpleDateFormat df = new SimpleDateFormat("HH:mm");//设置日期格式
////                        Date now = null;
////                        Date beginTime = null;
////                        Date endTime = null;
////                        try {
////                            now = df.parse(df.format(new Date()));
////                            beginTime = df.parse("06:00");
////                            endTime = df.parse("19:00");
////                        } catch (Exception e) {
////                            e.printStackTrace();
////                        }
////
////                        Boolean flag = belongCalendar(now, beginTime, endTime);
////                        if (!flag) {
////                            DeviceAlarmSeverity deviceAlarmSeverity = new DeviceAlarmSeverity();
////                            deviceAlarmSeverity.setAlarmLng(resultDatum.get("lng").toString());
////                            deviceAlarmSeverity.setAlarmLat(resultDatum.get("lat").toString());
////                            deviceAlarmSeverity.setAlarmName("超时运输");
////                            deviceAlarmSeverity.setAlarmStartSpeed(resultDatum.get("speed").toString());
////                            deviceAlarmSeverity.setCarNumber(resultDatum.get("carnumber").toString());
////                            deviceAlarmSeverity.setAlarmStartTime(DateUtil.getDateFormat(new Date(),
////                                    DateUtil.FULL_TIME_SPLIT_PATTERN));
////                            deviceAlarmSeverityMapper.insertAlarmSeverity(deviceAlarmSeverity);
////                            list.add(deviceAlarmSeverity);
////                            System.out.println("不好啦！报警了，这个人超时运输了");
////                        }
////                    } else if (type == 2) {//夜间准运时间
////                        SimpleDateFormat df = new SimpleDateFormat("HH:mm");//设置日期格式
////                        Date now = null;
////                        Date beginTime = null;
////                        Date endTime = null;
////                        try {
////                            now = df.parse(df.format(new Date()));
////                            beginTime = df.parse("18:00");
////                            endTime = df.parse("24:00");
////                        } catch (Exception e) {
////                            e.printStackTrace();
////                        }
////
////                        Boolean flag = belongCalendar(now, beginTime, endTime);
////                        SimpleDateFormat df1 = new SimpleDateFormat("HH:mm");//设置日期格式
////                        Date now1 = null;
////                        Date beginTime1 = null;
////                        Date endTime1 = null;
////                        try {
////                            now1 = df1.parse(df.format(new Date()));
////                            beginTime1 = df1.parse("00:00");
////                            endTime1 = df1.parse("07:00");
////                        } catch (Exception e) {
////                            e.printStackTrace();
////                        }
////
////                        Boolean flag1 = belongCalendar(now1, beginTime1, endTime1);
////                        if (!(flag || flag1)) {
////                            DeviceAlarmSeverity deviceAlarmSeverity = new DeviceAlarmSeverity();
////                            deviceAlarmSeverity.setAlarmLng(resultDatum.get("lng").toString());
////                            deviceAlarmSeverity.setAlarmLat(resultDatum.get("lat").toString());
////                            deviceAlarmSeverity.setAlarmName("超时运输");
////                            deviceAlarmSeverity.setAlarmStartSpeed(resultDatum.get("speed").toString());
////                            deviceAlarmSeverity.setCarNumber(resultDatum.get("carnumber").toString());
////                            deviceAlarmSeverity.setAlarmStartTime(DateUtil.getDateFormat(new Date(),
////                                    DateUtil.FULL_TIME_SPLIT_PATTERN));
////                            deviceAlarmSeverityMapper.insertAlarmSeverity(deviceAlarmSeverity);
////                            list.add(deviceAlarmSeverity);
////                            System.out.println("不好啦！报警了，这个人超时运输了");
////                        }
////                    }
////                }
////            }
////            WebSocket webSocket = new WebSocket();
////            webSocket.sendMessageAll(list.toString());
////        }
////    }
//
//    public static void main(String[] args) {
//        System.out.println("成功");
//    }
//}