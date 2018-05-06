package com.komarmoss.model.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Владелец транспортных средств. Имеет удостоверения и перечень транспортных средств.
 */
@Entity
@Table(name = "exdr_owner", schema = "public", catalog = "postgres")
public class OwnerEntity implements Identifiable {
    /**
     * Идентификатор
     */
    private Integer id;
    /**
     * Имя владельца
     */
    private String name;
    /**
     * Отчество владельца
     */
    private String patronymic;
    /**
     * Фамилия владельца
     */
    private String surname;
    /**
     * Дата рождения
     */
    private Date dateOfBirth;
    /**
     * Транспортные средства владельца
     */
    private List<VehicleEntity> transportList;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer idOwner) {
        this.id = idOwner;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "patronymic")
    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    @Basic
    @Column(name = "surname")
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Basic
    @Column(name = "date_of_birth")
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @OneToMany(mappedBy = "owner", cascade = {CascadeType.ALL})
    public List<VehicleEntity> getTransportList() {
        return transportList;
    }

    public void setTransportList(List<VehicleEntity> transportList) {
        this.transportList = transportList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OwnerEntity that = (OwnerEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(patronymic, that.patronymic) &&
                Objects.equals(surname, that.surname) &&
                Objects.equals(dateOfBirth, that.dateOfBirth) &&
                Objects.equals(transportList, that.transportList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, patronymic, surname, dateOfBirth, transportList);
    }
}
