/**
 * Copyright (C), 2020-2021, 众马科技有限公司
 * FileName: Apply
 * Author:   冷酷的苹果
 * Date:     2021/1/11 9:15
 * Description: 工单审核情况表
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.example.car.common.utils.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 〈一句话功能简述〉<br> 
 * 〈工单审核情况表〉
 *
 * @author 冷酷的苹果
 * @create 2021/1/11
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Apply {
    /**
     * 新申请
     */
   private Integer newApply;

    /**
     * .待处理
     */
   private Integer pendingApply;

    /***
     * 处理中
     */
   private Integer ingApply;

    /**
     * 已办结
     */
   private Integer overApply;
}
