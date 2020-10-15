/**
 * Copyright (C), 2020-2020, 众马科技有限公司
 * FileName: GivenPlace
 * Author:   冷酷的苹果
 * Date:     2020/10/12 17:11
 * Description: 消纳场所
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.example.car.entity;

import lombok.Data;

/**
 * 〈一句话功能简述〉<br>
 * 〈消纳场所〉
 *
 * @author 冷酷的苹果
 * @create 2020/10/12
 * @since 1.0.0
 */
@Data
public class Place {
    private Integer id;
    private String name;
    private String lat;
    private String lng;
    private String dic;
    private String createtime;
    private String createuser;
    private Integer type;
}
