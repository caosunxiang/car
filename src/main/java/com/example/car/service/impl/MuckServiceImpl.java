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
import com.example.car.common.utils.page.PageUtils;
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
@Transactional(transactionManager = "otherTransactionManager", propagation = Propagation.SUPPORTS, readOnly = true)
public class MuckServiceImpl implements MuckService {

    private final MuckMapper muckMapper;


    @Override
    public Body selectMuck(String carNo, String permitNo, String
            time) {
        List<Map<String, String>> list = this.muckMapper.selectMuck(carNo, permitNo, time);
        return Body.newInstance(list);
    }

    @Override
    public Body selectMuckByName(String projectName, String time, String name, String endTime, Integer index,
                                 Integer size) {
        if (StringUtils.isEmpty(index)) {
            index = 1;
        }
        if (StringUtils.isEmpty(size)) {
            size = 20;
        }
        if (index > muckMapper.selectMuckCount(projectName, time, name, endTime) / size) {
            size = muckMapper.selectMuckCount(projectName, time, name, endTime) % size;
        }
        List<Map<String, String>> list = this.muckMapper.
                selectMuckByName(projectName, time, name, endTime,
                        (index * size), size);
        return Body.newInstance(list);
    }

    @Override
    public Body selectMuckByProject(String projectId, String time) {
        List<Map<String, String>> list = this.muckMapper.selectMuckByProject(projectId, time);
        return Body.newInstance(list);
    }

    @Override
    public Body selectMuckCount(String projectName, String time, String name, String endTime) {
        Integer count = this.muckMapper.selectMuckCount(projectName, time, name, endTime);
        return Body.newInstance(count);
    }

    @Override
    public Body selectCarInfo(String carNo, Integer size, Integer index, String CompanyName,
                              String DriverName, String EngineNo,
                              String CarframeNo, String CarType) {
        List<Map<String, String>> list = this.muckMapper.selectCarInfo(carNo, size, index * size, CompanyName,
                DriverName, EngineNo, CarframeNo, CarType);
        PageUtils pageUtils = new PageUtils();
        pageUtils.setIndex(index);
        pageUtils.setSize(size);
        pageUtils.setTotal(muckMapper.selectCarInfoCount(carNo, CompanyName,
                DriverName, EngineNo, CarframeNo, CarType));
        pageUtils.setList(list);
        return Body.newInstance(pageUtils);
    }

    @Override
    public Body selectGivenPlace(String name, Integer size, Integer index, String M0702, String M0707, String M0708,
                                 String M0703, String M0704, String M0709,
                                 String M0710) {
        PageUtils pageUtils = new PageUtils();
        pageUtils.setIndex(index);
        pageUtils.setSize(size);
        pageUtils.setTotal(muckMapper.selectGivenPlaceCount(name, M0702, M0707, M0708, M0703, M0704, M0709, M0710));
        pageUtils.setList(this.muckMapper.selectGivenPlace(name, size, index * size, M0702, M0707, M0708, M0703,
                M0704, M0709, M0710));
        return Body.newInstance(pageUtils);
    }

    @Override
    public Body selectConstructionSite(String name, Integer size, Integer index, String M0417,
                                       String M0419, String M0402,
                                       String M0403, String M0414,
                                       String M0420, String M0425,
                                       String M0426) {
        PageUtils pageUtils = new PageUtils();
        pageUtils.setIndex(index);
        pageUtils.setSize(size);
        pageUtils.setTotal(muckMapper.selectConstructionSiteCount(name, M0417, M0419, M0402, M0403, M0414, M0420,
                M0425, M0426));
        pageUtils.setList(this.muckMapper.selectConstructionSite(name, size, index * size, M0417, M0419, M0402, M0403
                , M0414, M0420, M0425, M0426));
        return Body.newInstance(pageUtils);
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

    @Override
    public Body selectGivenPlaceOne(String recid) {
        return Body.newInstance(this.muckMapper.selectGivenPlaceOne(recid));
    }

    @Override
    public Body selectConstructionSiteOne(String recid) {
        return Body.newInstance(this.muckMapper.selectConstructionSiteOne(recid));
    }

    @Override
    public Body selectoperprogress(String number) {
        return Body.newInstance(this.muckMapper.selectoperprogress(number));
    }

    @Override
    public Body selectNotice() {
        return Body.newInstance(this.muckMapper.selectNotice());
    }

    @Override
    public Body selectM01() {
        return Body.newInstance(this.muckMapper.selectM01());
    }

    @Override
    public Body selectCountByMuck(String time) {
        return Body.newInstance(this.muckMapper.selectCountByMuck(time));
    }

    @Override
    public Body selectMuckAdvanced(String name, Integer size, Integer index, String BeginTime,
                                   String type) {
        PageUtils pageUtils = new PageUtils();
        pageUtils.setIndex(index);
        pageUtils.setSize(size);
        pageUtils.setTotal(muckMapper.selectMuckadvancedCount(name, BeginTime, type));
        List<Map<String, Object>> list = this.muckMapper.selectMuckadvanced(name, size, index * size, BeginTime, type);
        for (Map<String, Object> objectMap : list) {
            List<String> strings = muckMapper.selectCarByPermitNo(objectMap.get("PermitNo").toString());
            objectMap.put("car", strings);
        }
        pageUtils.setList(list);
        return Body.newInstance(pageUtils);
    }

    @Override
    public Body selectMuckPage(String name, Integer size, Integer index, String BeginTime, String type) {
        PageUtils pageUtils = new PageUtils();
        pageUtils.setIndex(index);
        pageUtils.setSize(size);
        pageUtils.setTotal(muckMapper.selectMuckPageCount(name, BeginTime, type));
        pageUtils.setList(this.muckMapper.selectMuckPage(name, size, index * size, BeginTime, type));
        return Body.newInstance(pageUtils);
    }
}
