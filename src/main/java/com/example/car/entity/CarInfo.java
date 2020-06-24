package com.example.car.entity;

import lombok.Data;

import java.util.Date;

/**
 * 车辆信息表 Entity
 *
 * @author 冷酷的苹果
 * @date 2020-06-18 08:54:02
 */
@Data
public class CarInfo {

    /**
     * id
     */
    private Long id;

    /**
     * 组织机构
     */
    private Long deptid;

    /**
     * 车牌号
     */
    private String carnumber;

    /**
     * 省
     */
    private String province;

    /**
     * 市
     */
    private String city;

    /**
     * 县/区
     */
    private String district;

    /**
     * 车辆类型 id
     */
    private String cartype;

    /**
     * 备注
     */
    private String remark;

    /**
     * 1 蓝色 2 黄色 3 黑色 4 白色 5红色6紫色9 其他
     */
    private Integer color;

    /**
     * 发动机号
     */
    private String engineint;

    /**
     * 车架号
     */
    private String vin;

    /**
     * 服务终止日期
     */
    private Date insurancetime;

    /**
     * 安装日期
     */
    private Date installtime;

    /**
     * 登记日期
     */
    private Date registertime;

    /**
     * 车辆用途
     */
    private Long caruse;

    /**
     * 业户名称
     */
    private String bk1;

    /**
     * 车辆年审日期
     */
    private String bk2;

    /**
     *
     */
    private String bk3;

    /**
     *
     */
    private String bk4;

    /**
     *
     */
    private String bk5;

    /**
     * 0 正常 1 删除
     */
    private Integer isDelete;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 最后一次修改时间
     */
    private Date modifyDate;

    /**
     * 修改记录用户
     */
    private String modifyUser;

    /**
     * 年审日期
     */
    private String inspectDate;

    /**
     * 车主姓名
     */
    private String owerName;

    /**
     * 联系人
     */
    private String owerContact;

    /**
     * 联系人电话
     */
    private String owerPhone;

    /**
     * 联系人地址
     */
    private String owerAddress;

    /**
     * 车主身份证号
     */
    private String owerIdentity;

    /**
     * 从业资格证
     */
    private String owerCertificate;

    /**
     * 押运员姓名
     */
    private String supercargoName;

    /**
     * 押运员电话
     */
    private String supercargoPhone;

    /**
     * 押运员身份证
     */
    private String supercargoIdentity;

    /**
     * 押运员从业资格证
     */
    private String supercargoCertificate;

    /**
     * 服务开始时间
     */
    private String serviceStart;

    /**
     * 服务到期时间
     */
    private String serviceEnd;

    /**
     * 行驶证号
     */
    private String vehicleLicense;

    /**
     * 运输证号
     */
    private String transportNumber;

    /**
     * 设备ID
     */
    private Long terminalId;

    /**
     * 车辆营运状态
     */
    private Integer carServiceStatus;

    /**
     * 经营路线id
     */
    private Integer carRouteId;

    /**
     * 吨位
     */
    private String tonnage;

    /**
     * 人数
     */
    private String personnum;

    /**
     * 运输行业编码
     */
    private String transtype;

    /**
     * 经营范围编码
     */
    private String bussinesscode;

    /**
     *
     */
    private String deptidnew;

}
