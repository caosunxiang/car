/**
 * Copyright (C), 2020-2020, 众马科技有限公司
 * FileName: PageUtils
 * Author:   冷酷的苹果
 * Date:     2020/11/18 14:56
 * Description: 分页
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.example.car.common.utils.page;

import lombok.Data;

import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈分页〉
 *
 * @author 冷酷的苹果
 * @create 2020/11/18
 * @since 1.0.0
 */
@Data
public class PageUtils {
    private Integer total;
    private List list;
    private Integer index;
    private Integer size;
}
