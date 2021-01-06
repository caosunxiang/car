package com.example.car.mapper.sqlserver;

import com.example.car.entity.M03;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;

public interface M03Mapper {
    List<M03> selectM03(@Param("carNumber") String carNumber, @Param("recId") String recId,
                        @Param("phone") String phone,@Param("MustId")String MustId);

    Integer updateM03(M03 m03);

    Integer updateStopTransport(@Param("stopTransport") String stopTransport, @Param("stopNumber") Integer stopNumber,
                                @Param("stopEndTime")String stopEndTime, @Param("recId")String recId);
}
