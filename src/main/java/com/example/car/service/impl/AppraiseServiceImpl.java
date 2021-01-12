/**
 * Copyright (C), 2020-2021, 众马科技有限公司
 * FileName: AppraiseServiceImpl
 * Author:   冷酷的苹果
 * Date:     2021/1/12 11:14
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.example.car.service.impl;

import com.example.car.common.utils.json.Body;
import com.example.car.entity.Appraise;
import com.example.car.mapper.sqlserver.AppraiseMapper;
import com.example.car.service.AppraiseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 冷酷的苹果
 * @create 2021/1/12
 * @since 1.0.0
 */
@Service
@RequiredArgsConstructor
@Transactional(transactionManager = "otherTransactionManager", propagation = Propagation.SUPPORTS, readOnly = true)
public class AppraiseServiceImpl implements AppraiseService {

    private final AppraiseMapper appraiseMapper;

    @Override
    public Body selectAppraise(String deptid, String appraiseLevel, Integer id) {
        List<Appraise> appraises = appraiseMapper.selectAppraise(deptid, id, appraiseLevel);
        return Body.newInstance(appraises);
    }

    @Override
    public Body insertAppraise(Appraise appraise) {
        appraiseMapper.insertAppraise(appraise);
        return Body.BODY_200;
    }

    @Override
    public Body updateAppraise(Appraise appraise) {
        appraiseMapper.updateAppraise(appraise);
        return Body.BODY_200;
    }

    @Override
    public Body deleteAppraise(Integer id) {
        appraiseMapper.deleteAppraise(id);
        return Body.BODY_200;
    }
}
