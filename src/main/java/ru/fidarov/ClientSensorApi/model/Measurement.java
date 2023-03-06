package ru.fidarov.ClientSensorApi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
@Entity
@Table(name="measurement")
public class Measurement {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotEmpty
    @Column(name = "value")
    private float value;
    @Column(name = "raining")
    private boolean raining;
    @ManyToOne
    @JoinColumn(name="sensor",referencedColumnName = "name")
    private Sensor sensor;

    public Measurement(){

    }

    public Measurement(float value, boolean raining, Sensor sensor) {
        this.value = value;
        this.raining = raining;
        this.sensor = sensor;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public boolean isRaining() {
        return raining;
    }

    public void setRaining(boolean raining) {
        this.raining = raining;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Measurement{" +
                "id=" + id +
                ", value=" + value +
                ", raining=" + raining +
                ", sensor=" + sensor +
                '}';
    }
}
