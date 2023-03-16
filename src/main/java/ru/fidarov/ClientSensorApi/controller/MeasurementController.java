package ru.fidarov.ClientSensorApi.controller;


import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.fidarov.ClientSensorApi.DTO.MeasurementDTO;
import ru.fidarov.ClientSensorApi.model.Measurement;
import ru.fidarov.ClientSensorApi.service.MeasurementService;

import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping
    private List<MeasurementDTO> getMeasurements(){
        return measurementService.getAllMeasurements().
                stream().
                map(this::convertToMeasurementDTO).
                collect(Collectors.toList());
    }
//    @GetMapping("/{sensor}")
//    private MeasurementDTO getMeasurement(@PathVariable String sensor){
//        Measurement foundMeas = measurementService.getMeasurement(sensor);
//        return convertToMeasurementDTO(foundMeas);
//    }

    @RequestMapping("/add")
    public ResponseEntity<HttpStatus> addMeasurement(@RequestBody @Valid Measurement measurement,
                                                     BindingResult bindingResult)
    {
        measurementService.addMeasurement(measurement);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private Measurement convertToMeasurement(MeasurementDTO measurementDTO){
        return modelMapper.map(measurementDTO, Measurement.class);
    }

    private MeasurementDTO convertToMeasurementDTO(Measurement measurement){
        return modelMapper.map(measurement, MeasurementDTO.class);
    }
}
