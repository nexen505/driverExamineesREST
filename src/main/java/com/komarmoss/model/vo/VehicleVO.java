package com.komarmoss.model.vo;

import com.komarmoss.model.entity.OwnerEntity;
import com.komarmoss.model.entity.TypeOfVehicleEntity;
import com.komarmoss.model.entity.VehicleEntity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "vehicle")
@XmlAccessorType(XmlAccessType.FIELD)
public class VehicleVO implements Serializable {
    private Integer id;
    private String name;
    private String brand;
    private Integer yearOfIssue;
    private TypeOfVehicleVO type;
    private OwnerVO owner;

    public VehicleVO() {
    }

    public VehicleVO(Integer id) {
        this.id = id;
    }

    public VehicleVO(VehicleEntity entity) {
        if (entity != null) {
            id = entity.getId();
            name = entity.getName();
            brand = entity.getBrand();
            yearOfIssue = entity.getYearOfIssue();
            TypeOfVehicleEntity vehicleType = entity.getType();
            if (vehicleType != null)
                type = new TypeOfVehicleVO(vehicleType.getId());

            OwnerEntity owner = entity.getOwner();
            if (owner != null)
                this.owner = new OwnerVO(owner.getId());
        }
    }

    public VehicleEntity createEntity() {
        VehicleEntity vehicleEntity = new VehicleEntity();
        vehicleEntity.setId(id);
        vehicleEntity.setName(name);
        vehicleEntity.setBrand(brand);
        vehicleEntity.setYearOfIssue(yearOfIssue);

        if (type != null) {
            TypeOfVehicleEntity typeOfVehicleEntity = new TypeOfVehicleEntity();
            typeOfVehicleEntity.setId(type.getId());
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

    public TypeOfVehicleVO getType() {
        return type;
    }

    public void setType(TypeOfVehicleVO type) {
        this.type = type;
    }

    public OwnerVO getOwner() {
        return owner;
    }

    public void setOwner(OwnerVO owner) {
        this.owner = owner;
    }
}
