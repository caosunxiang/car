package com.example.car.service.impl;

import com.example.car.common.utils.json.Body;
import com.example.car.mapper.mysql.CarInfoMapper;
import com.example.car.mapper.mysql.SysAuthDeptMapper;
import com.example.car.service.ISysAuthDeptService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 公司机构表 Service实现
 *
 * @author 冷酷的苹果
 * @date 2020-06-17 10:45:17
 */
@Service
@RequiredArgsConstructor
@Transactional(transactionManager = "masterTransactionManager",propagation = Propagation.SUPPORTS, readOnly = true)
public class SysAuthDeptServiceImpl implements ISysAuthDeptService {

    private final SysAuthDeptMapper sysAuthDeptMapper;

    private final CarInfoMapper carInfoMapper;

    @Override
    public Body selectSysAuthDept(String name,String number,String status) {
        List<Map<String, Object>> list = this.sysAuthDeptMapper.selectSysAuthDept(name,number);
        if (list.size()>0){
            for (Map<String, Object> map : list) {
                map.put("deptid",map.get("deptid")+"");
                map.put("parentid",map.get("parentid")+"");
                Integer count=carInfoMapper.selectStatusCount(map.get("ids").toString(),status);
                map.put("count",count);
            }
        }
        return Body.newInstance(list);
    }
}
