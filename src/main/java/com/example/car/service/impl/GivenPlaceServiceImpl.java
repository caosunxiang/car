/**
 * Copyright (C), 2020-2020, 众马科技有限公司
 * FileName: GivenPlaceServiceImpl
 * Author:   冷酷的苹果
 * Date:     2020/10/13 8:43
 * Description: 消纳场所业务逻辑
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.example.car.service.impl;

import com.example.car.common.utils.json.Body;
import com.example.car.entity.GivenPlace;
import com.example.car.mapper.mysql.GivenPlaceMapper;
import com.example.car.service.GivenPlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 〈一句话功能简述〉<br> 
 * 〈消纳场所业务逻辑〉
 *
 * @author 冷酷的苹果
 * @create 2020/10/13
 * @since 1.0.0
 */
@Service
@RequiredArgsConstructor
@Transactional(transactionManager = "masterTransactionManager", propagation = Propagation.SUPPORTS, readOnly = true)
public class GivenPlaceServiceImpl implements GivenPlaceService {

    @Autowired
    private GivenPlaceMapper givenPlaceMapper;

    @Override
    public Body insertGivenPlace(GivenPlace givenPlace) {
        givenPlaceMapper.insertGivenPlace(givenPlace);
        return Body.BODY_200;
    }

    @Override
    public Body updateGivenPlace(GivenPlace givenPlace) {
        givenPlaceMapper.updateGivenPlace(givenPlace);
        return Body.BODY_200;
    }

    @Override
    public Body delGivenPlace(Integer id) {
        givenPlaceMapper.delGivenPlace(id);
        return Body.BODY_200;
    }

    @Override
    public Body selectOne(Integer id) {
        return Body.newInstance(givenPlaceMapper.selectOne(id));
    }

    @Override
    public Body selectAll() {
        return Body.newInstance(givenPlaceMapper.selectAll());
    }
}
