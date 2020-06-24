package com.example.car.entity;

import java.util.Date;

import lombok.Data;

/**
 * 公司机构表 Entity
 *
 * @author 冷酷的苹果
 * @date 2020-06-17 10:45:17
 */
@Data
public class SysAuthDept {

    private Long deptid;

    /**
     * 机构名称
     */
    private String deptname;

    /**
     * 联系人
     */
    private String contract;

    /**
     * 电话
     */
    private String tel;

    /**
     * 地址
     */
    private String address;

    /**
     * 操作用户ID
     */
    private Long userid;

    /**
     * 父级ID
     */
    private Long parentid;

    /**
     * 机构车辆总数	包括子机构数量
     */
    private Integer total;

    /**
     * 机构车辆离线总数	包括子机构数量
     */
    private Integer caroffline;

    /**
     * 机构车辆长离总数	包括子机构数量
     */
    private Integer longoffline;

    /**
     * 备注
     */
    private String remark;

    /**
     * 删除标志（0未删除,1已删除）
     */
    private Integer isDelete;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 创建用户
     */
    private String createUser;

    /**
     * 修改时间
     */
    private Date modifyDate;

    /**
     * 修改用户
     */
    private String modifyUser;

    /**
     * 统一信用代码
     */
    private String identityNumber;

    /**
     * 最大挂车数量
     */
    private Integer maxCarNum;

    /**
     * 机构类别
     */
    private Integer depClass;

    /**
     * 业户ID
     */
    private Long ownerid;

    /**
     * 新部门id
     */
    private String deptidnew;

}
