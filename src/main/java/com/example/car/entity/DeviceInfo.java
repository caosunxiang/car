/**
 * Copyright (C), 2020-2021, 众马科技有限公司
 * FileName: DeviceInfo
 * Author:   冷酷的苹果
 * Date:     2021/1/4 15:19
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
 * @create 2021/1/4
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceInfo {

    private String id;

    private String deptid;

    private String terminal;

    private String carid;

    private String devicetype;

    private String deviceno;

    private String sim;

    private String installstatus;

    private String installtime;

    private String remark;

    private String bk1;

    private String bk2;

    private String is_delete;

    private String create_date;

    private String create_user;

    private String modify_date;

    private String modify_user;

    private String terminalparams;

    private String product_id;

    private String deptidnew;

    @TableField(exist = false)
    private String carnumber;
}
