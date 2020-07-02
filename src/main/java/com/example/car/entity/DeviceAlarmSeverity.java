package com.example.car.entity;

import lombok.Data;

import java.util.Date;

/**
 * 设备严重报警表 Entity
 *
 * @author 冷酷的苹果
 * @date 2020-06-18 10:52:07
 */
@Data
public class DeviceAlarmSeverity {

    /**
     *严重报警id
     */

    private Integer alarmId;

    /**
     * 报警名称
     */
    private String alarmName;

    /**
     * 报警车牌号
     */
    private String carNumber;

    /**
     * 报警开始时间
     */
    private String alarmStartTime;



    /**
     * 报警开始时间
     */
    private String alarmStartSpeed;

    /**
     * 报警纬度
     */
    private String alarmLat;

    /**
     * 报警经度
     */
    private String alarmLng;



}
