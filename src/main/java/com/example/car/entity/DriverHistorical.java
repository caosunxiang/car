/**
 * Copyright (C), 2020-2020, 众马科技有限公司
 * FileName: DriverHistorical
 * Author:   冷酷的苹果
 * Date:     2020/12/25 11:02
 * Description: 驾驶员历史信息
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.example.car.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 〈一句话功能简述〉<br> 
 * 〈驾驶员历史信息〉
 *
 * @author 冷酷的苹果
 * @create 2020/12/25
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DriverHistorical {

    private Integer id;

    private String carId;

    private String driverId;

    private String startTime;

    private String endTime;
}
