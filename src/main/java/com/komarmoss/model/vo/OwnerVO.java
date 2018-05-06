package com.komarmoss.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.komarmoss.model.entity.OwnerEntity;
import com.komarmoss.model.entity.VehicleEntity;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@XmlRootElement(name = "owner")
@XmlAccessorType(XmlAccessType.FIELD)
public class OwnerVO implements Serializable {

    private Integer id;
    private String name;
    private String patronymic;
    private String surname;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dateOfBirth;
    @XmlElementWrapper(name = "vehicles")
    @XmlElement(name = "vehicle")
    private List<VehicleVO> vehicles = Collections.emptyList();

    public OwnerVO() {
    }

    public OwnerVO(Integer id) {
        this.id = id;
    }

    public OwnerVO(OwnerEntity entity) {
        if (entity != null) {
            id = entity.getId();
            name = entity.getName();
            patronymic = entity.getPatronymic();
            surname = entity.getSurname();
            dateOfBirth = entity.getDateOfBirth();

            List<VehicleEntity> transportList = entity.getTransportList();
            if (transportList != null)
                vehicles = transportList.parallelStream()
                        .map(VehicleVO::new)
                        .collect(Collectors.toList());

        }
    }

    public OwnerEntity createEntity() {
        OwnerEntity ownerEntity = new OwnerEntity();
        ownerEntity.setId(id);
        ownerEntity.setName(name);
        ownerEntity.setPatronymic(patronymic);
        ownerEntity.setSurname(surname);
        ownerEntity.setDateOfBirth(dateOfBirth);
        ownerEntity.setTransportList(vehicles.parallelStream().map(VehicleVO::createEntity).filter(Objects::nonNull).collect(Collectors.toList()));
        return ownerEntity;
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

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public List<VehicleVO> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<VehicleVO> vehicles) {
        this.vehicles = vehicles;
    }

}
