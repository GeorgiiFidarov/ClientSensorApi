package ru.fidarov.ClientSensorApi.controller;


import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.fidarov.ClientSensorApi.DTO.MeasurementDTO;
import ru.fidarov.ClientSensorApi.model.Measurement;
import ru.fidarov.ClientSensorApi.service.MeasurementService;

@RestController
@RequestMapping("/measurements")
public class MeasurementController {

    private final MeasurementService measurementService;
    private final ModelMapper modelMapper;

    @Autowired
    public MeasurementController(MeasurementService measurementService, ModelMapper modelMapper){
        this.measurementService = measurementService;
        this.modelMapper = modelMapper;
    }

    @RequestMapping("/add")
    public ResponseEntity<HttpStatus> addMeasurement(@RequestBody @Valid MeasurementDTO measurementDTO,
                                                     BindingResult bindingResult){

        measurementService.addMeasurement(convertToMeasurement(measurementDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }
    private Measurement convertToMeasurement(MeasurementDTO measurementDTO){
        return modelMapper.map(measurementDTO, Measurement.class);
    }

}
