/**
 * Copyright (C), 2020-2020, 众马科技有限公司
 * FileName: M03ServiceImp
 * Author:   冷酷的苹果
 * Date:     2020/12/22 18:11
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.example.car.service.impl;

import com.example.car.common.utils.DateUtil;
import com.example.car.common.utils.json.Body;
import com.example.car.entity.M03;
import com.example.car.entity.OperationLog;
import com.example.car.mapper.sqlserver.M03Mapper;
import com.example.car.mapper.sqlserver.OperationLogMapper;
import com.example.car.service.M03Service;
import com.example.car.service.OperationLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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
public class M03ServiceImpl implements M03Service {

    @Autowired
    private M03Mapper m03Mapper;
    @Autowired
    private OperationLogMapper operationLogMapper;


    @Override
    public Body selectM03(String carNumber, String recId, String phone,String MustId) {
        List<M03> m03s = m03Mapper.selectM03(carNumber, recId, phone,MustId);
        return Body.newInstance(m03s);
    }

    @Override
    public Body updateM03(String person, String quality, String dimensions, String scrapTime, String IssuanceDate,
                          String totalQuality, String checkQuality, String tractionQuality, String stopTransport,
                          String stopNumber, String stopEndTime, String RecId, String userid) {
        M03 m03 = new M03();
        m03.setPerson(person);
        m03.setQuality(quality);
        m03.setDimensions(dimensions);
        m03.setScrapTime(scrapTime);
        m03.setIssuanceDate(IssuanceDate);
        m03.setTotalQuality(totalQuality);
        m03.setCheckQuality(checkQuality);
        m03.setTractionQuality(tractionQuality);
        m03.setStopTransport(stopTransport);
        m03.setStopNumber(stopNumber);
        m03.setStopEndTime(stopEndTime);
        m03.setRecId(RecId);
        m03Mapper.updateM03(m03);
        OperationLog operationLog = new OperationLog(null, RecId, "修改", "修改车辆信息", DateUtil.getDateFormat(new Date(),
                DateUtil.FULL_TIME_SPLIT_PATTERN),userid,null,null );
        operationLogMapper.insertLog(operationLog);
        return Body.BODY_200;
    }

    @Override
    public Body updateStopTransport(String stopTransport, Integer stopNumber, String recId) {
        m03Mapper.updateStopTransport(stopTransport,stopNumber,DateUtil.severalDaysAgo(DateUtil.FULL_TIME_SPLIT_PATTERN, -stopNumber),recId);
        return Body.BODY_200;
    }
}
