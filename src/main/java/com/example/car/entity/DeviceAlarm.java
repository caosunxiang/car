package com.example.car.entity;

import lombok.Data;

import java.util.Date;

/**
 * 设备报警表 Entity
 *
 * @author 冷酷的苹果
 * @date 2020-06-18 10:52:07
 */
@Data
public class DeviceAlarm {

    /**
     *
     */

    private Long id;

    /**
     * 报警时长(秒)
     */
    private Long alarmDuration;

    /**
     * 报警信息
     */
    private String alarmInfo;

    /**
     * 报警级别
     */
    private Byte alarmLevel;

    /**
     * 报警类型编码
     */
    private Integer alarmNumber;

    /**
     * 报警来源(0:终端 1:平台)
     */
    private Byte alarmSource;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 报警结束报警列表,json字符串
     */
    private String endAlarmList;

    /**
     * 报警结束附加信息,json字符串
     */
    private String endExtraInfo;

    /**
     * 报警结束纬度
     */
    private Double endLat;

    /**
     * 结束位置
     */
    private String endLocation;

    /**
     * 报警结束经度
     */
    private Double endLon;

    /**
     * 报警结束速度
     */
    private Double endSpeed;

    /**
     * 报警结束状态列表,json字符串
     */
    private String endStatusList;

    /**
     * 报警结束时间
     */
    private Date endTime;

    /**
     * 处理人ID
     */
    private Long handleBy;

    /**
     * 处理内容
     */
    private String handleContent;

    /**
     * 处理时间
     */
    private Date handleTime;

    /**
     * 是否删除(0:正常 1:删除)
     */
    private Byte isDelete;

    /**
     * 是否处理(0:未处理,1:已处理)
     */
    private String isHandle;

    /**
     * 最后修改时间
     */
    private Date modifyDate;

    /**
     * 报警开始报警列表,json字符串
     */
    private String startAlarmList;

    /**
     * 报警开始附加信息,json字符串
     */
    private String startExtraInfo;

    /**
     * 报警开始纬度
     */
    private Double startLat;

    /**
     * 开始位置
     */
    private String startLocation;

    /**
     * 报警开始经度
     */
    private Double startLon;

    /**
     * 报警开始速度
     */
    private Double startSpeed;

    /**
     * 报警开始状态列表,json字符串
     */
    private String startStatusList;

    /**
     * 报警开始时间
     */
    private Date startTime;

    /**
     * 报警开始时间戳
     */
    private Long startTimestamp;

    /**
     * 终端ID
     */
    private String terminalId;

    /**
     * 司机ID
     */
    private Long driverId;

    /**
     *
     */
    private Long activeSafetyAlarmId;

}
