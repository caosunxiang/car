package com.example.car.mapper.sqlserver;

import com.example.car.common.utils.entity.CarStatus;
import com.example.car.entity.M03;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;
import java.util.Map;

public interface M03Mapper {
    List<M03> selectM03(@Param("carNumber") String carNumber, @Param("recId") String recId,
                        @Param("phone") String phone,@Param("MustId")String MustId);

    Integer updateM03(M03 m03);

    Integer updateStopTransport(@Param("stopTransport") String stopTransport, @Param("stopNumber") Integer stopNumber,
                                @Param("stopEndTime")String stopEndTime, @Param("recId")String recId);

    List<CarStatus>selectM03Status(@Param("MustId")String MustId);

    void insertM03(M03 m03);
}
