/**
 * Copyright (C), 2020-2020, 众马科技有限公司
 * FileName: driver_info
 * Author:   冷酷的苹果
 * Date:     2020/12/24 10:09
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.example.car.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 冷酷的苹果
 * @create 2020/12/24
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DriverInfo {

    private Integer driverId;

    private String driverName;

    private String driverMobile;

    private String driverCardNo;

    private String driverReviewTime;

    private String driverStatus;

    private String driverPhotoUrl;

    private String driverSex;

    private String driverAddress;

    private String licenseUrl;

    @TableField(exist = false)
    private String startTime;

    @TableField(exist = false)
    private String endTime;

    @TableField(exist = false)
    private String carNumber;
}
