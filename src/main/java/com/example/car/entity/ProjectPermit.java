/**
 * Copyright (C), 2020-2020, 众马科技有限公司
 * FileName: ProjectPermit
 * Author:   冷酷的苹果
 * Date:     2020/6/22 9:54
 * Description: 工程准运证
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.example.car.entity;

import lombok.Data;

/**
 * 〈一句话功能简述〉<br> 
 * 〈工程准运证〉
 *
 * @author 冷酷的苹果
 * @create 2020/6/22
 * @since 1.0.0
 */
@Data
public class ProjectPermit {

    /**
     * 工程 id
     */

    private String projectId;

    /**
     * 工程名
     */

    private String projectName;
    /**
     * 运输线路
     */

    private String lineName;
    /**
     * 开始日期
     */

    private String beginTime;

    /**
     * 结束日期
     */

    private String endTime;
    /**
     *
     * 准运证号
     */
    private String permitNo;

}
