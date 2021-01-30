/**
 * Copyright (C), 2020-2021, 众马科技有限公司
 * FileName: StopTransport
 * Author:   冷酷的苹果
 * Date:     2021/1/28 15:18
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.example.car.task.stoptransport;

import com.example.car.mapper.sqlserver.M03Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 冷酷的苹果
 * @create 2021/1/28
 * @since 1.0.0
 */
@Component                //实例化
@Configurable             //注入bean
@EnableScheduling         //开启计划任务
public class StopTransport {

    @Autowired
    private M03Mapper m03Mapper;

    /**
     * @ Description: 还原停运车辆
     * @ Param: []
     * @ return: void
     * @ Author: 冷酷的苹果
     * @ Date: 2021/1/28 16:28
     */
    @Scheduled(cron = " 0 0 0 * * ? ")
    private void stopTransport() {
            m03Mapper.updateTransportAuto("0",null,null);
    }
}
