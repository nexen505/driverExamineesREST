package com.komarmoss.model.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * Транспортное средство (ТС).
 */
@Entity
@Table(name = "exdr_transport", schema = "public", catalog = "postgres")
public class VehicleEntity implements Identifiable {
    /**
     * Идентификатор
     */
    private Integer id;
    /**
     * Название модели транспортного средства
     */
    private String name;
    /**
     * Марка модели транспортного средства
     */
    private String brand;
    /**
     * Год выпуска транспортного средства
     */
    private Integer yearOfIssue;
    /**
     * Владелец транспортного средства
     */
    private OwnerEntity owner;
    /**
     * Тип транспортного средства
     */
    private TypeOfVehicleEntity type;

    public VehicleEntity() {
    }

    public VehicleEntity(Integer id, String name, String brand, Integer yearOfIssue, OwnerEntity owner, TypeOfVehicleEntity type) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.yearOfIssue = yearOfIssue;
        this.owner = owner;
        this.type = type;
    }


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_transport_seq_name")
//    @SequenceGenerator(name = "id_transport_seq_name", sequenceName = "id_transport_seq", allocationSize = 1)
    public Integer getId() {
        return id;
    }

    public void setId(Integer idTransport) {
        this.id = idTransport;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String nameTransport) {
        this.name = nameTransport;
    }

    @Basic
    @Column(name = "brand")
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brandTransport) {
        this.brand = brandTransport;
    }

    @Basic
    @Column(name = "year_of_issue")
    public Integer getYearOfIssue() {
        return yearOfIssue;
    }

    public void setYearOfIssue(Integer yearOfIssue) {
        this.yearOfIssue = yearOfIssue;
    }

    @ManyToOne
    @JoinColumn(name = "id_owner")
    public OwnerEntity getOwner() {
        return owner;
    }

    public void setOwner(OwnerEntity owner) {
        this.owner = owner;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "type_id")
    public TypeOfVehicleEntity getType() {
        return type;
    }

    public void setType(TypeOfVehicleEntity type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VehicleEntity that = (VehicleEntity) o;
        return Objects.equals(yearOfIssue, that.yearOfIssue) &&
                Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(brand, that.brand) &&
                Objects.equals(owner, that.owner) &&
                Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, brand, yearOfIssue, owner, type);
    }
}
