package com.courses.jpa.dto;

import com.courses.jpa.entity.Car;
import lombok.Data;

@Data
public class CarDto {

    private Long id;
    private String model;

    public CarDto() {
    }

    public CarDto(String model) {
        this.model = model;
    }

    public static CarDto from(Car car) {
        CarDto carDto = new CarDto();
        carDto.setId(car.getId());
        carDto.setModel(car.getModel());
        return carDto;
    }
}
