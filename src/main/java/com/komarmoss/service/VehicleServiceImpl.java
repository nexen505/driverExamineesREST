package com.komarmoss.service;

import com.komarmoss.messaging.model.vo.ChangesMessageVO;
import com.komarmoss.messaging.service.MessageSender;
import com.komarmoss.model.dao.TypeOfVehicleDAO;
import com.komarmoss.model.dao.VehicleDAO;
import com.komarmoss.model.entity.VehicleEntity;
import com.komarmoss.model.vo.TypeOfVehicleVO;
import com.komarmoss.model.vo.VehicleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehicleServiceImpl implements VehicleService {

    private final VehicleDAO vehicleDAO;

    private final TypeOfVehicleDAO typeOfVehicleDAO;

    private final MessageSender messageSender;

    @Autowired
    public VehicleServiceImpl(VehicleDAO vehicleDAO, TypeOfVehicleDAO typeOfVehicleDAO, MessageSender messageSender) {
        this.vehicleDAO = vehicleDAO;
        this.typeOfVehicleDAO = typeOfVehicleDAO;
        this.messageSender = messageSender;
    }

    @Override
    @Transactional
    public List<VehicleVO> findVehicles() {
        return vehicleDAO.getAllItems()
                .parallelStream()
                .map(VehicleVO::new)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public VehicleVO findVehicle(Integer id) {
        return new VehicleVO(vehicleDAO.getItemById(id));
    }

    @Override
    @Transactional
    public VehicleVO saveOrUpdateVehicle(VehicleVO vehicle) {
        VehicleEntity vehicleEntity = vehicle.createEntity();
        vehicleDAO.saveOrUpdateItem(vehicleEntity);
        messageSender.send(vehicle.getId() == null ? ChangesMessageVO.createCreateMessage(vehicle) : ChangesMessageVO.createUpdateMessage(vehicle));
        return findVehicle(vehicleEntity.getId());
    }

    @Override
    @Transactional
    public boolean removeVehicle(Integer id) {
        if (id != null) {
            vehicleDAO.removeItemById(id);
            messageSender.send(ChangesMessageVO.createDeleteMessage(id));
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public List<TypeOfVehicleVO> getTypesOfVehicles() {
        return typeOfVehicleDAO.getAllItems()
                .parallelStream()
                .map(TypeOfVehicleVO::new)
                .collect(Collectors.toList());
    }

}
