package com.komarmoss.model.vo;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.komarmoss.model.entity.TypeOfVehicleEntity;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;

@JacksonXmlRootElement(localName = "typeOfVehicle")
public class TypeOfVehicleVO implements ValueObject<TypeOfVehicleEntity> {

    @JacksonXmlProperty(localName = "id")
    private Integer id;
    @JacksonXmlProperty(localName = "name")
    private String name;
    @JacksonXmlProperty(localName = "minimumWeight")
    private Float minimumWeight;
    @JacksonXmlProperty(localName = "maximumWeight")
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
