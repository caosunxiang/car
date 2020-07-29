/**
 * Copyright (C), 2020-2020, 众马科技有限公司
 * FileName: CarPicture
 * Author:   冷酷的苹果
 * Date:     2020/6/28 15:57
 * Description: 车辆图片
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.example.car.entity;

import lombok.Data;

/**
 * 〈一句话功能简述〉<br> 
 * 〈车辆图片〉
 *
 * @author 冷酷的苹果
 * @create 2020/6/28
 * @since 1.0.0
 */
@Data
public class CarTarget {
    /**
     * 车辆类型
     *
     * */
   private String id;

   /**
    * 车辆图标
    * */
   private String carNumber;
}
