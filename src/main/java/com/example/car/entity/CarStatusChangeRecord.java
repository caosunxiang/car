package com.example.car.entity;

    import java.util.Date;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
* 车辆报备登记 Entity
*
* @author 冷酷的苹果
* @date 2020-07-21 17:28:17
*/
@Data
@TableName("car_status_change_record")
public class CarStatusChangeRecord {

        /**
        * 
        */
            @TableId(value = "id", type = IdType.AUTO)
            private Long id;

        /**
        * 车辆ID
        */
            @TableField("carid")
            private Long carid;

        /**
        * 用户id
        */
            @TableField("userid")
            private Long userid;

        /**
        * 修改前状态  正常  维修  停运
        */
            @TableField("prev_status")
            private Integer prevStatus;

        /**
        * 修改后状态
        */
            @TableField("current_status")
            private Integer currentStatus;

        /**
        * 报备日期
        */
            @TableField("modify_date")
            private String modifyDate;

        /**
        * 备注
        */
            @TableField("remark")
            private String remark;

        /**
        * 
        */
            @TableField("back1")
            private String back1;

        /**
        * 
        */
            @TableField("back2")
            private String back2;

        /**
        * 
        */
            @TableField("back3")
            private String back3;

        /**
        * 
        */
            @TableField("back4")
            private String back4;

        /**
        * 
        */
            @TableField("back5")
            private Long back5;

}
