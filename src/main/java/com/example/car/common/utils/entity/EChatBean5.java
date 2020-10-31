/**
 * Copyright (C), 2020-2020, 众马科技有限公司
 * FileName: EChatBean5
 * Author:   冷酷的苹果
 * Date:     2020/10/31 13:24
 * Description: 报警数量统计
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.example.car.common.utils.entity;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br> 
 * 〈报警数量统计〉
 *
 * @author 冷酷的苹果
 * @create 2020/10/31
 * @since 1.0.0
 */
@Data
public class EChatBean5 {

    private String time;

    private List<EChatBean6> message;
}
