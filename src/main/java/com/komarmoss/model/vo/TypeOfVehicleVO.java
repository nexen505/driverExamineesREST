package com.komarmoss.model.vo;

import com.komarmoss.model.entity.TypeOfVehicleEntity;
import org.jetbrains.annotations.NotNull;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collections;

@XmlRootElement(name = "vehicleType")
@XmlAccessorType(XmlAccessType.FIELD)
public class TypeOfVehicleVO implements ValueObject<TypeOfVehicleEntity> {
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

    @NotNull
    public TypeOfVehicleEntity createEntity() {
        return new TypeOfVehicleEntity(id, name, minimumWeight, maximumWeight, Collections.emptyList());
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
