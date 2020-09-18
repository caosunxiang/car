/**
 * Copyright (C), 2020-2020, 众马科技有限公司
 * FileName: APIManage
 * Author:   冷酷的苹果
 * Date:     2020/6/16 11:58
 * Description: 接口管理
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.example.car.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.car.common.utils.*;
import com.example.car.common.utils.async.service.AreaSelect;
import com.example.car.common.utils.entity.*;
import com.example.car.common.utils.json.Body;
import com.example.car.entity.CarInfo;
import com.example.car.entity.DeviceAlarmSeverity;
import com.example.car.entity.DeviceLasposition;
import com.example.car.entity.SysAuthDept;
import com.example.car.mapper.mysql.*;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 〈一句话功能简述〉<br>
 * 〈接口管理〉
 *
 * @author 冷酷的苹果
 * @create 2020/6/16
 * @since 1.0.0
 */
@RequestMapping("Api")
@Slf4j
@CrossOrigin
@RestController
public class APIManage {
    private static final Logger logger = LoggerFactory.getLogger(APIManage.class);


    private final static String username = "yccgj";
    private final static String tradeno = "20180908180001";
    private final static String url = "http://101.132.236.6:8088/";
    private static Integer AlarmCount;
    @Autowired
    private CarInfoMapper carInfoMapper;
    @Autowired
    private SysAuthDeptMapper sysAuthDeptMapper;
    @Autowired
    private DeviceAlarmSeverityMapper deviceAlarmSeverityMapper;
    @Autowired
    private DeviceAlarmMapper deviceAlarmMapper;
    @Autowired
    private AreaSelect areaSelect;
    @Autowired
    private DeviceLaspositionMapper deviceLaspositionMapper;
    @Autowired
    private DeviceOnlineRecordMapper deviceOnlineRecordMapper;
    @Autowired
    private CarStatusChangeRecordMapper carStatusChangeRecordMapper;

    /**
     * @Description: 接口转发
     * @Param: [address, json]
     * @return: java.lang.String
     * @Author: 冷酷的苹果
     * @Date: 2020/6/18 14:03
     */
    @RequestMapping("transmit")
    public String transmit(String address, String json) {
        address = url + address;
        System.out.println("url:" + address);
        System.out.println("json:" + json);
        return HttpUtils.doJsonPost(address, json);
    }


    /**
     * @Description: 历史轨迹
     * @Param: []
     * @return: com.example.car.common.utils.json.Body
     * @Author: 冷酷的苹果
     * @Date: 2020/6/16 12:00
     */
    @RequestMapping("carRecord")
    public Body carRecord(String carnumber, String startTime,
                          String endTime) {
        String address = url + "cmsapi/getHistoryTrack";
        String sign = Md5Util.MD5EncodeUtf8(username + "admin12320180908180001");
        Map<String, String> map = new HashMap<>();
        map.put("carnumber", carnumber);
        map.put("endTime", endTime);
        map.put("sign", sign);
        map.put("startTime", startTime);
        map.put("tradeno", tradeno);
        map.put("username", username);
        String json = JSON.toJSONString(map);
        String result = HttpUtils.httpPostRaw(address, json, null, "UTF-8");
        JSONObject jsonObject = JSONObject.parseObject(result);
        List<Map<String, Object>> list = (List<Map<String, Object>>) jsonObject.get("resultData");
        if (list.size() > 0) {
            String lat = list.get(0).get("lat").toString();
            String lng = list.get(0).get("lng").toString();
            String direction = list.get(0).get("direction").toString();
            List<Map<String, Object>> list1 = new ArrayList<>();
            list1.add(list.get(0));
            for (int i = 0; i < list.size(); i++) {
                if (i != list.size() - 1) {
                    if (!list.get(i + 1).get("lat").equals(0) && !list.get(i + 1).get("lng").equals(0)) {
                        if (!lat.equals(list.get(i + 1).get("lat").toString()) || !lng.equals(list.get(i + 1).get(
                                "lng").toString())) {
                            if (Direction.directionGap(Double.parseDouble(direction),
                                    Double.parseDouble(list.get(i + 1).get("direction").toString()))) {
                                list1.add(list.get(i + 1));
                            }
                            lat = list.get(i + 1).get("lat").toString();
                            lng = list.get(i + 1).get("lng").toString();
                        }
                    }
                }
            }
            // list.sort((o1, o2) -> Integer.compare(o1.get("createtime").toString().compareTo(o2.get("createtime")
            // .toString()), 0));
            System.out.println(list1.toString());
            return Body.newInstance(list1);
        }
        return Body.newInstance(list);
    }

