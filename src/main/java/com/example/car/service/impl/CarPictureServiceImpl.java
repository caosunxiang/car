/**
 * Copyright (C), 2020-2020, 众马科技有限公司
 * FileName: CarTypeServiceImpl
 * Author:   冷酷的苹果
 * Date:     2020/6/28 16:00
 * Description: 车辆图标
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.example.car.service.impl;

import com.example.car.common.utils.json.Body;
import com.example.car.entity.CarPicture;
import com.example.car.mapper.mysql.CarPictureMapper;
import com.example.car.service.CarPictureService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 〈一句话功能简述〉<br> 
 * 〈车辆图标〉
 *
 * @author 冷酷的苹果
 * @create 2020/6/28
 * @since 1.0.0
 */
@Service
@RequiredArgsConstructor
@Transactional(transactionManager = "masterTransactionManager",propagation = Propagation.SUPPORTS, readOnly = true)
public class CarPictureServiceImpl implements CarPictureService {

    @Autowired
    private final CarPictureMapper carPictureMapper;


}
