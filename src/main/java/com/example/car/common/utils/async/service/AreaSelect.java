/**
 * Copyright (C), 2020-2020, 众马科技有限公司
 * FileName: AreaSelect
 * Author:   冷酷的苹果
 * Date:     2020/7/21 9:20
 * Description: 区域查车
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.example.car.common.utils.async.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.car.common.utils.*;
import com.example.car.common.utils.json.Body;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * 〈一句话功能简述〉<br>
 * 〈区域查车〉
 *
 * @author 冷酷的苹果
 * @create 2020/7/21
 * @since 1.0.0
 */
@Service
public class AreaSelect {
    private static final Logger logger = LoggerFactory.getLogger(AreaSelect.class);

    // 这里进行标注为异步任务，在执行此方法的时候，会单独开启线程来执行(并指定线程池的名字)
    @Async("area")
    public CompletableFuture<List<Map<String, String>>> areaSelect(Double lat1, Double lng1, String startTime,
                                                                   String endTime, List<String> numbers) {
        List<Map<String, String>> carInArea = new ArrayList<>();
        for (String s : numbers) {
            logger.info("Looking up " + s);
            Map<String, String> map = new HashMap<>();
            map.put("carnumber", s);
            map.put("tradeno", "20180908180001");
            map.put("startTime", startTime);
            map.put("endTime", endTime);
            map.put("username", "yccgj");
            map.put("sign", "4DEB2D8E22EDB820A88FBFE2F597E086");
            String json = JSON.toJSONString(map);
            String address = "http://101.132.236.6:8088/cmsapi/getHistoryTrack";
            String result = HttpUtils.doJsonPost(address, json);
            JSONObject jsonObject = JSONObject.parseObject(result);
            List<Map<String, Object>> resultData = (List<Map<String, Object>>) jsonObject.get("resultData");
            if (resultData.size() > 0) {
                for (Map<String, Object> resultDatum : resultData) {
                    Double lng = new Double(resultDatum.get("lng").toString());
                    Double lat = new Double(resultDatum.get("lat").toString());
                    boolean isIn = Distance.coordinateToDistance(lat1, lng1, lat, lng, 5000.00);
                    if (isIn) {
                        Map<String, String> objectMap = new HashMap<>();
                        String carnumber = resultDatum.get("carnumber").toString();
                        objectMap.put("carnumber", carnumber);
                        carInArea.add(objectMap);
                        break;
                    }
                }
            }


        }
        return CompletableFuture.completedFuture(carInArea);
    }
}
