package ru.fidarov.ClientSensorApi.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "measurement")
public class Measurement implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "value")
    private float value;

    @Column(name="raining")
    private boolean raining;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="sensor")
    private Sensor sensor;

    @Column(name = "current")
    private LocalDateTime current;

    public Measurement() {

    }

    public Measurement(float value, boolean raining) {
        this.value = value;
        this.raining = raining;
    }

    public LocalDateTime getCurrent() {return current;}

    public void setCurrent(LocalDateTime current) {this.current = current;}

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

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
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
