package ru.fidarov.ClientSensorApi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.fidarov.ClientSensorApi.model.Sensor;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Integer>
{

    void findByName(String name);

    Sensor findSensorByName(String name);
}
