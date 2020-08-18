/**
 * Copyright (C), 2020-2020, 众马科技有限公司
 * FileName: EChatBean
 * Author:   冷酷的苹果
 * Date:     2020/8/3 16:07
 * Description: 折线图
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.example.car.common.utils.entity;

import lombok.Data;

/**
 * 〈一句话功能简述〉<br>
 * 〈折线图〉
 *
 * @author 冷酷的苹果
 * @create 2020/8/3
 * @since 1.0.0
 */
@Data
public class EChatBean {

    private long id;

    private Integer status;

    private String createDate;

    private String type;

    private String nowStatus;

    private String endTime;
}
