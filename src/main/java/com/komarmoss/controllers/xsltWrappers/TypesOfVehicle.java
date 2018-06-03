package com.komarmoss.controllers.xsltWrappers;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.komarmoss.model.vo.TypeOfVehicleVO;
import com.komarmoss.model.vo.VehicleVO;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "typesOfVehicle")
public class TypesOfVehicle implements Serializable {

    @JacksonXmlElementWrapper(localName = "typesOfVehicle")
    @JacksonXmlProperty(localName = "typeOfVehicle")
    @XmlElement(name = "typeOfVehicle", type = VehicleVO.class)
    private List<TypeOfVehicleVO> typesOfVehicle = new ArrayList<>();

    public TypesOfVehicle() {
    }

    public TypesOfVehicle(List<TypeOfVehicleVO> typesOfVehicle) {
        this.typesOfVehicle = typesOfVehicle;
    }

    public List<TypeOfVehicleVO> getTypesOfVehicle() {
        return typesOfVehicle;
    }
}
