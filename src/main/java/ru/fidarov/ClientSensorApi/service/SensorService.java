package ru.fidarov.ClientSensorApi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.fidarov.ClientSensorApi.model.Sensor;
import ru.fidarov.ClientSensorApi.repositories.SensorRepository;
import ru.fidarov.ClientSensorApi.sensorExceptions.SensorDeleteError;
import ru.fidarov.ClientSensorApi.sensorExceptions.SensorNotFoundException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SensorService {

    private final SensorRepository sensorRepository;

    @Autowired
    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }
    public List<Sensor> getAllSensors(){
        return sensorRepository.findAll();
    }
    public Sensor getSensorById(int id){
        return sensorRepository.findById(id).orElseThrow(SensorNotFoundException::new);
    }
    public Optional<Sensor> getName(String name){
        return Optional.ofNullable(sensorRepository.findSensorByName(name));
    }

    @Transactional
    public void save(Sensor sensor){
        enrichSensor(sensor);
        sensorRepository.save(sensor);
    }
    @Transactional
    public void delete(int id){
        if (sensorRepository.findById(id).isPresent()){
            sensorRepository.deleteById(id);
        }else
            throw new SensorDeleteError();
    }
    private void enrichSensor(Sensor sensor){
        sensor.setCreated_at(LocalDateTime.now());
        sensor.setUpdated_at(LocalDateTime.now());
    }


}
