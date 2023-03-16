package ru.fidarov.ClientSensorApi.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class SensorDTO {

    @NotEmpty(message = "Sensor's name can't be empty")
    @Size(min = 3,max = 250,message = "Amount of characters have to be between 3 and 250")
    private String name;

    public SensorDTO(String name) {
        this.name = name;
    }

    public SensorDTO(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "SensorDTO{" +
                "name='" + name + '\'' +
                '}';
    }
}
