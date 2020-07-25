/**
 * Copyright (C), 2020-2020, 众马科技有限公司
 * FileName: Task
 * Author:   冷酷的苹果
 * Date:     2020/7/3 13:59
 * Description: 定时任务
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.example.car.task;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.car.common.utils.DateUtil;
import com.example.car.common.utils.HttpUtils;
import com.example.car.common.utils.Md5Util;
import com.example.car.entity.CarMileage;
import com.example.car.entity.DeviceAlarmSeverity;
import com.example.car.mapper.mysql.CarMileageMapper;
import com.example.car.mapper.mysql.DeviceAlarmSeverityMapper;
import com.example.car.mapper.mysql.SysAuthDeptMapper;
import com.example.car.mapper.sqlserver.MuckMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.*;

/**
 * 〈一句话功能简述〉<br>
 * 〈定时任务〉
 *
 * @author 冷酷的苹果
 * @create 2020/7/3
 * @since 1.0.0
 */
@Component                //实例化
@Configurable             //注入bean
@EnableScheduling         //开启计划任务
public class Task {
    private final static String username = "yccgj";
    private final static String tradeno = "20180908180001";
    private final static String url = "http://101.132.236.6:8088/";


    @Autowired
    private SysAuthDeptMapper sysAuthDeptMapper;

    @Autowired
    private MuckMapper muckMapper;

    @Autowired
    private DeviceAlarmSeverityMapper deviceAlarmSeverityMapper;

    @Autowired
    private CarMileageMapper carMileageMapper;


