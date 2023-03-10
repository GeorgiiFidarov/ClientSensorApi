package ru.fidarov.ClientSensorApi.DTO;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import ru.fidarov.ClientSensorApi.model.Sensor;

public class MeasurementDTO {

    private float value;

    private boolean raining;
    @ManyToOne
    @JoinColumn(name="sensor",referencedColumnName = "name")
    private Sensor sensor;

    public MeasurementDTO() {
    }

    public MeasurementDTO(float value, boolean raining, Sensor sensor) {
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
}
