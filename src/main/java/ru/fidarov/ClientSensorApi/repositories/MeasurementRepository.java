package ru.fidarov.ClientSensorApi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.fidarov.ClientSensorApi.model.Measurement;

public interface MeasurementRepository extends JpaRepository<Measurement,Integer> {

}
