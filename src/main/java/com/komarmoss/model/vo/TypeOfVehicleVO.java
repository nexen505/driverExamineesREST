package com.komarmoss.model.vo;

import com.komarmoss.model.entity.TypeOfVehicleEntity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "vehicleType")
@XmlAccessorType(XmlAccessType.FIELD)
public class TypeOfVehicleVO implements Serializable {
    private Integer id;
    private String name;
    private Float minimumWeight;
    private Float maximumWeight;

    public TypeOfVehicleVO() {
    }

    public TypeOfVehicleVO(Integer id) {
        this.id = id;
    }

    public TypeOfVehicleVO(TypeOfVehicleEntity entity) {
        if (entity != null) {
            id = entity.getId();
            name = entity.getName();
            minimumWeight = entity.getMinimumWeight();
            maximumWeight = entity.getMaximumWeight();
        }
    }

    public TypeOfVehicleEntity createEntity() {
        TypeOfVehicleEntity typeOfVehicleEntity = new TypeOfVehicleEntity();
        typeOfVehicleEntity.setId(id);
        typeOfVehicleEntity.setName(name);
        typeOfVehicleEntity.setMaximumWeight(maximumWeight);
        typeOfVehicleEntity.setMinimumWeight(minimumWeight);
        return typeOfVehicleEntity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getMinimumWeight() {
        return minimumWeight;
    }

    public void setMinimumWeight(Float minimumWeight) {
        this.minimumWeight = minimumWeight;
    }

    public Float getMaximumWeight() {
        return maximumWeight;
    }

    public void setMaximumWeight(Float maximumWeight) {
        this.maximumWeight = maximumWeight;
    }
}
