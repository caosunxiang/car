package com.example.car.entity;

import com.baomidou.mybatisplus.annotation.TableField;
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
     * 严重报警id
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
     * 报警开始速度
     */
    private String alarmStartSpeed;
    /**
     * 报警结束时间
     */
    private String alarmEndTime;

    /**
     * 报警结束速度
     */
    private String alarmEndSpeed;

    /**
     * 报警纬度
     */
    private String alarmLat;

    /**
     * 报警经度
     */
    private String alarmLng;

    /**
     * 行驶里程
     */
    private String alarmMileage;

    /**
     * 报警结束纬度
     */
    private String alarmEndLat;

    /**
     * 报警结束经度
     */
    private String alarmEndLng;

    /**
     * 机构id
     */
    private Long deptid;

    /**
     * 处理人
     */
    private Long handleBy;

    /**
     * 处理状态
     */
    private Long handleType;

    /**
     * 处理时间
     */
    private Long handleTime;

    /**
     * 车队信息
     */
    @TableField(exist = false)
    private String deptname;
}
