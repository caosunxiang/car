/**
 * Copyright (C), 2020-2020, 众马科技有限公司
 * FileName: ProjectInfo
 * Author:   冷酷的苹果
 * Date:     2020/6/22 9:53
 * Description: 工程信息
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.example.car.entity;

import lombok.Data;

/**
 * 〈一句话功能简述〉<br> 
 * 〈工程信息〉
 *
 * @author 冷酷的苹果
 * @create 2020/6/22
 * @since 1.0.0
 */
@Data
public class ProjectInfo {
    /**
     * 工程 id
     */

    private String projectId;

    /**
     * 工程名
     */

    private String projectName;
    /**
     * 位置
     */

    private String place;
    /**
     * 开始日期
     */

    private String beginTime;

    /**
     * 结束日期
     */

    private String endTime;

}
