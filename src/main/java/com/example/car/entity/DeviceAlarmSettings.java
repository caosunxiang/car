package com.example.car.entity;

import lombok.Data;

import java.util.Date;

/**
 * 设备报警配置表 Entity
 *
 * @author 冷酷的苹果
 * @date 2020-06-18 10:51:07
 */
@Data
public class DeviceAlarmSettings {

    /**
     * 报警编号	用来报警的唯一识别号
     */
    private Integer alarmnumber;

    /**
     * 报警名称
     */
    private String alarmname;

    /**
     * 报警的类型	0：系统报警
     * 1：用户报警
     */
    private Integer type;

    /**
     * 是否是关键报警	对应于JT808-2013协议中的8103中的0x54 参数。
     */
    private Integer keyalarm;

    /**
     * 报警大类
     * 1：原JT808平台定义的报警
     * 2：JT1078平台报警
     * 3：JT905特有的报警
     * 4：主动安全报警
     * 5：自定义报警
     */
    private Integer alarmClass;

    /**
     * 报警大类名称
     */
    private String alarmClassName;

    /**
     * 报警小类编号
     */
    private Integer minclass;

    /**
     * 报警小类名称
     */
    private String minclassName;

    /**
     * 报警附加说明
     */
    private String bk1;

    /**
     *
     */
    private String bk2;

    /**
     *
     */
    private String bk3;

    /**
     *
     */
    private Date createtime;

}
