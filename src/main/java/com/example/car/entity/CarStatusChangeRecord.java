package com.example.car.entity;

import lombok.Data;

import java.util.Date;

/**
* 车辆报备登记 Entity
*
* @author 冷酷的苹果
* @date 2020-07-21 17:28:17
*/
@Data
public class CarStatusChangeRecord {

        /**
        * 
        */

            private Long id;

        /**
        * 车辆ID
        */

            private Long carid;

        /**
        * 用户id
        */

            private Long userid;

        /**
        * 修改前状态  正常  维修  停运
        */

            private Integer prevStatus;

        /**
        * 修改后状态
        */

            private Integer currentStatus;

        /**
        * 报备日期
        */

            private Date modifyDate;

        /**
        * 备注
        */

            private String remark;

        /**
        * 
        */

            private String back1;

        /**
        * 
        */

            private String back2;

        /**
        * 
        */

            private String back3;

        /**
        * 
        */

            private String back4;

        /**
        * 
        */

            private Long back5;

}