    /**
     * @Description: 报警信息处理
     * @Param: []
     * @return: com.example.car.common.utils.json.Body
     * @Author: 冷酷的苹果
     * @Date: 2020/6/16 12:00
     */
    @RequestMapping("alertProcessing")
    public Body alertProcessing(String carnumber, String startTime, String page, String size,
                                String endTime) {
        String address = url + "cmsapi/getAlarmList";
        String sign = Md5Util.MD5EncodeUtf8(username + "admin12320180908180001");
        System.out.println(sign);
        Map<String, String> map = new HashMap<>();
        map.put("currentPage", page);
        map.put("everyPage", size);
        map.put("carnumber", carnumber);
        map.put("endTime", endTime);
        map.put("sign", sign);
        map.put("startTime", startTime);
        map.put("tradeno", tradeno);
        map.put("username", username);
        String json = JSON.toJSONString(map);
        String result = HttpUtils.httpPostRaw(address, json, null, "UTF-8");
        System.out.println(result);
        JSONObject jsonObject = JSONObject.parseObject(result);
        Map<String, Object> resultData = (Map<String, Object>) jsonObject.get("resultData");
        List<Map<String, Object>> list = (List<Map<String, Object>>) resultData.get("list");
        return Body.newInstance(list);
    }

    /**
     * @Description: 查询所有的报警信息
     * @Param: [startTime, endTime]
     * @return: com.example.car.common.utils.json.Body
     * @Author: 冷酷的苹果
     * @Date: 2020/7/2 15:17
     */
    @RequestMapping("selectAlarmAll")
    public Body selectAlarmAll(String startTime, String endTime, String number, Integer size, Integer type) {
        Long id = new Long("722445496500748288");
        if (StringUtils.isEmpty(size)) {
            size = 250;
        }
        List<Map<String, Object>> list = new ArrayList<>();
        List<SysAuthDept> deptList = sysAuthDeptMapper.selectSysAuthDeptByParent(id);
        for (SysAuthDept sysAuthDept : deptList) {
            list.addAll(this.deviceAlarmMapper.selectAlarm(number, startTime, endTime, sysAuthDept.getDeptid(), size,
                    type));
        }
        List<Map<String, Object>> list1 = deviceAlarmSeverityMapper.selectAlarmSeverityAll(startTime, endTime, number
                , size);
        list.addAll(list1);
        return Body.newInstance(list);
    }


    /**
     * @Description: 查询组织信息
     * @Param: [name, startTime]
     * @return: com.example.car.common.utils.json.Body
     * @Author: 冷酷的苹果
     * @Date: 2020/6/18 9:09
     */
    @RequestMapping("selectOrganization")
    public Body selectOrganization(String name, String startTime) {
        String address = url + "cmsapi/deptTree";
        String sign = Md5Util.MD5EncodeUtf8(username + "admin12320180908180001");
        System.out.println(sign);
        Map<String, String> map = new HashMap<>();
        map.put("sign", sign);
        map.put("startTime", startTime);
        map.put("tradeno", tradeno);
        map.put("username", username);
        String json = JSON.toJSONString(map);
        String result = HttpUtils.doJsonPost(address, json);
        System.out.println(result);
        JSONObject jsonObject = JSONObject.parseObject(result);
        List<Map<String, Object>> resultData = (List<Map<String, Object>>) jsonObject.get("resultData");
        return Body.newInstance(resultData);
    }

    /**
     * @Description: 查询两个设备的状态
     * @Param: [name, startTime]
     * @return: com.example.car.common.utils.json.Body
     * @Author: 冷酷的苹果
     * @Date: 2020/6/18 9:10
     */
    @RequestMapping("selectGPSStatus")
    public Body selectGPSStatus(String terminal, String carnumber) {
        Map<String, String> status = new HashMap<>();
        String address = url + "cmsapi/getCarStatus";
        String sign = Md5Util.MD5EncodeUtf8(username + "admin12320180908180001");
        System.out.println(sign);
        Map<String, String> map = new HashMap<>();
        map.put("sign", sign);
        if (!StringUtils.isEmpty(terminal)) {
            map.put("terminal", terminal);
        } else {
            map.put("carnumber", carnumber);
        }
        map.put("tradeno", tradeno);
        map.put("username", username);
        String jsonMain = JSON.toJSONString(map);
        String resultMain = HttpUtils.doJsonPost(address, jsonMain);
        JSONObject jsonObjectMain = JSONObject.parseObject(resultMain);
        List<Map<String, Object>> resultDataMain = (List<Map<String, Object>>) jsonObjectMain.get("resultData");
        if (resultDataMain.size() > 0) {
            for (Map<String, Object> stringObjectMap : resultDataMain) {
                if (stringObjectMap.get("carstatus").equals(1) || stringObjectMap.get("carstatus").equals(2)) {
                    status.put("GPS1", "2");
                } else if (stringObjectMap.get("carstatus").equals(3) || stringObjectMap.get("carstatus").equals(4
                ) || stringObjectMap.get("carstatus").equals(5) || stringObjectMap.get("carstatus").equals(7)) {
                    status.put("GPS1", "1");
                } else if (stringObjectMap.get("carstatus").equals(6)) {
                    status.put("GPS1", "3");
                } else {
                    status.put("GPS1", "4");
                }
            }
        } else {
            status.put("GPS2", "4");
        }
        map.put("carnumber", carnumber + "辅");
        String json = JSON.toJSONString(map);
        String result = HttpUtils.doJsonPost(address, json);
        JSONObject jsonObject = JSONObject.parseObject(result);
        List<Map<String, Object>> resultData = (List<Map<String, Object>>) jsonObject.get("resultData");
        if (resultData.size() > 0) {
            for (Map<String, Object> stringObjectMap : resultData) {
                System.out.println("当前状态" + stringObjectMap.get("carstatus"));
                if (stringObjectMap.get("carstatus").equals(1) || stringObjectMap.get("carstatus").equals(2)) {
                    status.put("GPS2", "2");
                } else if (stringObjectMap.get("carstatus").equals(3) || stringObjectMap.get("carstatus").equals(4
                ) || stringObjectMap.get("carstatus").equals(5) || stringObjectMap.get("carstatus").equals(7)) {
                    status.put("GPS2", "1");
                } else if (stringObjectMap.get("carstatus").equals(6)) {
                    status.put("GPS2", "3");
                } else {
                    status.put("GPS2", "4");
                }
            }
        } else {
            status.put("GPS2", "4");
        }
        return Body.newInstance(status);
    }

