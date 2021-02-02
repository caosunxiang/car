/**
 * Copyright (C), 2020-2021, 众马科技有限公司
 * FileName: Driver
 * Author:   冷酷的苹果
 * Date:     2021/2/1 16:52
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.example.car.common.utils.entity;

import com.example.car.entity.DriverInfo;
import lombok.Data;

import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author 冷酷的苹果
 * @create 2021/2/1
 * @since 1.0.0
 */
@Data
public class DriverBean {

    private DriverInfo driver;

    private List<String> carnumber;

}
