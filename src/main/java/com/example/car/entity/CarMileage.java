/**
 * Copyright (C), 2020-2020, 众马科技有限公司
 * FileName: CarMileage
 * Author:   冷酷的苹果
 * Date:     2020/7/24 15:48
 * Description: 车辆里程数
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.example.car.entity;

import lombok.Data;

/**
 * 〈一句话功能简述〉<br> 
 * 〈车辆里程数〉
 *
 * @author 冷酷的苹果
 * @create 2020/7/24
 * @since 1.0.0
 */
@Data
public class CarMileage {

    /**
     * id
     */
    private Integer id;

    /**
     * 车牌号
     */
    private String carName;

    /**
     * 总里程数
     */
    private Double carMileage;

    /**
     * 当天里程
     */
    private Double carMileageToday;

}