    @Scheduled(cron = " * 0/10 * * * ? ")//无证运输存数据库
    public void noMuckIn() throws IOException {
        String terminals = Task.getCarTerminal();
        String address = url + "cmsapi/getTerminalGpsStatus";
        String sign = Md5Util.MD5EncodeUtf8(username + "admin12320180908180001");
        System.out.println(sign);
        Map<String, String> map = new HashMap<>();
        map.put("sign", sign);
        map.put("tradeno", tradeno);
        map.put("username", username);
        map.put("terminal", terminals);
        String json = JSON.toJSONString(map);
        String result = HttpUtils.doJsonPost(address, json);
        JSONObject jsonObject = JSONObject.parseObject(result);
        List<Map<String, Object>> resultData = (List<Map<String, Object>>) jsonObject.get("resultData");
        for (Map<String, Object> resultDatum : resultData) {
            Double mileage=null;
            DeviceAlarmSeverity deviceAlarmSeverity = deviceAlarmSeverityMapper.selectAlarmSeverityTask(null,
                    resultDatum.get("carnumber").toString(), null, "无准运证行驶", "A");
            CarMileage carMileage = carMileageMapper.selectByName(resultDatum.get("carnumber").toString());
            if (!StringUtils.isEmpty(resultDatum.get("summileage"))){
                mileage = Double.parseDouble(resultDatum.get("summileage").toString()) - carMileage.getCarMileage();
            }else {
                mileage=0.0;
            }

            if (!StringUtils.isEmpty(resultDatum.get("speed")) && Double.parseDouble(resultDatum.get("speed").toString()) > 35 && mileage > 3) {//分行驶和停止两个情况
                // if (StringUtils.isEmpty(deviceAlarmSeverity)||!StringUtils.isEmpty(deviceAlarmSeverity
                // .getAlarmEndTime())) {//判断行驶情况下没有未完成报警记录的情况
                List<Map<String, String>> muck = muckMapper.selectMuck(resultDatum.get("carnumber").toString(),
                        null, DateUtil.getDateFormat(new Date(), DateUtil.FULL_TIME_SPLIT_PATTERN));
                if (muck.size() <= 0 && StringUtils.isEmpty(deviceAlarmSeverity)) {//无准运证情况并且没有无证运输的当天记录
                    DeviceAlarmSeverity deviceAlarmSeverity1 = new DeviceAlarmSeverity();
                    deviceAlarmSeverity1.setAlarmLng(resultDatum.get("lng").toString());
                    deviceAlarmSeverity1.setAlarmLat(resultDatum.get("lat").toString());
                    deviceAlarmSeverity1.setAlarmName("无准运证行驶");
                    deviceAlarmSeverity1.setAlarmStartSpeed(resultDatum.get("speed").toString());
                    deviceAlarmSeverity1.setCarNumber(resultDatum.get("carnumber").toString());
                    deviceAlarmSeverity1.setAlarmMileage(mileage.toString());
                    deviceAlarmSeverity1.setAlarmStartTime(DateUtil.getDateFormat(new Date(),
                            DateUtil.FULL_TIME_SPLIT_PATTERN));
                    deviceAlarmSeverityMapper.insertAlarmSeverity(deviceAlarmSeverity1);
                    System.out.println("不好啦！报警了，这个人没有准运证");
                } else if (muck.size() <= 0 && !StringUtils.isEmpty(deviceAlarmSeverity)) {//无准运证情况但有无证运输的当天记录
                    deviceAlarmSeverity.setAlarmEndSpeed(resultDatum.get("speed").toString());
                    deviceAlarmSeverity.setAlarmMileage(mileage.toString());
                    deviceAlarmSeverity.setAlarmEndTime(DateUtil.getDateFormat(new Date(),
                            DateUtil.FULL_TIME_SPLIT_PATTERN));
                    deviceAlarmSeverity.setAlarmEndLat(resultDatum.get("lat").toString());
                    deviceAlarmSeverity.setAlarmEndLng(resultDatum.get("lng").toString());
                    deviceAlarmSeverityMapper.updateAlarmSeverity(deviceAlarmSeverity);
                }
                // }
            }
//            else {//停止状态
//                    if (!StringUtils.isEmpty(deviceAlarmSeverity)&&StringUtils.isEmpty(deviceAlarmSeverity
//                    .getAlarmEndTime())) {
//                        deviceAlarmSeverity.setAlarmEndSpeed(resultDatum.get("speed").toString());
//                        deviceAlarmSeverity.setAlarmEndTime(DateUtil.getDateFormat(new Date(),
//                                DateUtil.FULL_TIME_SPLIT_PATTERN));
//                        deviceAlarmSeverityMapper.updateAlarmSeverity(deviceAlarmSeverity);
//                    }
//            }
        }
    }

//    @Scheduled(cron = " * 0/10 * * * ? ")//无证运输推送
//    public void noMuck() throws IOException {
//        List<DeviceAlarmSeverity> list = new ArrayList<>();
//        String terminals = Task.getCarTerminal();
//        String address = url + "cmsapi/getTerminalGpsStatus";
//        String sign = Md5Util.MD5EncodeUtf8(username + "admin12320180908180001");
//        System.out.println(sign);
//        Map<String, String> map = new HashMap<>();
//        map.put("sign", sign);
//        map.put("tradeno", tradeno);
//        map.put("username", username);
//        map.put("terminal", terminals);
//        String json = JSON.toJSONString(map);
//        String result = HttpUtils.doJsonPost(address, json);
//        JSONObject jsonObject = JSONObject.parseObject(result);
//        List<Map<String, Object>> resultData = (List<Map<String, Object>>) jsonObject.get("resultData");
//        for (Map<String, Object> resultDatum : resultData) {
//            if (!StringUtils.isEmpty(resultDatum.get("speed")) && Double.parseDouble(resultDatum.get("speed")
//            .toString()) > 5) {
//                List<Map<String, String>> muck = muckMapper.selectMuck(resultDatum.get("carnumber").toString(), null,
//                        DateUtil.getDateFormat(new Date(), DateUtil.FULL_TIME_SPLIT_PATTERN));
//                if (muck.size() <= 0) {
//                    DeviceAlarmSeverity deviceAlarmSeverity = new DeviceAlarmSeverity();
//                    deviceAlarmSeverity.setAlarmLng(resultDatum.get("lng").toString());
//                    deviceAlarmSeverity.setAlarmLat(resultDatum.get("lat").toString());
//                    deviceAlarmSeverity.setAlarmName("无准运证行驶");
//                    deviceAlarmSeverity.setAlarmStartSpeed(resultDatum.get("speed").toString());
//                    deviceAlarmSeverity.setCarNumber(resultDatum.get("carnumber").toString());
//                    deviceAlarmSeverity.setAlarmStartTime(DateUtil.getDateFormat(new Date(),
//                            DateUtil.FULL_TIME_SPLIT_PATTERN));
//                    System.out.println("不好啦！报警了，这个人没有准运证");
//                    list.add(deviceAlarmSeverity);
//                }
//            }
//        }
//        WebSocket webSocket = new WebSocket();
//        webSocket.sendMessageAll(list.toString());
//    }


