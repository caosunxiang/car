package com.example.car.entity;

import lombok.Data;

import java.util.Date;

/**
* 设备上下线表 Entity
*
* @author 冷酷的苹果
* @date 2020-07-21 16:52:53
*/
@Data
public class DeviceOnlineRecord {

        /**
        * id
        */
            private Long id;

        /**
        * 
        */
            private Long terminalId;

        /**
        * 终端号码
        */
            private String terminal;

        /**
        * 经度
        */
            private Double lng;

        /**
        * 纬度
        */
            private Double lat;

        /**
        * 状态	1上线 2下线
        */
            private Integer status;

        /**
        * 备用
        */
            private String bk1;

        /**
        * 备用
        */
            private String bk2;

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

}
