/**
 * Copyright (C), 2020-2020, 众马科技有限公司
 * FileName: CarCertification
 * Author:   冷酷的苹果
 * Date:     2020/12/28 9:31
 * Description:
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
 * 〈〉
 *
 * @author 冷酷的苹果
 * @create 2020/12/28
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarCertification {

    private Integer id;

    private  String certificationStatus;

    private String certificationTime;

    private String createTime;

    private String createUser;

    private String carId;

    private String updateUser;
}
