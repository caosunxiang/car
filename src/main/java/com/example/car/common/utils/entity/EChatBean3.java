/**
 * Copyright (C), 2020-2020, 众马科技有限公司
 * FileName: EChatBean3
 * Author:   冷酷的苹果
 * Date:     2020/8/7 10:38
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.example.car.common.utils.entity;

import lombok.Data;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author 冷酷的苹果
 * @create 2020/8/7
 * @since 1.0.0
 */
@Data
public class EChatBean3 {
    private Long id;
    private String alarmname;
    private String carnumber;
    private String start_time;
    private String start_speed;
    private String end_time;
    private String end_speed;
    private String end_lat;
    private String end_lon;
    private String start_lat;
    private String start_lon;
    private String alarmMileage;
}
