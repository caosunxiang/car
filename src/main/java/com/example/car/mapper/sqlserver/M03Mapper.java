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
                                @Param("stopEndTime")String stopEndTime, @Param("recId")String recId,@Param("stopRemark")String stopRemark,
                                @Param("MustId")String MustId);

    List<CarStatus>selectM03Status(@Param("MustId")String MustId,@Param("name")String name);

    void insertM03(M03 m03);

    List<String>selectPermitTruck(@Param("CarNo")String CarNo);

    Integer deleteM03(@Param("RecId") String RecId);

    void updateM0331(M03 m03);

    Integer selectM03Count(@Param("auditStatus")String auditStatus,@Param("MustId")String MustId);

    void updateM0329(M03 m03);

    List<M03> selectTransport();

    void updateTransportAuto(@Param("stopTransport") String stopTransport, @Param("stopNumber") Integer stopNumber,
                             @Param("stopEndTime")String stopEndTime);

    List<M03>selectM03Usable(@Param("carNumber") String carNumber, @Param("recId") String recId,
                             @Param("phone") String phone,@Param("MustId")String MustId);

    List<CarStatus>selectM03StatusUsable(@Param("MustId")String MustId,@Param("name")String name);
}
