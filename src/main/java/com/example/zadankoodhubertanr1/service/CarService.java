package com.example.zadankoodhubertanr1.service;

import com.example.zadankoodhubertanr1.model.Car;
import com.example.zadankoodhubertanr1.model.CarDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public interface CarService {
    void addCar(Car car);
    Car findCarById(Long id) throws EntityNotFoundException;
    Page<Car> findAllCars(Pageable pageable);

    void deleteCarById(Long id);

    void updateOwner(Long id , Car car);
//    List<Car> findAllCarsList();
    List<Car> findBmwOwner(String producer);
}
