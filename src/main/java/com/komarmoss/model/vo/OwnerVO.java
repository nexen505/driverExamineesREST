package com.komarmoss.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.komarmoss.model.entity.OwnerEntity;
import com.komarmoss.model.entity.VehicleEntity;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@JacksonXmlRootElement(localName = "owner")
public class OwnerVO implements ValueObject<OwnerEntity> {

    @JacksonXmlProperty(localName = "id")
    private Integer id;
    @JacksonXmlProperty(localName = "name")
    private String name;
    @JacksonXmlProperty(localName = "patronymic")
    private String patronymic;
    @JacksonXmlProperty(localName = "surname")
    private String surname;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JacksonXmlProperty(localName = "dateOfBirth")
    private Date dateOfBirth;
    @JacksonXmlElementWrapper(localName = "vehicles")
    @JacksonXmlProperty(localName = "vehicle")
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

    @NotNull
    public OwnerEntity createEntity() {
        OwnerEntity ownerEntity = new OwnerEntity();
        ownerEntity.setId(id);
        ownerEntity.setName(name);
        ownerEntity.setPatronymic(patronymic);
        ownerEntity.setSurname(surname);
        ownerEntity.setDateOfBirth(dateOfBirth);
        ownerEntity.setTransportList(vehicles.parallelStream()
                .map(VehicleVO::createEntity)
                .collect(Collectors.toList()));
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
