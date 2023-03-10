package ru.fidarov.ClientSensorApi.controller;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.fidarov.ClientSensorApi.DTO.SensorDTO;
import ru.fidarov.ClientSensorApi.model.Sensor;
import ru.fidarov.ClientSensorApi.service.SensorService;
import ru.fidarov.ClientSensorApi.util.*;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sensors")
public class SensorController {

    private final SensorService sensorService;
    private final ModelMapper modelMapper;
    private final SensorValidator sensorValidator;

    @Autowired
    public SensorController(SensorService sensorService,
                            ModelMapper modelMapper, SensorValidator sensorValidator) {
        this.sensorService = sensorService;
        this.modelMapper = modelMapper;
        this.sensorValidator = sensorValidator;
    }

    //выдаёт по адресу /sensors все сенсоры в базе
    @GetMapping()
    public List<SensorDTO> getSensors(){
        return sensorService.getAllSensors().stream().map(this::convertToSensorDTO)
                .collect(Collectors.toList());
    }
    @GetMapping("/{id}")
    public SensorDTO getSensor(@PathVariable int id){
        return convertToSensorDTO(sensorService.getSensorById(id));
    }
    @PostMapping("/delete/{id}")
    public void delete(@PathVariable int id){
        sensorService.delete(id);;
    }
    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid SensorDTO sensorDTO,
                                             BindingResult bindingResult){
        sensorValidator.validate(convertToSensor(sensorDTO), bindingResult);
        System.out.println(bindingResult);
        if (bindingResult.hasErrors()){
            StringBuilder errorMessage = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors){
                errorMessage
                        .append(error.getField())
                        .append(" - ")
                        .append(error.getDefaultMessage())
                        .append(";");
            }
            System.out.println(errorMessage);
            throw new SensorNotCreatedException(errorMessage.toString());
        }
        sensorService.save(convertToSensor(sensorDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @ExceptionHandler
    private ResponseEntity<SensorErrorResponse> handleException(DoubleSensorNameError e){
        SensorErrorResponse errorResponse = new SensorErrorResponse(
                "Duplicate sensor name",System.currentTimeMillis()
        );
        return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    private ResponseEntity<SensorErrorResponse> handleException(SensorNotFoundException e){
        SensorErrorResponse errorResponse = new SensorErrorResponse(
                "Sensor with this Id was not found",System.currentTimeMillis()
        );
        return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    private ResponseEntity<SensorErrorResponse> handleException(SensorNotCreatedException e){
        SensorErrorResponse errorResponse = new SensorErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler
    private ResponseEntity<SensorErrorResponse> handleException(SensorDeleteError e){
        SensorErrorResponse sensorErrorResponse = new SensorErrorResponse(
                "Can't delete this sensor from database",
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(sensorErrorResponse,HttpStatus.BAD_REQUEST);
    }

    private Sensor convertToSensor(SensorDTO sensorDTO){
        return modelMapper.map(sensorDTO,Sensor.class);
    }
    private SensorDTO convertToSensorDTO(Sensor sensor){
        return modelMapper.map(sensor, SensorDTO.class);
    }
}
