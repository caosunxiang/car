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
import com.example.car.common.utils.DateUtil;
import com.example.car.common.utils.Distance;
import com.example.car.common.utils.HttpUtils;
import com.example.car.common.utils.Md5Util;
import com.example.car.common.utils.json.Body;
import com.example.car.entity.CarInfo;
import com.example.car.entity.DeviceAlarmSeverity;
import com.example.car.entity.SysAuthDept;
import com.example.car.mapper.mysql.*;
import com.example.car.mapper.sqlserver.MuckMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.*;

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

    private final static String username = "yccgj";
    private final static String tradeno = "20180908180001";
    private final static String url = "http://101.132.236.6:8088/";
    @Autowired
    private CarPictureMapper carPictureMapper;
    @Autowired
    private CarInfoMapper carInfoMapper;
    @Autowired
    private SysAuthDeptMapper sysAuthDeptMapper;
    @Autowired
    private MuckMapper muckMapper;
    @Autowired
    private DeviceAlarmSeverityMapper deviceAlarmSeverityMapper;
    @Autowired
    private DeviceAlarmMapper deviceAlarmMapper;

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
    public Body selectAlarmAll(String startTime, String endTime,String number,Integer size,Integer type) {
        Long id=new Long("722445496500748288");
        if (StringUtils.isEmpty(size)){
            size=250;
        }
        List<Map<String, Object>> list=new ArrayList<>();
        List<SysAuthDept> deptList=sysAuthDeptMapper.selectSysAuthDeptByParent(id);
        for (SysAuthDept sysAuthDept : deptList) {
            list.addAll(this.deviceAlarmMapper.selectAlarm(number,startTime,endTime,sysAuthDept.getDeptid(),size,type)) ;
        }
        List<Map<String, Object>> list1 = deviceAlarmSeverityMapper.selectAlarmSeverityAll(startTime, endTime,number,size);
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
    public Body selectHome(String json, Double lat, Double lng, Double distance) {
        List<Map<String, Object>> maps = new ArrayList<>();
        String address = url + "cmsapi/getTerminalGpsStatus";
        String result = HttpUtils.doJsonPost(address, json);
        JSONObject jsonObject = JSONObject.parseObject(result);
        List<Map<String, Object>> resultData = (List<Map<String, Object>>) jsonObject.get("resultData");
        if (resultData.size() > 0) {
            for (Map<String, Object> resultDatum : resultData) {
                Double latCar = Double.valueOf(resultDatum.get("lat").toString());
                Double lngCar = Double.valueOf(resultDatum.get("lng").toString());
                boolean isIn = Distance.coordinateToDistance(lat, lng, latCar, lngCar, distance);
                if (!isIn) {
                    CarInfo carInfo = carInfoMapper.selectCarOnly(resultDatum.get("carnumber").toString());
                    String picture = carPictureMapper.selectCarPicture(carInfo.getCartype());
                    Long deptid = carInfo.getDeptid();
                    SysAuthDept sysAuthDept = sysAuthDeptMapper.selectSysAuthDeptById(deptid);
                    resultDatum.put("picture", picture);
                    resultDatum.put("dept", sysAuthDept.getDeptname());
                    maps.add(resultDatum);
                }
            }
        }
        return Body.newInstance(maps);
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
        String terminal = null;
        Map<String, String> map = new HashMap<>();
        map.put("carnumber", number);
        map.put("tradeno", tradeno);
        map.put("username", username);
        String sign = Md5Util.MD5EncodeUtf8(username + "admin12320180908180001");
        System.out.println(sign);
        map.put("sign", sign);
        String json = JSON.toJSONString(map);
        String address = url + "cmsapi/getTerminalList";
        String result = HttpUtils.doJsonPost(address, json);
        JSONObject jsonObject = JSONObject.parseObject(result);
        List<Map<String, Object>> resultData = (List<Map<String, Object>>) jsonObject.get("resultData");
        if (resultData.size() > 0) {
            terminal = resultData.get(0).get("terminal").toString();
        }
        System.out.println(terminal);
        Map<String, String> map1 = new HashMap<>();
        map1.put("terminal", terminal);
        map1.put("tradeno", tradeno);
        map1.put("username", username);
        String sign1 = Md5Util.MD5EncodeUtf8(username + "admin12320180908180001");
        System.out.println(sign1);
        map1.put("sign", sign1);
        String json1 = JSON.toJSONString(map1);
        String address1 = url + "cmsapi/getTerminalGpsStatus";
        String result1 = HttpUtils.doJsonPost(address1, json1);
        JSONObject jsonObject1 = JSONObject.parseObject(result1);
        List<Map<String, Object>> resultData1 = (List<Map<String, Object>>) jsonObject1.get("resultData");
        if (jsonObject1.get("errCode").equals(500)) {
            return Body.newInstance(jsonObject1.get("resultMsg"));
        }
        if (resultData1.size() > 0) {
            for (Map<String, Object> stringObjectMap : resultData1) {
                CarInfo carInfo = carInfoMapper.selectCarOnly(stringObjectMap.get("carnumber").toString());
                Long deptid = carInfo.getDeptid();
                SysAuthDept sysAuthDept = sysAuthDeptMapper.selectSysAuthDeptById(deptid);
                stringObjectMap.put("dept", sysAuthDept.getDeptname());
            }
        }
        return Body.newInstance(resultData1);
    }

    /**
     * @Description: 区域查车
     * @Param: [lat1, lng1, lat2, lng2, startTime, endTime, terminals]
     * @return: com.example.car.common.utils.json.Body
     * @Author: 冷酷的苹果
     * @Date: 2020/7/2 9:32
     */
    @RequestMapping("areaSelect")
    public Body areaSelect(Double lat1, Double lng1, Double lat2, Double lng2, String startTime, String endTime,
                           String numbers) {
        List<String> list = divide(numbers);
        List<Map<String, String> >carInArea = new ArrayList<>();
        for (String s : list) {
            Map<String, String> map = new HashMap<>();
            map.put("carnumber", s);
            map.put("tradeno", tradeno);
            map.put("startTime", startTime);
            map.put("endTime", endTime);
            map.put("username", username);
            String sign = Md5Util.MD5EncodeUtf8(username + "admin12320180908180001");
            System.out.println(sign);
            map.put("sign", sign);
            String json = JSON.toJSONString(map);
            String address = url + "cmsapi/getHistoryTrack";
            String result = HttpUtils.doJsonPost(address, json);
            JSONObject jsonObject = JSONObject.parseObject(result);
            List<Map<String, Object>> resultData = (List<Map<String, Object>>) jsonObject.get("resultData");
            if (resultData.size() > 0) {
                for (Map<String, Object> resultDatum : resultData) {
                    Double lng = new Double( resultDatum.get("lng").toString());
                    Double lat = new Double( resultDatum.get("lat").toString());
                    boolean isIn = isInArea(lat, lng, lat1, lat2, lng1, lng2);
                    if (isIn) {
                        Map<String,String> objectMap=new HashMap<>();
                        String carnumber = resultDatum.get("carnumber").toString();
                        objectMap.put("carnumber", carnumber);
                        carInArea.add(objectMap);
                        break;
                    }
                }
            }
        }
        return Body.newInstance(carInArea);
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


    /**
     *      *
     *      * @param latitue 待测点的纬度
     *      * @param longitude 待测点的经度
     *      * @param areaLatitude1 纬度范围限制1
     *      * @param areaLatitude2 纬度范围限制2
     *      * @param areaLongitude1 经度限制范围1
     *      * @param areaLongitude2 经度范围限制2
     *      * @return
     *     
     */
    public static boolean isInArea(double latitue, double longitude, double areaLatitude1, double areaLatitude2,
                                   double areaLongitude1, double areaLongitude2) {
        if (isInRange(latitue, areaLatitude1, areaLatitude2)) {//如果在纬度的范围内
            if (areaLongitude1 * areaLongitude2 > 0) {//如果都在东半球或者都在西半球
                if (isInRange(longitude, areaLongitude1, areaLongitude2)) {
                    return true;
                } else {
                    return false;
                }
            } else {//如果一个在东半球，一个在西半球
                if (Math.abs(areaLongitude1) + Math.abs(areaLongitude2) < 180) {//如果跨越0度经线在半圆的范围内
                    if (isInRange(longitude, areaLongitude1, areaLongitude2)) {
                        return true;
                    } else {
                        return false;
                    }
                } else {//如果跨越180度经线在半圆范围内
                    double left = Math.max(areaLongitude1, areaLongitude2);//东半球的经度范围left-180
                    double right = Math.min(areaLongitude1, areaLongitude2);//西半球的经度范围right-（-180）
                    if (isInRange(longitude, left, 180) || isInRange(longitude, right, -180)) {
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        } else {
            return false;
        }
    }


    public static boolean isInRange(double point, double left, double right) {
        if (point >= Math.min(left, right) && point <= Math.max(left, right)) {
            return true;
        } else {
            return false;
        }

    }

    public static List<String> divide(String msg) {
        List<String> list = new ArrayList<String>();
        msg = msg + ",";
        char a[] = msg.toCharArray();
        Integer c = 0;
        Integer changeCount = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] == ',') {
                String string = msg.substring(c, i);
                c = i + 1;
                System.out.println(string);
                list.add(string);
                changeCount++;
            }
        }
        return list;
    }
}
