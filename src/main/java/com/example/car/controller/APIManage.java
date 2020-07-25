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
import com.example.car.common.utils.json.Body;
import com.example.car.entity.CarInfo;
import com.example.car.entity.CarMileage;
import com.example.car.entity.SysAuthDept;
import com.example.car.mapper.mysql.*;
import com.example.car.mapper.sqlserver.MuckMapper;
import com.example.car.task.Task;
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
    @Autowired
    private AreaSelect areaSelect;
    @Autowired
    private CarMileageMapper carMileageMapper;

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
                           String numbers) throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        List<String> list = CutString.divide(numbers);
        List<List<String>> lists = ListUtils.averageAssign(list, 6);
        List<Map<String, String>> mapList = new ArrayList<>();
        CompletableFuture<List<Map<String, String>>> page1 = areaSelect.areaSelect(lat1, lng1, lat2, lng2, startTime,
                endTime, lists.get(0));
        CompletableFuture<List<Map<String, String>>> page2 = areaSelect.areaSelect(lat1, lng1, lat2, lng2, startTime,
                endTime, lists.get(1));
        CompletableFuture<List<Map<String, String>>> page3 = areaSelect.areaSelect(lat1, lng1, lat2, lng2, startTime,
                endTime, lists.get(2));
        CompletableFuture<List<Map<String, String>>> page4 = areaSelect.areaSelect(lat1, lng1, lat2, lng2, startTime,
                endTime, lists.get(3));
        CompletableFuture<List<Map<String, String>>> page5 = areaSelect.areaSelect(lat1, lng1, lat2, lng2, startTime,
                endTime, lists.get(4));
        CompletableFuture<List<Map<String, String>>> page6 = areaSelect.areaSelect(lat1, lng1, lat2, lng2, startTime,
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

    @RequestMapping("test")
    public void getTradeno() {
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
                if (StringUtils.isEmpty(resultDatum.get("mileage"))) {
                    carMileage1.setCarName(resultDatum.get("carnumber").toString());
                    carMileage1.setCarMileage(0.0);
                    carMileage1.setCarMileageToday(0.0);
                    carMileageMapper.insertCarMileage(carMileage1);
                }else {
                    carMileage1.setCarName(resultDatum.get("carnumber").toString());
                    carMileage1.setCarMileage(new Double(resultDatum.get("mileage").toString()));
                    carMileage1.setCarMileageToday(0.0);
                    carMileageMapper.insertCarMileage(carMileage1);
                }
            } else {
                if (StringUtils.isEmpty(carMileage.getCarMileage())) {
                    carMileage.setCarMileage(0.0);
                }
                carMileage.setCarMileageToday(Double.parseDouble(resultDatum.get("mileage").toString()) - carMileage.getCarMileage());
                carMileage.setCarMileage(new Double(resultDatum.get("mileage").toString()));
                carMileageMapper.updateCarMileage(carMileage);
            }
        }
    }
}
