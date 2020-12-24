package com.example.car.mapper.sqlserver;

import com.example.car.entity.OperationLog;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;

public interface OperationLogMapper {
   List<OperationLog> selectLog(@Param("carid")String carid,@Param("userid")String userid,@Param("id")Integer id);

   Integer insertLog(OperationLog operationLog);
}
