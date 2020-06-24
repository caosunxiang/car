/**
 * Copyright (C), 2020-2020, 众马科技有限公司
 * FileName: PermitTruck
 * Author:   冷酷的苹果
 * Date:     2020/6/22 9:24
 * Description: 车辆信息表
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.example.car.entity;

import lombok.Data;

/**
 * 〈一句话功能简述〉<br>
 * 〈车辆信息表〉
 *
 * @author 冷酷的苹果
 * @create 2020/6/22
 * @since 1.0.0
 */
@Data
public class PermitInfo {
    /**
     * 准运证号
     */

    private String permitNo;

    /**
     * 有效期起始
     */

    private String beginTime;
    /**
     * 有效期截止
     */

    private String endTime;
    /**
     * 运输线路
     */

    private String lineName;

}
