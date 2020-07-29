package com.example.car.entity;

    import java.util.Date;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
* 设备最后位置表 Entity
*
* @author 冷酷的苹果
* @date 2020-07-29 10:31:06
*/
@Data
@TableName("device_last_position")
public class DeviceLasposition {

        /**
        * id
        */
            @TableId(value = "id", type = IdType.AUTO)
            private Long id;

        /**
        * 机构id
        */
            @TableField("deptid")
            private Long deptid;

        /**
        * 车辆id
        */
            @TableField("carid")
            private Long carid;

        /**
        * 设备ID
        */
            @TableField("terminal_id")
            private Long terminalId;

        /**
        * 司机ID
        */
            @TableField("driver_id")
            private Long driverId;

        /**
        * 终端号码
        */
            @TableField("terminal")
            private String terminal;

        /**
        * 车辆状态 1：长时间离线 2：离线 3：熄火 4：停车 5：行驶 6：报警 7：在线 8：未定位
        */
            @TableField("carstatus")
            private Integer carstatus;

        /**
        * 车牌号
        */
            @TableField("carnumber")
            private String carnumber;

        /**
        * 信息ID
        */
            @TableField("msgid")
            private Integer msgid;

        /**
        * 经度
        */
            @TableField("lng")
            private Double lng;

        /**
        * 纬度
        */
            @TableField("lat")
            private Double lat;

        /**
        * 0: ACC关;1:ACC开
        */
            @TableField("acc")
            private Integer acc;

        /**
        * 高度
        */
            @TableField("altitude")
            private String altitude;

        /**
        * 速度
        */
            @TableField("speed")
            private String speed;

        /**
        * 方向
        */
            @TableField("direction")
            private String direction;

        /**
        * GPS时间
        */
            @TableField("gpstime")
            private String gpstime;

        /**
        * GPS是否有效，0为无效,1为有效,2为上次信号有效时的位置
        */
            @TableField("gpsflag")
            private Integer gpsflag;

        /**
        * 备注信息
        */
            @TableField("remark")
            private String remark;

        /**
        * 地址
        */
            @TableField("address")
            private String address;

        /**
        * 省
        */
            @TableField("province")
            private String province;

        /**
        * 市
        */
            @TableField("city")
            private String city;

        /**
        * 县/区
        */
            @TableField("district")
            private String district;

        /**
        * 里程
        */
            @TableField("mileage")
            private String mileage;

        /**
        * 总里程
        */
            @TableField("summileage")
            private String summileage;

        /**
        * 油量 单位：L
        */
            @TableField("bk1")
            private String bk1;

        /**
        * 状态标志位：32位分别代表s0到s31
        */
            @TableField("bk2")
            private String bk2;

        /**
        * 胎压
        */
            @TableField("bk3")
            private String bk3;

        /**
        * 备用
        */
            @TableField("bk4")
            private String bk4;

        /**
        * 备用
        */
            @TableField("bk5")
            private String bk5;

        /**
        * 0 正常 1 删除
        */
            @TableField("is_delete")
            private Integer isDelete;

        /**
        * 创建时间
        */
            @TableField("create_date")
            private Date createDate;

        /**
        * 创建人
        */
            @TableField("create_user")
            private String createUser;

        /**
        * 最后一次修改时间
        */
            @TableField("modify_date")
            private Date modifyDate;

        /**
        * 修改记录用户
        */
            @TableField("modify_user")
            private String modifyUser;

        /**
        * 报警状态
        */
            @TableField("alarm_status")
            private String alarmStatus;

        /**
        * 平台报警状态
        */
            @TableField("alarm_status_plat")
            private String alarmStatusPlat;

        /**
        * 附加信息
        */
            @TableField("extra_info")
            private String extraInfo;

}
