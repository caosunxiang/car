package com.example.car.entity;

    import java.util.Date;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
* 设备上下线表 Entity
*
* @author 冷酷的苹果
* @date 2020-07-21 16:52:53
*/
@Data
@TableName("device_online_record")
public class DeviceOnlineRecord {

        /**
        * id
        */
            @TableId(value = "id", type = IdType.AUTO)
            private Long id;

        /**
        * 
        */
            @TableField("terminal_id")
            private Long terminalId;

        /**
        * 终端号码
        */
            @TableField("terminal")
            private String terminal;

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
        * 状态	1上线 2下线
        */
            @TableField("status")
            private Integer status;

        /**
        * 备用
        */
            @TableField("bk1")
            private String bk1;

        /**
        * 备用
        */
            @TableField("bk2")
            private String bk2;

        /**
        * 0 正常 1 删除
        */
            @TableField("is_delete")
            private Integer isDelete;

        /**
        * 创建时间
        */
            @TableField("create_date")
            private String createDate;

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

}
