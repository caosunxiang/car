/**
 * Copyright (C), 2020-2020, 众马科技有限公司
 * FileName: MuckServiceImpl
 * Author:   冷酷的苹果
 * Date:     2020/6/22 10:25
 * Description: 查询准运证
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.example.car.service.impl;

import com.example.car.common.utils.json.Body;
import com.example.car.mapper.sqlserver.MuckMapper;
import com.example.car.service.MuckService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br> 
 * 〈查询准运证〉
 *
 * @author 冷酷的苹果
 * @create 2020/6/22
 * @since 1.0.0
 */
@Service
@RequiredArgsConstructor
@Transactional(transactionManager = "otherTransactionManager",propagation = Propagation.SUPPORTS, readOnly = true)
public class MuckServiceImpl implements MuckService {

    private final MuckMapper muckMapper;


    @Override
    public Body selectMuck(String carNo, String permitNo,String
                            time) {
        List<Map<String,String>>list=this.muckMapper.selectMuck(carNo,permitNo,time);
        return Body.newInstance(list);
    }

    @Override
    public Body selectMuckByName(String projectName,String time,String name) {
        List<Map<String,String>>list=this.muckMapper.selectMuckByName(projectName,time,name);
        return Body.newInstance(list);
    }

    @Override
    public Body selectMuckByProject(String projectId,String time) {
        List<Map<String,String>>list=this.muckMapper.selectMuckByProject(projectId,time);
        return Body.newInstance(list);
    }
}
