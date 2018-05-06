package com.komarmoss.service;

import com.komarmoss.model.vo.TypeOfVehicleVO;
import com.komarmoss.model.vo.VehicleVO;

import java.util.List;

public interface VehicleService {

    List<VehicleVO> findVehicles();

    VehicleVO findVehicle(Integer id);

    VehicleVO saveOrUpdateVehicle(VehicleVO vehicleVO);

    boolean removeVehicle(Integer id);

    List<TypeOfVehicleVO> getTypesOfVehicles();

}
