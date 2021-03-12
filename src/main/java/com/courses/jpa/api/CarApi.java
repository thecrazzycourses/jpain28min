package com.courses.jpa.api;

import com.courses.jpa.dto.CarDto;
import com.courses.jpa.entity.Car;
import com.courses.jpa.service.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarApi {

    private final CarService carService;

    public CarApi(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getOwnerDetailsById(@PathVariable Long id) {
        Car byId = carService.findById(id);
        return new ResponseEntity<Car>(byId, HttpStatus.OK);
    }

    @GetMapping
    public List<CarDto> findAll() {
        List<Car> all = carService.findAll();
        List<CarDto> dtoList = new ArrayList<>();
        for(Car car: all) {
            CarDto carDto = new CarDto();
            carDto.setId(car.getId());
            carDto.setModel(car.getModel());

            dtoList.add(carDto);
        }
        return dtoList;
    }
}
