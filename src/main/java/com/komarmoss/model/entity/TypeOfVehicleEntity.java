package com.komarmoss.model.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 * Тип ТС.
 */
@Entity
@Table(name = "exdr_type_of_vehicle", schema = "public", catalog = "postgres")
public class TypeOfVehicleEntity implements Identifiable {
    /**
     * Идентификатор
     */
    private Integer id;
    /**
     * Название типа ТС
     */
    private String name;
    /**
     * Минимальный вес в тоннах
     */
    private Float minimumWeight;
    /**
     * Максимальный вес в тоннах
     */
    private Float maximumWeight;
    /**
     * ТС данного типа
     */
    private List<VehicleEntity> vehicles;

    public TypeOfVehicleEntity() {
    }

    public TypeOfVehicleEntity(Integer id, String name, Float minimumWeight, Float maximumWeight, List<VehicleEntity> vehicles) {
        this.id = id;
        this.name = name;
        this.minimumWeight = minimumWeight;
        this.maximumWeight = maximumWeight;
        this.vehicles = vehicles;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
    @Column(name = "minimum_weight")
    public Float getMinimumWeight() {
        return minimumWeight;
    }

    public void setMinimumWeight(Float minimumWeight) {
        this.minimumWeight = minimumWeight;
    }

    @Basic
    @Column(name = "maximum_weight")
    public Float getMaximumWeight() {
        return maximumWeight;
    }

    public void setMaximumWeight(Float maximumWeight) {
        this.maximumWeight = maximumWeight;
    }

    @OneToMany(mappedBy = "type")
    public List<VehicleEntity> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<VehicleEntity> vehicles) {
        this.vehicles = vehicles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypeOfVehicleEntity that = (TypeOfVehicleEntity) o;
        return Float.compare(that.minimumWeight, minimumWeight) == 0 &&
                Float.compare(that.maximumWeight, maximumWeight) == 0 &&
                Objects.equals(id, that.id) &&
                Objects.equals(vehicles, that.vehicles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, minimumWeight, maximumWeight, vehicles);
    }
}