    /**
     * @Description: 查询首页指定范围的车辆位置信息
     * @Param: [json, lat, lng, distance]
     * @return: com.example.car.common.utils.json.Body
     * @Author: 冷酷的苹果
     * @Date: 2020/6/28 10:43
     */
    @RequestMapping("selectHome")
    public Body selectHome(Double lat, Double lng, Double distance) {
        if (StringUtils.isEmpty(lat) || StringUtils.isEmpty(lng)) {
            return Body.BODY_451;
        }
        List<SysAuthDept> deptList = sysAuthDeptMapper.selectSysAuthDeptByParent(new Long("722445496500748288"));
        List<DeviceAlarmSeverity> deviceAlarmSeverity = deviceAlarmSeverityMapper.selectAlarmGroupCar();
        List<DeviceLasposition> deviceLaspositions = new ArrayList<>();
        List<DeviceLasposition> list = new ArrayList<>();
        for (SysAuthDept sysAuthDept : deptList) {
            List<DeviceLasposition> deviceLasposition =
                    deviceLaspositionMapper.selectLasposition(sysAuthDept.getDeptid().toString());
            for (DeviceLasposition lasposition : deviceLasposition) {
                if (sysAuthDept.getDeptid().equals(lasposition.getDeptid())) {
                    lasposition.setDept(sysAuthDept.getDeptname());
                    lasposition.setBk1(lasposition.getDeptid().toString());
                }
            }
            deviceLaspositions.addAll(deviceLasposition);
        }
        for (DeviceLasposition resultDatum : deviceLaspositions) {
            Double latCar = resultDatum.getLat();
            Double lngCar = resultDatum.getLng();
            boolean isIn = Distance.coordinateToDistance(lat, lng, latCar, lngCar, distance);
            if (!isIn) {
                if (!StringUtils.isEmpty(resultDatum.getCarnumber())){
                    for (DeviceAlarmSeverity alarmSeverity : deviceAlarmSeverity) {
                        if (resultDatum.getCarnumber().equals(alarmSeverity.getCarNumber())){
                            resultDatum.setHint(true);
                        }else {
                            resultDatum.setHint(false);
                        }
                    }
                }else {
                    resultDatum.setHint(false);
                }
                list.add(resultDatum);
            }
        }
        return Body.newInstance(list);
    }

    /**
     * @Description: 首页指定车牌查询
     * @Param: [number]
     * @return: com.example.car.common.utils.json.Body
     * @Author: 冷酷的苹果
     * @Date: 2020/6/30 15:28
     */
    @RequestMapping("homeSelect")
    public Body homeSelect(String number) {
        DeviceLasposition deviceLasposition = deviceLaspositionMapper.selectLaspositionByCarNo(number);
        deviceLasposition.setDept(sysAuthDeptMapper.selectSysAuthDeptById(deviceLasposition.getDeptid()).getDeptname());
        List<EChatBean3> EChatBean3 = deviceAlarmSeverityMapper.selectEChat1(number,
                null, null, deviceLasposition.getDeptid().toString(), "A", null);
        deviceLasposition.setAlarm(EChatBean3);
        return Body.newInstance(deviceLasposition);
    }

