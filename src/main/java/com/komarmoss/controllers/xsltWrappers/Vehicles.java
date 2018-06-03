package com.komarmoss.controllers.xsltWrappers;

import com.komarmoss.model.vo.VehicleVO;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "vehicles")
public class Vehicles implements Serializable {

    @XmlElement(name = "vehicle", type = VehicleVO.class)
    private List<VehicleVO> vehicles = new ArrayList<>();

    public Vehicles() {
    }

    public Vehicles(List<VehicleVO> vehicles) {
        this.vehicles = vehicles;
    }

    public List<VehicleVO> getVehicles() {
        return vehicles;
    }
}
