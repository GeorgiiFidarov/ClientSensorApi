package ru.fidarov.ClientSensorApi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.fidarov.ClientSensorApi.model.Measurement;
import ru.fidarov.ClientSensorApi.model.Sensor;
import ru.fidarov.ClientSensorApi.repositories.MeasurementRepository;

import java.time.LocalDateTime;

@Service
@Transactional(readOnly = true)
public class MeasurementService {

    private final MeasurementRepository measurementRepository;

    @Autowired
    public MeasurementService(MeasurementRepository measurementRepository) {
        this.measurementRepository = measurementRepository;
    }

    @Transactional
    public void addMeasurement(Measurement measurement){
        //enrichSensor(measurement);
        measurementRepository.save(measurement);
    }

//    private void enrichSensor(Measurement measurement){
//        measurement.setCurrent_time(LocalDateTime.now());
//    }
}
