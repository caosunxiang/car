/**
 * Copyright (C), 2020-2021, 众马科技有限公司
 * FileName: appraise
 * Author:   冷酷的苹果
 * Date:     2021/1/12 10:42
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
 * @create 2021/1/12
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Appraise {

    private Integer id;

    private String deptid;

    private String appraiseLevel;

    private String appraiseScore;

    private String appraiseRemark;

    private String appraiseTime;
}
