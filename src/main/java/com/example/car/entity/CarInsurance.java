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

import lombok.Data;

/**
 * 〈一句话功能简述〉<br>
 * 〈车辆保险〉
 *
 * @author 冷酷的苹果
 * @create 2020/12/15
 * @since 1.0.0
 */
@Data
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
    private String annualVerification;
    private String verification;
    private String verificationTime;
    private String examinant;
    private String carId;
}