package com.komarmoss.model.vo;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.komarmoss.model.entity.OwnerEntity;
import com.komarmoss.model.entity.TypeOfVehicleEntity;
import com.komarmoss.model.entity.VehicleEntity;
import org.jetbrains.annotations.NotNull;

import javax.xml.bind.annotation.XmlElement;

@JacksonXmlRootElement(localName = "vehicle")
public class VehicleVO implements ValueObject<VehicleEntity> {
    @JacksonXmlProperty(localName = "id")
    private Integer id;
    @JacksonXmlProperty(localName = "name")
    private String name;
    @JacksonXmlProperty(localName = "brand")
    private String brand;
    @JacksonXmlProperty(localName = "yearOfIssue")
    private Integer yearOfIssue;
    @JacksonXmlProperty(localName = "typeOfVehicle")
    private TypeOfVehicleVO typeOfVehicle;
    @JacksonXmlProperty(localName = "owner")
    private OwnerVO owner;

    public VehicleVO() {
    }

    public VehicleVO(VehicleEntity entity) {
        if (entity != null) {
            id = entity.getId();
            name = entity.getName();
            brand = entity.getBrand();
            yearOfIssue = entity.getYearOfIssue();
            TypeOfVehicleEntity vehicleType = entity.getType();
            if (vehicleType != null)
                typeOfVehicle = new TypeOfVehicleVO(vehicleType.getId());

            OwnerEntity owner = entity.getOwner();
            if (owner != null)
                this.owner = new OwnerVO(owner.getId());
        }
    }

    @NotNull
    public VehicleEntity createEntity() {
        VehicleEntity vehicleEntity = new VehicleEntity();
        vehicleEntity.setId(id);
        vehicleEntity.setName(name);
        vehicleEntity.setBrand(brand);
        vehicleEntity.setYearOfIssue(yearOfIssue);

        if (typeOfVehicle != null) {
            TypeOfVehicleEntity typeOfVehicleEntity = new TypeOfVehicleEntity();
            typeOfVehicleEntity.setId(typeOfVehicle.getId());
            vehicleEntity.setType(typeOfVehicleEntity);
        }

        if (owner != null) {
            OwnerEntity ownerEntity = new OwnerEntity();
            ownerEntity.setId(owner.getId());
            vehicleEntity.setOwner(ownerEntity);
        }

        return vehicleEntity;
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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Integer getYearOfIssue() {
        return yearOfIssue;
    }

    public void setYearOfIssue(Integer yearOfIssue) {
        this.yearOfIssue = yearOfIssue;
    }

    @XmlElement(name = "typeOfVehicle", namespace = "driverApp")
    public TypeOfVehicleVO getTypeOfVehicle() {
        return typeOfVehicle;
    }

    public void setTypeOfVehicle(TypeOfVehicleVO type) {
        this.typeOfVehicle = type;
    }

    @XmlElement(name = "owner", namespace = "driverApp")
    public OwnerVO getOwner() {
        return owner;
    }

    public void setOwner(OwnerVO owner) {
        this.owner = owner;
    }
}
