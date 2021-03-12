package com.courses.jpa.service;

import com.courses.jpa.entity.Car;
import com.courses.jpa.repositories.CarRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CarService {

    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Car findById(Long id) {

        Optional<Car> car = carRepository.findById(id);
        if (car.isPresent()) {
            return car.get();
        }
        return null;
    }

    public List<Car> findAll() {
        List<Car> all = carRepository.findAll();
        return all;
    }
}
