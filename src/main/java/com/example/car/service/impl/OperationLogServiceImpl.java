/**
 * Copyright (C), 2020-2020, 众马科技有限公司
 * FileName: OperationLogServiceImpl
 * Author:   冷酷的苹果
 * Date:     2020/12/22 13:26
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.example.car.service.impl;

import com.example.car.common.utils.json.Body;
import com.example.car.entity.OperationLog;
import com.example.car.mapper.sqlserver.OperationLogMapper;
import com.example.car.service.OperationLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author 冷酷的苹果
 * @create 2020/12/22
 * @since 1.0.0
 */
@Service
@RequiredArgsConstructor
@Transactional(transactionManager = "otherTransactionManager", propagation = Propagation.SUPPORTS, readOnly = true)
public class OperationLogServiceImpl implements OperationLogService {

    @Autowired
    private OperationLogMapper operationLogMapper;

    @Override
    public Body selectLog(String carid, String userid, Integer id) {
        List<OperationLog>operationLogs=operationLogMapper.selectLog(carid, userid, id);
        return Body.newInstance(operationLogs);
    }
}
