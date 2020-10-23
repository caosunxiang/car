/**
 * Copyright (C); 2020-2020; 众马科技有限公司
 * FileName: Operprogress
 * Author:   冷酷的苹果
 * Date:     2020/10/23 15:09
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.example.car.entity;

import lombok.Data;

/**
 * 〈一句话功能简述〉<br> 
 * 〈审批流程〉
 *
 * @author 冷酷的苹果
 * @create 2020/10/23
 * @since 1.0.0
 */
@Data
public class Operprogress {
    private String actionResult;
    private String callerDesc;
    private String callerTime;
    private String creatorDesc;
    private String description;
    private String dueDate;
    private String finishDate;
    private String ownerDesc;
    private String senderDesc;
    private String serialNumber;
    private String startDate;
    private String status;
    private String stepName;
}
