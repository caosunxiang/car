/**
 * Copyright (C), 2020-2021, 众马科技有限公司
 * FileName: CarStatus
 * Author:   冷酷的苹果
 * Date:     2021/1/8 17:30
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.example.car.common.utils.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author 冷酷的苹果
 * @create 2021/1/8
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarStatus {
  private String M0331;
  private String certificationTime;
  private String EndTime;
  private String stopTransport;
  private String stopNumber;
  private String stopEndTime;
  @TableField(exist = false)
  private String status;
}
