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
import com.example.car.common.utils.HttpUtils;
import com.example.car.common.utils.Md5Util;
import com.example.car.common.utils.json.Body;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private final static String username = "admin";
    private final static String tradeno = "20180908180001";
    private final static String url = "http://101.132.236.6:8088/";

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
        String result = HttpUtils.httpPostRaw(address, json, null, "UTF-8");
        return result;
    }


    /**
     * @Description: 报警信息处理
     * @Param: []
     * @return: com.example.car.common.utils.json.Body
     * @Author: 冷酷的苹果
     * @Date: 2020/6/16 12:00
     */
    @RequestMapping("alertProcessing")
    public Body alertProcessing(String carnumber, String startTime, String page, String size, Integer level,
                                String endTime) {
        List<Map<String, Object>> processingOne = new ArrayList<>();
        List<Map<String, Object>> processingTwo = new ArrayList<>();
        String address = url + "cmsapi/queryAlarmList";
        String sign = Md5Util.MD5EncodeUtf8(username + "12345620180908180001");
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
        if (list.size() > 0 && !StringUtils.isEmpty(level)) {
            for (Map<String, Object> stringObjectMap : list) {
                if (!StringUtils.isEmpty(stringObjectMap.get("alarmNumber")) &&
                        (int) stringObjectMap.get("alarmNumber") < 105 && level == 1) {
                    processingOne.add(stringObjectMap);
                } else {
                    processingTwo.add(stringObjectMap);
                }

            }
        } else {
            return Body.newInstance(list);
        }
        if (level == 1) {
            return Body.newInstance(processingOne);
        } else {
            return Body.newInstance(processingTwo);
        }

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
        String sign = Md5Util.MD5EncodeUtf8(username + "12345620180908180001");
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
        String sign = Md5Util.MD5EncodeUtf8(username + "12345620180908180001");
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

}
