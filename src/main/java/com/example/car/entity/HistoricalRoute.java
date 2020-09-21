/**
 * Copyright (C), 2020-2020, 众马科技有限公司
 * FileName: HistoricalRoute
 * Author:   冷酷的苹果
 * Date:     2020/9/21 9:05
 * Description: 车辆历史轨迹
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.example.car.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.persistence.Table;

/**
 * 〈一句话功能简述〉<br>
 * 〈车辆历史轨迹〉
 *
 * @author 冷酷的苹果
 * @create 2020/9/21
 * @since 1.0.0
 */
@Data
@Table(name="historical_route")
public class HistoricalRoute {
    private String id;
    private String city;
    private String deptid;
    private String speed;
    private String carid;
    private String blat;
    private String gpsflag;
    private String province;
    private String tName;
    private String carstatus;
    private String gaddress;
    private String lat;
    private String seq;
    private String direction;
    private String mileage;
    private String createtime;
    private String address;
    private String lng;
    private String summileage;
    private String glat;
    private String msgid;
    private String terminal;
    private String blng;
    private String glng;
    private String deptname;
    private String carnumber;
    private String district;
    private String gpstime;
    private String altitude;
}
