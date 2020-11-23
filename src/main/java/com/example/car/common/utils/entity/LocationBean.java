/**
 * Copyright (C), 2020-2020, 众马科技有限公司
 * FileName: LocationBean
 * Author:   冷酷的苹果
 * Date:     2020/11/13 15:10
 * Description: 所有的东西的位置信息
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.example.car.common.utils.entity;

import lombok.Data;

import java.util.Map;

/**
 * 〈一句话功能简述〉<br> 
 * 〈所有的东西的位置信息〉
 *
 * @author 冷酷的苹果
 * @create 2020/11/13
 * @since 1.0.0
 */
@Data
public class LocationBean {
private String name;
private Integer type;
private Map<String,String> area;
}
