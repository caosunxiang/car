/**
 * Copyright (C), 2020-2020, 众马科技有限公司
 * FileName: Online
 * Author:   冷酷的苹果
 * Date:     2020/12/7 13:51
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.example.car.common.utils.excel.entity;

import lombok.Data;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author 冷酷的苹果
 * @create 2020/12/7
 * @since 1.0.0
 */
@Data
public class Online {

    private String carnumber;
    private String deptname;
    private String terminalId;
    private Integer car_service_status;
    private Integer carstatus;
    private Long up;
    private Long down;
    private Long other;
}
