/**
 * Copyright (C), 2020-2020, 众马科技有限公司
 * FileName: M01Mapper
 * Author:   冷酷的苹果
 * Date:     2020/12/28 13:52
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.example.car.mapper.sqlserver;

import com.example.car.entity.M01;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author 冷酷的苹果
 * @create 2020/12/28
 * @since 1.0.0
 */
public interface M01Mapper {

    List<M01>selectM01Details(@Param("MustId")String MustId,@Param("name")String name,@Param("phone")String phone);

    void updateM01(M01 m01);

    void insertM01(M01 m01);

}
