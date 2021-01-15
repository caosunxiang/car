/**
 * Copyright (C), 2020-2020, 众马科技有限公司
 * FileName: CarInsurance
 * Author:   冷酷的苹果
 * Date:     2020/12/15 10:15
 * Description: 车辆保险
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
 * 〈车辆保险〉
 *
 * @author 冷酷的苹果
 * @create 2020/12/15
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarInsurance {

    private Integer insuranceId;
    private String insuranceUrl;
    private String jqxTime;
    private String jqxId;
    private String jqxCompany;
    private String jqxMoney;
    private String syxId;
    private String syxTime;
    private String syxCompany;
    private String syxMoney;
    private String annualVerification;
    //审查意见
    private String verification;
    private String verificationTime;
    //审查人
    private String examinant;
    private String carId;
    private String carRestrict;
    private String useRestrict;
    @TableField(exist = false)
    private String carNumber;
}
