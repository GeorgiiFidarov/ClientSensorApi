package ru.fidarov.ClientSensorApi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.fidarov.ClientSensorApi.DAO.MeasurementDAO;
import ru.fidarov.ClientSensorApi.model.Measurement;
import ru.fidarov.ClientSensorApi.model.Sensor;
import ru.fidarov.ClientSensorApi.repositories.MeasurementRepository;
import ru.fidarov.ClientSensorApi.repositories.SensorRepository;

import java.time.LocalDateTime;
import java.util.List;

@Transactional(readOnly = true)
@Service
public class MeasurementService {
    private final MeasurementRepository measurementRepository;
    private final SensorRepository sensorRepository;
    private final MeasurementDAO measurementDAO;

    @Autowired
    public MeasurementService(MeasurementRepository measurementRepository, SensorRepository sensorRepository, MeasurementDAO measurementDAO) {
        this.measurementRepository = measurementRepository;
        this.sensorRepository = sensorRepository;
        this.measurementDAO = measurementDAO;
    }
    public List<Measurement> getAllMeasurements(){
        return measurementDAO.getAllMeasurements();
    }
//    public Measurement getMeasurement(String sensor){
//        return measurementRepository.findMeasurementBySensor(sensor);
//    }
    @Transactional
    //сюда приходит межурмент с листом из сенсоров
    public void addMeasurement(Measurement measurement){
        enrichMeasurement(measurement);
        measurementDAO.save(measurement);
    }

    private void enrichMeasurement(Measurement measurement){
        measurement.setCurrent(LocalDateTime.now());
    }
}
