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
import com.example.car.entity.M04;
import com.example.car.entity.M07;
import com.example.car.mapper.sqlserver.MuckMapper;
import com.example.car.service.MuckService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

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
    public Body selectMuckByName(String projectName,String time,String name,String endTime,Integer index,Integer size) {
        if (StringUtils.isEmpty(index)){
            index=1;
        }
        if (StringUtils.isEmpty(size)){
            size=20;
        }
        if (index>muckMapper.selectMuckCount(projectName,time,name,endTime)/size){
            size=muckMapper.selectMuckCount(projectName,time,name,endTime)%size;
        }
        List<Map<String,String>>list=this.muckMapper.selectMuckByName(projectName,time,name,endTime,(index*size),size);
        return Body.newInstance(list);
    }

    @Override
    public Body selectMuckByProject(String projectId,String time) {
        List<Map<String,String>>list=this.muckMapper.selectMuckByProject(projectId,time);
        return Body.newInstance(list);
    }

    @Override
    public Body selectMuckCount(String projectName, String time, String name, String endTime) {
        Integer count =this.muckMapper.selectMuckCount(projectName,time,name,endTime);
        return Body.newInstance(count);
    }

    @Override
    public Body selectCarInfo(String carNo) {
        List<Map<String,String>>list=this.muckMapper.selectCarInfo(carNo);
        return Body.newInstance(list);
    }

    @Override
    public Body selectGivenPlace() {
        return Body.newInstance(this.muckMapper.selectGivenPlace());
    }

    @Override
    public Body selectConstructionSite() {
        return Body.newInstance(this.muckMapper.selectConstructionSite());
    }

    @Override
    public Body updateGivenPlace(M07 m07) {
        this.muckMapper.updateGivenPlace(m07);
        return Body.newInstance();
    }

    @Override
    public Body updateConstructionSite(M04 m04) {
        this.muckMapper.updateConstructionSite(m04);
        return Body.newInstance();
    }

    @Override
    public Body selectCarInfoByCompany(String companyId) {
        return Body.newInstance(this.muckMapper.selectCarInfoByCompany(companyId));
    }
}