    /**
     * @Description: 区域查车
     * @Param: [lat1, lng1, lat2, lng2, startTime, endTime, terminals]
     * @return: com.example.car.common.utils.json.Body
     * @Author: 冷酷的苹果
     * @Date: 2020/7/2 9:32
     */
    @RequestMapping("areaSelect")
    public Body areaSelect(Double lat1, Double lng1, String startTime, String endTime,
                           String numbers) throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        List<String> list = CutString.divide(numbers);
        List<List<String>> lists = ListUtils.averageAssign(list, 6);
        List<Map<String, String>> mapList = new ArrayList<>();
        CompletableFuture<List<Map<String, String>>> page1 = areaSelect.areaSelect(lat1, lng1, startTime,
                endTime, lists.get(0));
        CompletableFuture<List<Map<String, String>>> page2 = areaSelect.areaSelect(lat1, lng1, startTime,
                endTime, lists.get(1));
        CompletableFuture<List<Map<String, String>>> page3 = areaSelect.areaSelect(lat1, lng1, startTime,
                endTime, lists.get(2));
        CompletableFuture<List<Map<String, String>>> page4 = areaSelect.areaSelect(lat1, lng1, startTime,
                endTime, lists.get(3));
        CompletableFuture<List<Map<String, String>>> page5 = areaSelect.areaSelect(lat1, lng1, startTime,
                endTime, lists.get(4));
        CompletableFuture<List<Map<String, String>>> page6 = areaSelect.areaSelect(lat1, lng1, startTime,
                endTime, lists.get(5));
        // Wait until they are all done
        //join() 的作用：让“主线程”等待“子线程”结束之后才能继续运行
        CompletableFuture.allOf(page1, page2, page3, page4, page5, page6).join();
        // Print results, including elapsed time
        float exc = (float) (System.currentTimeMillis() - start) / 1000;
        logger.info("Elapsed time: " + exc + " seconds");
        logger.info("--> " + page1.get());
        logger.info("--> " + page2.get());
        logger.info("--> " + page3.get());
        logger.info("--> " + page4.get());
        logger.info("--> " + page5.get());
        logger.info("--> " + page6.get());
        mapList.addAll(page1.get());
        mapList.addAll(page2.get());
        mapList.addAll(page3.get());
        mapList.addAll(page4.get());
        mapList.addAll(page5.get());
        mapList.addAll(page6.get());
        return Body.newInstance(mapList);
    }

    /**
     * @Description: 折线图
     * @Param: [number, time]
     * @return: com.example.car.common.utils.json.Body
     * @Author: 冷酷的苹果
     * @Date: 2020/8/3 15:58
     */
    @RequestMapping("selectEChat")
    public Body selectEChat(String number, String startTime, String endTime) {
        long up = 0;
        long down = 0;
        long other = 0;
        String temp = startTime;
        Map<String, Long> map = new HashMap<>();
        List<EChatBean> list = new ArrayList<>();
        DeviceLasposition deviceLasposition = deviceLaspositionMapper.selectLaspositionByCarNo(number);
        CarInfo carInfo = carInfoMapper.selectCarOnly(number);
        List<EChatBean> deviceOnlineRecords = deviceOnlineRecordMapper.selectEChat(number, startTime, endTime);
        for (EChatBean deviceOnlineRecord : deviceOnlineRecords) {
            if (deviceOnlineRecord.getStatus().equals(1)) {
                deviceOnlineRecord.setType("上线");
            } else {
                deviceOnlineRecord.setType("下线");
            }
        }
        List<EChatBean> carStatusChangeRecords = carStatusChangeRecordMapper.selectEChat(number, startTime, endTime);
        if (carInfo.getCarServiceStatus() != 0 && carStatusChangeRecords.size() <= 0) {
            EChatBean eChatBean = new EChatBean();
            eChatBean.setType("报备");
            eChatBean.setEndTime(endTime);
            eChatBean.setCreateDate(startTime);
            list.add(eChatBean);
        } else {
            for (EChatBean carStatusChangeRecord : carStatusChangeRecords) {
                if (carStatusChangeRecord.getStatus().equals(0)) {
                    carStatusChangeRecord.setType("正常");
                    if (deviceLasposition.getCarstatus() == 1 || deviceLasposition.getCarstatus() == 2) {
                        carStatusChangeRecord.setNowStatus("下线");
                    } else {
                        carStatusChangeRecord.setNowStatus("上线");
                    }
                } else {
                    carStatusChangeRecord.setType("报备");
                }
            }
            list.addAll(deviceOnlineRecords);
            list.addAll(carStatusChangeRecords);
            list.sort((o1, o2) -> Integer.compare(o1.getCreateDate().compareTo(o2.getCreateDate()), 0));
            if (list.size() <= 0) {
                EChatBean eChatBean = new EChatBean();
                if (carInfo.getCarServiceStatus() == 0) {
                    if (!StringUtils.isEmpty(deviceLasposition)) {
                        if (deviceLasposition.getCarstatus() == 1 || deviceLasposition.getCarstatus() == 2) {
                            eChatBean.setNowStatus("下线");
                        } else {
                            eChatBean.setNowStatus("上线");
                        }
                        list.add(eChatBean);
                    }
                } else {
                    eChatBean.setType("报备");
                    list.add(eChatBean);
                }
            }
        }
        System.out.println(list.toString());
        for (EChatBean eChatBean : list) {
            if (!StringUtils.isEmpty(eChatBean.getType()) && eChatBean.getType().equals("下线")) {
                if (StringUtils.isEmpty(eChatBean.getCreateDate())) {
                    up += DateUtil.datetime(temp, endTime, DateUtil.FULL_TIME_SPLIT_PATTERN, "m");
                    temp = endTime;
                } else {
                    up += DateUtil.datetime(temp, eChatBean.getCreateDate(), DateUtil.FULL_TIME_SPLIT_PATTERN, "m");
                    temp = eChatBean.getCreateDate();
                }
            } else if (!StringUtils.isEmpty(eChatBean.getType()) && eChatBean.getType().equals("上线")) {
                if (StringUtils.isEmpty(eChatBean.getCreateDate())) {
                    down += DateUtil.datetime(temp, endTime, DateUtil.FULL_TIME_SPLIT_PATTERN, "m");
                    temp = endTime;
                } else {
                    down += DateUtil.datetime(temp, eChatBean.getCreateDate(), DateUtil.FULL_TIME_SPLIT_PATTERN, "m");
                    temp = eChatBean.getCreateDate();
                }
            } else if (!StringUtils.isEmpty(eChatBean.getType()) && eChatBean.getType().equals("报备")) {
                if (StringUtils.isEmpty(eChatBean.getCreateDate())) {
                    other += DateUtil.datetime(temp, endTime, DateUtil.FULL_TIME_SPLIT_PATTERN, "m");
                    temp = endTime;
                } else {
                    other += DateUtil.datetime(temp, eChatBean.getCreateDate(), DateUtil.FULL_TIME_SPLIT_PATTERN, "m");
                    temp = eChatBean.getCreateDate();
                }
            } else {
                if (eChatBean.getNowStatus().equals("下线")) {
                    if (StringUtils.isEmpty(eChatBean.getCreateDate())) {
                        down += DateUtil.datetime(temp, endTime, DateUtil.FULL_TIME_SPLIT_PATTERN, "m");
                        temp = endTime;
                    } else {
                        down += DateUtil.datetime(temp, eChatBean.getCreateDate(), DateUtil.FULL_TIME_SPLIT_PATTERN,
                                "m");
                        temp = eChatBean.getCreateDate();
                    }
                } else {
                    if (StringUtils.isEmpty(eChatBean.getCreateDate())) {
                        up += DateUtil.datetime(temp, endTime, DateUtil.FULL_TIME_SPLIT_PATTERN, "m");
                        temp = endTime;
                    } else {
                        up += DateUtil.datetime(temp, eChatBean.getCreateDate(), DateUtil.FULL_TIME_SPLIT_PATTERN, "m");
                        temp = eChatBean.getCreateDate();
                    }
                }
            }
        }
        EChatBean eChatBean = ListUtils.getLastElement(list);
        if (!StringUtils.isEmpty(eChatBean.getType()) && eChatBean.getType().equals("下线")) {
            down += DateUtil.datetime(temp, endTime, DateUtil.FULL_TIME_SPLIT_PATTERN, "m");
        } else if (!StringUtils.isEmpty(eChatBean.getType()) && eChatBean.getType().equals("上线")) {
            up += DateUtil.datetime(temp, endTime, DateUtil.FULL_TIME_SPLIT_PATTERN, "m");
        } else if (!StringUtils.isEmpty(eChatBean.getType()) && eChatBean.getType().equals("报备")) {
            other += DateUtil.datetime(temp, endTime, DateUtil.FULL_TIME_SPLIT_PATTERN, "m");
        } else {
            if (eChatBean.getNowStatus().equals("下线")) {
                down += DateUtil.datetime(temp, endTime, DateUtil.FULL_TIME_SPLIT_PATTERN, "m");
            } else {
                up += DateUtil.datetime(temp, endTime, DateUtil.FULL_TIME_SPLIT_PATTERN, "m");
            }
        }
        map.put("up", up);
        map.put("down", down);
        map.put("other", other);
        return Body.newInstance(map);
    }

    /**
     * @Description: 计算时间差
     * @Param: [startTime, endTime]
     * @return: com.example.car.common.utils.json.Body
     * @Author: 冷酷的苹果
     * @Date: 2020/8/3 16:21
     */
    @RequestMapping("timeDeff")
    public Body timeDeff(String startTime, String endTime) {
        String time = DateUtil.dateDiff(startTime, endTime, DateUtil.FULL_TIME_SPLIT_PATTERN, "h");
        return Body.newInstance(time);
    }


    @RequestMapping("test1")
    public void getTradeno() {
//        long up = 0;
//        long down = 0;
//        long other = 0;
//        Map<String, Long> map = new HashMap<>();
//        List<DeviceOnlineRecord> deviceOnlineRecords = deviceOnlineRecordMapper.selectOnlineRecord(number, time);
//        List<CarStatusChangeRecord> carStatusChangeRecords = carStatusChangeRecordMapper.selectCarStatusRecord(number
//                , time);
//        CarInfo carInfo = carInfoMapper.selectCarOnly(number);
//            if (carStatusChangeRecords.size() > 0) {
//                String temp = day;
//                for (CarStatusChangeRecord carStatusChangeRecord : carStatusChangeRecords) {
//                    if (!carInfo.getCarServiceStatus().equals(0)){
//                        other += DateUtil.datetime(temp, carStatusChangeRecord.getModifyDate(),
//                                DateUtil.FULL_TIME_SPLIT_PATTERN, "m");
//                        temp = carStatusChangeRecord.getModifyDate();
//                    }
//
//                }
//                other += DateUtil.datetime(temp, DateUtil.getDateFormat(new Date(), DateUtil.FULL_TIME_SPLIT_PATTERN),
//                        DateUtil.FULL_TIME_SPLIT_PATTERN, "m");
//            } else {
//                other += DateUtil.datetime(day, DateUtil.getDateFormat(new Date(), DateUtil.FULL_TIME_SPLIT_PATTERN),
//                        DateUtil.FULL_TIME_SPLIT_PATTERN, "m");
//            }
//        if (deviceOnlineRecords.size() > 0) {
//            String temp = day;
//            for (DeviceOnlineRecord deviceOnlineRecord : deviceOnlineRecords) {
//                if (deviceOnlineRecord.getStatus().equals(2)) {
//                    up += DateUtil.datetime(temp, deviceOnlineRecord.getCreateDate(),
//                            DateUtil.FULL_TIME_SPLIT_PATTERN, "m");
//                    temp = deviceOnlineRecord.getCreateDate();
//                } else {
//                    down += DateUtil.datetime(temp, deviceOnlineRecord.getCreateDate(), DateUtil
//                    .FULL_TIME_SPLIT_PATTERN
//                            , "m");
//                    temp = deviceOnlineRecord.getCreateDate();
//                }
//            }
//            if (ListUtils.getLastElement(deviceOnlineRecords).getStatus().equals(2)) {
//                down += DateUtil.datetime(temp, DateUtil.getDateFormat(new Date(), DateUtil.FULL_TIME_SPLIT_PATTERN),
//                        DateUtil.FULL_TIME_SPLIT_PATTERN, "m");
//            } else {
//                up += DateUtil.datetime(temp, DateUtil.getDateFormat(new Date(), DateUtil.FULL_TIME_SPLIT_PATTERN),
//                        DateUtil.FULL_TIME_SPLIT_PATTERN, "m");
//            }
//        } else {
//            DeviceOnlineRecord deviceOnlineRecord = deviceOnlineRecordMapper.selectOnlineRecordOnly();
//            if (StringUtils.isEmpty(deviceOnlineRecord)) {
//                up += DateUtil.datetime(day, DateUtil.getDateFormat(new Date(), DateUtil.FULL_TIME_SPLIT_PATTERN),
//                        DateUtil.FULL_TIME_SPLIT_PATTERN, "m");
//            } else {
//                if (deviceOnlineRecord.getStatus().equals(1)) {
//                    up += DateUtil.datetime(day, DateUtil.getDateFormat(new Date(), DateUtil.FULL_TIME_SPLIT_PATTERN),
//                            DateUtil.FULL_TIME_SPLIT_PATTERN, "m");
//                } else {
//                    down += DateUtil.datetime(day, DateUtil.getDateFormat(new Date(), DateUtil
//                    .FULL_TIME_SPLIT_PATTERN),
//                            DateUtil.FULL_TIME_SPLIT_PATTERN, "m");
//                }
//            }
//        }
//        map.put("up", up);
//        map.put("down", down);
//        map.put("other", other);
//        return Body.newInstance(map);
    }

    @RequestMapping("test")
    public Body test() {
        List<EChatBean3> eChatBean3sGps = deviceAlarmSeverityMapper.selectEChat1(null,
                null, null, null, "A", "离线告警");
        List<EChatBean3> eChatBean3sOther = deviceAlarmSeverityMapper.selectEChat1(null,
                null, null, null, null, "超速告警");
        List<EChatBean3> eChatBean3sMuck = deviceAlarmSeverityMapper.selectEChat1(null,
                null, null, null, null, "无准运证行驶");
        Integer count = eChatBean3sGps.size() + eChatBean3sOther.size() + eChatBean3sMuck.size();
        if (count.equals(AlarmCount)) {
            return Body.newInstance(0);
        } else {
            AlarmCount = count;
            return Body.newInstance(1);
        }
    }


    /*** 
     * @Description: 在线历史纪录
     * @Param: [number, startTime, endTime]
     * @return: com.example.car.common.utils.json.Body
     * @Author: 冷酷的苹果
     * @Date: 2020/8/11 13:11
     */
    @RequestMapping("onlineHistory")
    public Body onlineHistory(String number, String startTime, String endTime) {
        List<EChatBean> list = new ArrayList<>();
        DeviceLasposition deviceLasposition = deviceLaspositionMapper.selectLaspositionByCarNo(number);
        CarInfo carInfo = carInfoMapper.selectCarOnly(number);
        List<EChatBean> deviceOnlineRecords = deviceOnlineRecordMapper.selectEChat(number, startTime, endTime);
        for (EChatBean deviceOnlineRecord : deviceOnlineRecords) {
            if (deviceOnlineRecord.getStatus().equals(1)) {
                deviceOnlineRecord.setType("上线");
            } else {
                deviceOnlineRecord.setType("下线");
            }
        }
        List<EChatBean> carStatusChangeRecords = carStatusChangeRecordMapper.selectEChat(number, startTime, endTime);
        if (carInfo.getCarServiceStatus() != 0 && carStatusChangeRecords.size() <= 0) {
            EChatBean eChatBean = new EChatBean();
            eChatBean.setType("报备");
            eChatBean.setEndTime(endTime);
            eChatBean.setCreateDate(startTime);
            list.add(eChatBean);
        } else {
            for (EChatBean carStatusChangeRecord : carStatusChangeRecords) {
                if (carStatusChangeRecord.getStatus().equals(0)) {
                    carStatusChangeRecord.setType("正常");
                    if (deviceLasposition.getCarstatus() == 1 || deviceLasposition.getCarstatus() == 2) {
                        carStatusChangeRecord.setNowStatus("下线");
                    } else {
                        carStatusChangeRecord.setNowStatus("上线");
                    }
                } else {
                    carStatusChangeRecord.setType("报备");
                }
            }
            list.addAll(deviceOnlineRecords);
            list.addAll(carStatusChangeRecords);
            if (list.size() <= 0) {
                EChatBean eChatBean = new EChatBean();
                if (carInfo.getCarServiceStatus() == 0) {
                    if (!StringUtils.isEmpty(deviceLasposition)) {
                        if (deviceLasposition.getCarstatus() == 1 || deviceLasposition.getCarstatus() == 2) {
                            eChatBean.setNowStatus("下线");
                        } else {
                            eChatBean.setNowStatus("上线");
                        }
                        list.add(eChatBean);
                    }
                } else {
                    eChatBean.setType("报备");
                    list.add(eChatBean);
                }
            }
        }

        if (list.size() != 1) {
            for (int i = 0; i < list.size(); i++) {
                if (i == list.size() - 1) {
                    list.get(list.size() - 1).setEndTime(endTime);
                } else {
                    list.get(i).setEndTime(list.get(i + 1).getCreateDate());
                }
            }
            EChatBean eChatBean = new EChatBean();
            eChatBean.setCreateDate(startTime);
            eChatBean.setEndTime(list.get(0).getCreateDate());
            if (!StringUtils.isEmpty(list.get(0).getStatus())) {
                if (list.get(0).getStatus() == 1) {
                    eChatBean.setType("下线");
                    eChatBean.setStatus(2);
                } else {
                    eChatBean.setType("上线");
                    eChatBean.setStatus(1);
                }
            }
            list.add(0, eChatBean);
        } else {
            list.get(0).setCreateDate(startTime);
            list.get(0).setEndTime(endTime);
        }
        list.sort((o1, o2) -> Integer.compare(o2.getCreateDate().compareTo(o1.getCreateDate()), 0));
        return Body.newInstance(list);
    }


    /**
     * @Description: 报警信息柱状图
     * @Param: []
     * @return: com.example.car.common.utils.json.Body
     * @Author: 冷酷的苹果
     * @Date: 2020/8/5 18:08
     */
    @RequestMapping("EChat")
    public Body EChat() {
        List<SysAuthDept> deptList = sysAuthDeptMapper.selectSysAuthDeptByParent(new Long("722445496500748288"));
        List<EChatBean1> eChatBean1s = new ArrayList<>();
        for (SysAuthDept sysAuthDept : deptList) {
            List<EChatBean3> eChatBean3sGps = deviceAlarmSeverityMapper.selectEChat1(null,
                    null, null, sysAuthDept.getDeptid().toString(), "A", null);
//            List<EChatBean3> eChatBean3sOther = deviceAlarmSeverityMapper.selectEChat1(null,
//                    1, null, sysAuthDept.getDeptid().toString(), null, null);
//            List<EChatBean3> eChatBean3sMuck = deviceAlarmSeverityMapper.selectEChat1(null,
//                    1, null, sysAuthDept.getDeptid().toString(), null, "无准运证行驶");
//            eChatBean3sGps.addAll(eChatBean3sOther);
//            eChatBean3sGps.addAll(eChatBean3sMuck);
            Set<String> str = new HashSet<>();
            for (EChatBean3 eChatBean3 : eChatBean3sGps) {
                str.add(eChatBean3.getCarnumber());
            }
            EChatBean1 eChatBean1 = new EChatBean1();
            eChatBean1.setDept(sysAuthDept.getDeptname());
            eChatBean1.setDeptid(sysAuthDept.getDeptid().toString());
            eChatBean1.setNumber(str.size());
            eChatBean1.setDelete(sysAuthDept.getIsDelete());
            eChatBean1s.add(eChatBean1);
        }
        return Body.newInstance(eChatBean1s);
    }

    /**
     * @Description: 车辆报警
     * @Param: [deptid, number]
     * @return: com.example.car.common.utils.json.Body
     * @Author: 冷酷的苹果
     * @Date: 2020/8/6 10:11
     */
    @RequestMapping("EChat1")
    public Body EChat1(Long deptid, String number) {
        List<EChatBean2> eChatBean2s = new ArrayList<>();
        List<DeviceLasposition> deviceLasposition =
                deviceLaspositionMapper.selectposition(deptid.toString(), number);
        for (DeviceLasposition deviceLaspositions : deviceLasposition) {
            List<String> alarm = new ArrayList<>();
            EChatBean2 eChatBean2 = new EChatBean2();
            eChatBean2.setNumber(deviceLaspositions.getCarnumber());
            if (deviceLaspositions.getCarstatus() == 2 || deviceLaspositions.getCarstatus() == 1) {
                DeviceAlarmSeverity deviceAlarmSeverity =
                        deviceAlarmSeverityMapper.selectAlarmSeverityTask(null,
                                deviceLaspositions.getCarnumber(), null, "离线告警", "A");
                if (!StringUtils.isEmpty(deviceAlarmSeverity)) {
                    alarm.add("离线告警");
                }
            }
            List<DeviceAlarmSeverity> deviceAlarmSeverities =
                    deviceAlarmSeverityMapper.selectAlarmMuck(deviceLaspositions.getCarnumber(), "无准运证行驶");
            if (deviceAlarmSeverities.size() > 0) {
                alarm.add("无准运证行驶");
            }
            List<DeviceAlarmSeverity> deviceAlarmSeverity1 =
                    deviceAlarmSeverityMapper.selectAlarmMuck(deviceLaspositions.getCarnumber(), "超速告警");
            if (deviceAlarmSeverity1.size() > 0) {
                alarm.add("超速行驶");
            }
            eChatBean2.setAlarm(alarm);
            eChatBean2s.add(eChatBean2);
        }
        return Body.newInstance(eChatBean2s);
    }

    /**
     * @Description: 车辆所有报警
     * @Param: [number]
     * @return: com.example.car.common.utils.json.Body
     * @Author: 冷酷的苹果
     * @Date: 2020/8/6 17:45
     */
    @RequestMapping("EChat2")
    public Body EChat2(String number, Integer time, String type, String deptid) {
        List<EChatBean3> eChatBean3sGps = deviceAlarmSeverityMapper.selectEChat1(number,
                time, type, deptid, null, "离线告警");
        List<EChatBean3> eChatBean3sOther = deviceAlarmSeverityMapper.selectEChat1(number,
                time, type, deptid, null, "超速告警");
        List<EChatBean3> eChatBean3sMuck = deviceAlarmSeverityMapper.selectEChat1(number,
                time, type, deptid, null, "无准运证行驶");
        eChatBean3sGps.addAll(eChatBean3sOther);
        eChatBean3sGps.addAll(eChatBean3sMuck);
        return Body.newInstance(eChatBean3sGps);
    }

    /**
     * @Description: 所有车队的车辆情况
     * @Param:
     * @return: com.example.car.common.utils.json.Body
     * @Author: 冷酷的苹果
     * @Date: 2020/8/12 14:03
     */
    @RequestMapping("CarDetail")
    public Body CarDetail() {
        List<EChatBean4> eChatBean4s = new ArrayList<>();
        List<SysAuthDept> deptList = sysAuthDeptMapper.selectSysAuthDeptByParent(new Long("722445496500748288"));
        for (SysAuthDept sysAuthDept : deptList) {
            Integer up = 0;
            Integer down = 0;
            Integer other = 0;
            EChatBean4 eChatBean4 = new EChatBean4();
            eChatBean4.setDeptid(sysAuthDept.getDeptid().toString());
            eChatBean4.setName(sysAuthDept.getDeptname());
            List<Map<String, Object>> list = carInfoMapper.selectCarDetail(sysAuthDept.getDeptid().toString(), null,
                    null, null);
            for (Map<String, Object> objectMap : list) {
                if (!(objectMap.get("carstatus").equals(1) || objectMap.get("carstatus").equals(2)) && objectMap.get(
                        "car_service_status").equals(0)) {
                    up++;
                } else if ((objectMap.get("carstatus").equals(1) || objectMap.get("carstatus").equals(2)) && objectMap.get("car_service_status").equals(0)) {
                    down++;
                } else {
                    other++;
                }
            }
            eChatBean4.setUp(up);
            eChatBean4.setDown(down);
            eChatBean4.setOther(other);
            eChatBean4s.add(eChatBean4);
        }
        return Body.newInstance(eChatBean4s);
    }
}
