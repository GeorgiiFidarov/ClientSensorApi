package ru.fidarov.ClientSensorApi.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.fidarov.ClientSensorApi.DTO.SensorDTO;
import ru.fidarov.ClientSensorApi.model.Measurement;

import java.util.List;

@Component
public class MeasurementDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MeasurementDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Measurement> getAllMeasurements(){
        return jdbcTemplate.query("SELECT * FROM measurement",
                new BeanPropertyRowMapper<>(Measurement.class));
    }

    public void save(Measurement measurement){
        jdbcTemplate.update("INSERT INTO measurement(value,raining,sensor,current)values (?,?,?,?)",
                measurement.getValue(),
                measurement.isRaining(),
                measurement.getSensor().getName(),
                measurement.getCurrent());
    }
}
