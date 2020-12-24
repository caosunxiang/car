/**
 * Copyright (C), 2020-2020, 众马科技有限公司
 * FileName: 同步历史轨迹
 * Author:   冷酷的苹果
 * Date:     2020/12/21 9:08
 * Description: 同步历史轨迹
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.example.car.task.track;

import com.alibaba.fastjson.JSON;
import com.example.car.common.utils.DateUtil;
import com.example.car.common.utils.HttpUtils;
import com.example.car.entity.DeviceLasposition;
import com.example.car.entity.HistoricalRoute;
import com.example.car.entity.SysAuthDept;
import com.example.car.mapper.mysql.DeviceLaspositionMapper;
import com.example.car.mapper.mysql.HistoricalRouteMapper;
import com.example.car.mapper.mysql.SysAuthDeptMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br>
 * 〈同步历史轨迹〉
 *
 * @author 冷酷的苹果
 * @create 2020/12/21
 * @since 1.0.0
 */
@Component                //实例化
@Configurable             //注入bean
@EnableScheduling         //开启计划任务
public class synchronousTrack {

    @Autowired
    private SysAuthDeptMapper sysAuthDeptMapper;
    @Autowired
    private DeviceLaspositionMapper deviceLaspositionMapper;
    @Autowired
    private HistoricalRouteMapper historicalRouteMapper;

    /**
     * @ Description: 同步历史轨迹
     * @ Param: []
     * @ return: void
     * @ Author: 冷酷的苹果
     * @ Date: 2020/9/21 8:38
     */
    @Scheduled(cron = " 0 0 0 * * ? ")
    public void historicalRoute() {
        List<SysAuthDept> deptList = sysAuthDeptMapper.selectSysAuthDeptByParent(new Long("722445496500748288"));
        List<DeviceLasposition> list = new ArrayList<>();
        for (SysAuthDept sysAuthDept : deptList) {
            List<DeviceLasposition> deviceLasposition =
                    deviceLaspositionMapper.selectLaspositionAlarm(sysAuthDept.getDeptid().toString());
            list.addAll(deviceLasposition);
        }
        for (DeviceLasposition s : list) {
            Map<String, String> map = new HashMap<>();
            map.put("carnumber", s.getCarnumber());
            map.put("tradeno", "20180908180001");
            map.put("startTime", DateUtil.timeVariousTypes(2));
            map.put("endTime", DateUtil.timeVariousTypes(1));
            map.put("username", "yccgj");
            map.put("sign", "4DEB2D8E22EDB820A88FBFE2F597E086");
            String json = JSON.toJSONString(map);
            String address = "http://101.132.236.6:8088/cmsapi/getHistoryTrack";
            String result = HttpUtils.doJsonPost(address, json);
            List<HistoricalRoute> resultData = JSON.parseArray(JSON.parseObject(result).getString("resultData"),
                    HistoricalRoute.class);
            if (resultData.size() > 0) {
                historicalRouteMapper.insertHistoricalRoute(resultData);
            }
        }
    }


    /**
     * @ Description: 同步历史轨迹
     * @ Param: []
     * @ return: void
     * @ Author: 冷酷的苹果
     * @ Date: 2020/9/21 8:38
     */
    @Scheduled(cron = " 0 0 3 * * ? ")
    public void delhistoricalRoute() {
        this.historicalRouteMapper.delHistoricalRoute(DateUtil.severalDaysAgo(DateUtil.FULL_TIME_SPLIT_PATTERN, 15));
    }

}
