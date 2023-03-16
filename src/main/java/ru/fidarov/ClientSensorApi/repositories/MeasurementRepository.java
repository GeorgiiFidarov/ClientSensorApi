package ru.fidarov.ClientSensorApi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.fidarov.ClientSensorApi.model.Measurement;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement,Integer> {
}
