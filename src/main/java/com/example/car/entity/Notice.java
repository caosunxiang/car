/**
 * Copyright (C); 2020-2020; 众马科技有限公司
 * FileName: Notice
 * Author:   冷酷的苹果
 * Date:     2020/10/30 9:44
 * Description: 公告
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.example.car.entity;

import lombok.Data;

/**
 * 〈一句话功能简述〉<br> 
 * 〈公告〉
 *
 * @author 冷酷的苹果
 * @create 2020/10/30
 * @since 1.0.0
 */
@Data
public class Notice {
    private String NoticeId;
    private String Title;
    private String Content;
    private String Publisher;
    private String PublishTime;
    private String Owner;
    private String TitleColor;
    private String IsTop;
    private String IsPop;
    private String Mark;
    private String Category;
    private String Published;
    private String ExpireTime;
}
