/**
 * Copyright (C), 2020-2020, 众马科技有限公司
 * FileName: OperationLog
 * Author:   冷酷的苹果
 * Date:     2020/12/21 15:20
 * Description: 操作日志
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
 * 〈操作日志〉
 *
 * @author 冷酷的苹果
 * @create 2020/12/21
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OperationLog {
    private Integer id;
    private String carId;
    private String title;
    private String content;
    private String time;
    private String userId;
    @TableField(exist = false)
    private String carNumber;
    @TableField(exist = false)
    private String userName;
}