    @Scheduled(cron = " * 0/10 * * * ? ")
    public void GPSDownIn() throws IOException {//gps不在线报警存数据库
        String terminals = Task.getCarTerminal();
        String address = url + "cmsapi/getTerminalGpsStatus";
        String sign = Md5Util.MD5EncodeUtf8(username + "admin12320180908180001");
        System.out.println(sign);
        Map<String, String> map = new HashMap<>();
        map.put("sign", sign);
        map.put("tradeno", tradeno);
        map.put("username", username);
        map.put("terminal", terminals);
        String json = JSON.toJSONString(map);
        String result = HttpUtils.doJsonPost(address, json);
        JSONObject jsonObject = JSONObject.parseObject(result);
        List<Map<String, Object>> resultData = (List<Map<String, Object>>) jsonObject.get("resultData");
        for (Map<String, Object> resultDatum : resultData) {
            DeviceAlarmSeverity deviceAlarmSeverity = deviceAlarmSeverityMapper.selectAlarmSeverityTask(null,
                    resultDatum.get("carnumber").toString(), null, "GPS不在线", null);
            if (resultDatum.get("carstatus").toString().equals("1") || resultDatum.get("carstatus").toString().equals("2")) {//分离线状态，在线状态
                if (StringUtils.isEmpty(deviceAlarmSeverity) || !StringUtils.isEmpty(deviceAlarmSeverity.getAlarmEndTime())) {//离线状态下没有未完成的报警记录
                    DeviceAlarmSeverity deviceAlarmSeverity1 = new DeviceAlarmSeverity();
                    deviceAlarmSeverity1.setAlarmLng(resultDatum.get("lng").toString());
                    deviceAlarmSeverity1.setAlarmLat(resultDatum.get("lat").toString());
                    deviceAlarmSeverity1.setAlarmName("GPS不在线");
                    deviceAlarmSeverity1.setAlarmStartSpeed(resultDatum.get("speed").toString());
                    deviceAlarmSeverity1.setCarNumber(resultDatum.get("carnumber").toString());
                    deviceAlarmSeverity1.setAlarmStartTime(DateUtil.getDateFormat(new Date(),
                            DateUtil.FULL_TIME_SPLIT_PATTERN));
                    deviceAlarmSeverityMapper.insertAlarmSeverity(deviceAlarmSeverity1);
                    System.out.println("不好啦！报警了，这个人GPS不在线");
                }
            } else {//在线状态
                if (!StringUtils.isEmpty(deviceAlarmSeverity) && StringUtils.isEmpty(deviceAlarmSeverity.getAlarmEndTime())) {//在线 有未完成的报警记录
                    deviceAlarmSeverity.setAlarmEndSpeed(resultDatum.get("speed").toString());
                    deviceAlarmSeverity.setAlarmEndTime(DateUtil.getDateFormat(new Date(),
                            DateUtil.FULL_TIME_SPLIT_PATTERN));
                    deviceAlarmSeverity.setAlarmEndLat(resultDatum.get("lat").toString());
                    deviceAlarmSeverity.setAlarmEndLng(resultDatum.get("lng").toString());
                    deviceAlarmSeverityMapper.updateAlarmSeverity(deviceAlarmSeverity);
                }
            }
        }
    }

