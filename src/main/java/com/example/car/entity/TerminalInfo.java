/**
 * Copyright (C), 2020-2021, 众马科技有限公司
 * FileName: TerminalInfo
 * Author:   冷酷的苹果
 * Date:     2021/1/5 9:00
 * Description:
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
 * 〈〉
 *
 * @author 冷酷的苹果
 * @create 2021/1/5
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TerminalInfo {

    private Integer terminalId;

    private String terminal;

    private String terminalType;

    private String createDate;

    private String createUser;

    private String carId;

    private String deptid;

    private String installstatus;

    private String installtime;

    private String isDelete;

    private String modifyDate;

    private String modifyUser;

    private String carNumber;

    @TableField(exist = false)
    private String status;

    @TableField(exist = false)
    private String  typename;


}
