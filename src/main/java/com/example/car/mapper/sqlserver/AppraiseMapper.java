package com.example.car.mapper.sqlserver;

import com.example.car.entity.Appraise;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;

public interface AppraiseMapper {
    List<Appraise> selectAppraise(@Param("deptid") String deptid, @Param("id") Integer id,
                                  @Param("appraiseLevel") String appraiseLevel);

    void insertAppraise(Appraise appraise);

    void updateAppraise(Appraise appraise);

    void deleteAppraise(@Param("id")Integer id);
}