    /**
     * @Description: 检测系统车辆的里程数
     * @Param: []
     * @return: void
     * @Author: 冷酷的苹果
     * @Date: 2020/7/24 17:50
     */
    @Scheduled(cron = " 0 0 1 * * ? ")
    public void carMileage() {
        String terminals = Task.getCarTerminal();
        String address = url + "cmsapi/getTerminalGpsStatus";
        String sign = Md5Util.MD5EncodeUtf8(username + "admin12320180908180001");
        System.out.println(sign);
        Map<String, String> map = new HashMap<>();
        map.put("sign", sign);
        map.put("tradeno", tradeno);
        map.put("username", username);
        map.put("terminal", terminals);
        String json = JSON.toJSONString(map);
        String result = HttpUtils.doJsonPost(address, json);
        JSONObject jsonObject = JSONObject.parseObject(result);
        List<Map<String, Object>> resultData = (List<Map<String, Object>>) jsonObject.get("resultData");
        for (Map<String, Object> resultDatum : resultData) {
            System.out.println(resultDatum.get("carnumber").toString());
            CarMileage carMileage = carMileageMapper.selectByName(resultDatum.get("carnumber").toString());
            if (StringUtils.isEmpty(carMileage)) {
                CarMileage carMileage1 = new CarMileage();
                carMileage1.setCarName(resultDatum.get("carnumber").toString());
                carMileage1.setCarMileage(new Double(resultDatum.get("mileage").toString()));
                carMileage1.setCarMileageToday(0.0);
                carMileageMapper.insertCarMileage(carMileage1);
            } else {
                if (StringUtils.isEmpty(carMileage.getCarMileage())){
                    carMileage.setCarMileage(0.0);
                }
                carMileage.setCarMileageToday(Double.parseDouble(resultDatum.get("mileage").toString()) - carMileage.getCarMileage());
                carMileage.setCarMileage(new Double(resultDatum.get("mileage").toString()));
                carMileageMapper.updateCarMileage(carMileage);
            }
        }
    }


//    @Scheduled(cron = " * 0/13 * * * ? ")
//    public void GPSDown() throws IOException {//gps不在线报警推送
//        List<DeviceAlarmSeverity> list = new ArrayList<>();
//        String terminals = Task.getCarTerminal();
//        String address = url + "cmsapi/getTerminalGpsStatus";
//        String sign = Md5Util.MD5EncodeUtf8(username + "admin12320180908180001");
//        System.out.println(sign);
//        Map<String, String> map = new HashMap<>();
//        map.put("sign", sign);
//        map.put("tradeno", tradeno);
//        map.put("username", username);
//        map.put("terminal", terminals);
//        String json = JSON.toJSONString(map);
//        String result = HttpUtils.doJsonPost(address, json);
//        JSONObject jsonObject = JSONObject.parseObject(result);
//        List<Map<String, Object>> resultData = (List<Map<String, Object>>) jsonObject.get("resultData");
//        for (Map<String, Object> resultDatum : resultData) {
//            if (StringUtils.isEmpty(resultDatum.get("gpsflag")) && Double.parseDouble(resultDatum.get("speed")
//            .toString()) > 5) {
//                if (resultDatum.get("gpsflag").toString().equals("0")) {
//                    DeviceAlarmSeverity deviceAlarmSeverity = new DeviceAlarmSeverity();
//                    deviceAlarmSeverity.setAlarmLng(resultDatum.get("lng").toString());
//                    deviceAlarmSeverity.setAlarmLat(resultDatum.get("lat").toString());
//                    deviceAlarmSeverity.setAlarmName("GPS不在线");
//                    deviceAlarmSeverity.setAlarmStartSpeed(resultDatum.get("speed").toString());
//                    deviceAlarmSeverity.setCarNumber(resultDatum.get("carnumber").toString());
//                    deviceAlarmSeverity.setAlarmStartTime(DateUtil.getDateFormat(new Date(),
//                            DateUtil.FULL_TIME_SPLIT_PATTERN));
//                    System.out.println("不好啦！报警了，这个人GPS不在线");
//                    list.add(deviceAlarmSeverity);
//                }
//            }
//        }
//        WebSocket webSocket = new WebSocket();
//        webSocket.sendMessageAll(list.toString());
//    }

//    @Scheduled(cron = " 0 30 7,19 * * ?")
//    public void timeOut() throws IOException {//运输超时
//        List<DeviceAlarmSeverity> list = new ArrayList<>();
//        String terminals = Task.getCarTerminal();
//        String address = url + "cmsapi/getTerminalGpsStatus";
//        String sign = Md5Util.MD5EncodeUtf8(username + "admin12320180908180001");
//        System.out.println(sign);
//        Map<String, String> map = new HashMap<>();
//        map.put("sign", sign);
//        map.put("tradeno", tradeno);
//        map.put("username", username);
//        map.put("terminal", terminals);
//        String json = JSON.toJSONString(map);
//        String result = HttpUtils.doJsonPost(address, json);
//        JSONObject jsonObject = JSONObject.parseObject(result);
//        List<Map<String, Object>> resultData = (List<Map<String, Object>>) jsonObject.get("resultData");
//        for (Map<String, Object> resultDatum : resultData) {
//            List<Map<String, String>> muck = muckMapper.selectMuck(resultDatum.get("carnumber").toString(), null,
//                    DateUtil.getDateFormat(new Date(), DateUtil.FULL_TIME_SPLIT_PATTERN));
//            if (muck.size() > 0 && !StringUtils.isEmpty(resultDatum.get("speed")) && Double.parseDouble(resultDatum
//            .get("speed").toString()) > 5) {
//                int type = 0;
//                for (Map<String, String> map1 : muck) {//判断是不是在准允时间内
//                    if (map1.get("PermitType").equals("日间准运") && type == 0) {
//                        type = 1;
//                    } else if (map1.get("PermitType").equals("夜间准运") && type == 0) {
//                        type = 2;
//                    } else {
//                        type = 3;
//                    }
//                    if (type == 1) {//白天准允时间
//                        SimpleDateFormat df = new SimpleDateFormat("HH:mm");//设置日期格式
//                        Date now = null;
//                        Date beginTime = null;
//                        Date endTime = null;
//                        try {
//                            now = df.parse(df.format(new Date()));
//                            beginTime = df.parse("06:00");
//                            endTime = df.parse("19:00");
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//
//                        Boolean flag = belongCalendar(now, beginTime, endTime);
//                        if (!flag) {
//                            DeviceAlarmSeverity deviceAlarmSeverity = new DeviceAlarmSeverity();
//                            deviceAlarmSeverity.setAlarmLng(resultDatum.get("lng").toString());
//                            deviceAlarmSeverity.setAlarmLat(resultDatum.get("lat").toString());
//                            deviceAlarmSeverity.setAlarmName("超时运输");
//                            deviceAlarmSeverity.setAlarmStartSpeed(resultDatum.get("speed").toString());
//                            deviceAlarmSeverity.setCarNumber(resultDatum.get("carnumber").toString());
//                            deviceAlarmSeverity.setAlarmStartTime(DateUtil.getDateFormat(new Date(),
//                                    DateUtil.FULL_TIME_SPLIT_PATTERN));
//                            deviceAlarmSeverityMapper.insertAlarmSeverity(deviceAlarmSeverity);
//                            list.add(deviceAlarmSeverity);
//                            System.out.println("不好啦！报警了，这个人超时运输了");
//                        }
//                    } else if (type == 2) {//夜间准运时间
//                        SimpleDateFormat df = new SimpleDateFormat("HH:mm");//设置日期格式
//                        Date now = null;
//                        Date beginTime = null;
//                        Date endTime = null;
//                        try {
//                            now = df.parse(df.format(new Date()));
//                            beginTime = df.parse("18:00");
//                            endTime = df.parse("24:00");
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//
//                        Boolean flag = belongCalendar(now, beginTime, endTime);
//                        SimpleDateFormat df1 = new SimpleDateFormat("HH:mm");//设置日期格式
//                        Date now1 = null;
//                        Date beginTime1 = null;
//                        Date endTime1 = null;
//                        try {
//                            now1 = df1.parse(df.format(new Date()));
//                            beginTime1 = df1.parse("00:00");
//                            endTime1 = df1.parse("07:00");
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//
//                        Boolean flag1 = belongCalendar(now1, beginTime1, endTime1);
//                        if (!(flag || flag1)) {
//                            DeviceAlarmSeverity deviceAlarmSeverity = new DeviceAlarmSeverity();
//                            deviceAlarmSeverity.setAlarmLng(resultDatum.get("lng").toString());
//                            deviceAlarmSeverity.setAlarmLat(resultDatum.get("lat").toString());
//                            deviceAlarmSeverity.setAlarmName("超时运输");
//                            deviceAlarmSeverity.setAlarmStartSpeed(resultDatum.get("speed").toString());
//                            deviceAlarmSeverity.setCarNumber(resultDatum.get("carnumber").toString());
//                            deviceAlarmSeverity.setAlarmStartTime(DateUtil.getDateFormat(new Date(),
//                                    DateUtil.FULL_TIME_SPLIT_PATTERN));
//                            deviceAlarmSeverityMapper.insertAlarmSeverity(deviceAlarmSeverity);
//                            list.add(deviceAlarmSeverity);
//                            System.out.println("不好啦！报警了，这个人超时运输了");
//                        }
//                    }
//                }
//            }
//            WebSocket webSocket = new WebSocket();
//            webSocket.sendMessageAll(list.toString());
//        }
//    }

//    @Scheduled(cron = "0 0/10 18-7 * * ?")
//    public void park(){//规定区域停车
//
//    }


