/**
 * Copyright (C), 2020-2020, 众马科技有限公司
 * FileName: TruckInfo
 * Author:   冷酷的苹果
 * Date:     2020/6/22 9:50
 * Description: 车辆信息
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.example.car.entity;

import lombok.Data;

/**
 * 〈一句话功能简述〉<br> 
 * 〈车辆信息〉
 *
 * @author 冷酷的苹果
 * @create 2020/6/22
 * @since 1.0.0
 */
@Data
public class TruckInfo {
    /**
     * 车牌号
     */

    private String carNo;

    /**
     * 车型
     */

    private String carType;
    /**
     * 容量
     */

    private String capacity;
    /**
     * 年审有效期
     */

    private String expiryTime;
    /**
     * 所属公司名称
     */

    private String companyName;
    /**
     * 驾驶员姓名
     */

    private String driverName;
    /**
     * 驾驶员手机
     */

    private String driverMobile;

}
