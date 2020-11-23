/**
 * Copyright (C), 2020-2020, 众马科技有限公司
 * FileName: DeptDetail
 * Author:   冷酷的苹果
 * Date:     2020/11/17 17:38
 * Description: 分组详情
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.example.car.common.utils.entity;

import lombok.Data;

import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈分组详情〉
 *
 * @author 冷酷的苹果
 * @create 2020/11/17
 * @since 1.0.0
 */
@Data
public class DeptDetail {
  private String id;
  private String name;
  private String pid;
  private List<DeptDetailChild> sysAuthDepts;
}