    public static String getCarTerminal() {
        String terminals = null;
        String address = url + "cmsapi/deptTree";
        String sign = Md5Util.MD5EncodeUtf8(username + "admin12320180908180001");
        System.out.println(sign);
        Map<String, String> map = new HashMap<>();
        map.put("sign", sign);
        map.put("tradeno", tradeno);
        map.put("username", username);
        String json = JSON.toJSONString(map);
        System.out.println(json);
        String result = HttpUtils.httpPostRaw(address, json, null, "UTF-8");
        System.out.println(result);
        JSONObject jsonObject = JSONObject.parseObject(result);
        List<Map<String, Object>> resultData = (List<Map<String, Object>>) jsonObject.get("resultData");
        for (Map<String, Object> resultDatum : resultData) {
            if (!StringUtils.isEmpty(resultDatum.get("terminal")) && StringUtils.isEmpty(terminals)) {
                terminals = (resultDatum.get("terminal").toString());
            } else if (!StringUtils.isEmpty(resultDatum.get("terminal")) && !StringUtils.isEmpty(terminals)) {
                terminals = terminals + "," + (resultDatum.get("terminal").toString());
            }
        }
        return terminals;
    }

    /**
     * 判断时间是否在时间段内
     *
     * @param nowTime
     * @param beginTime
     * @param endTime
     * @return
     */
    public static boolean belongCalendar(Date nowTime, Date beginTime, Date endTime) {
        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(beginTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }


    public static void main(String[] args) {
        DeviceAlarmSeverity deviceAlarmSeverity = null;
        if (!StringUtils.isEmpty(deviceAlarmSeverity) && StringUtils.isEmpty(deviceAlarmSeverity.getAlarmEndTime())) {
            System.out.println("成功");
        } else {

            System.out.println("成功");
        }
    }
}
