package com.example.zadankoodhubertanr1.service;

import com.example.zadankoodhubertanr1.model.Car;
import com.example.zadankoodhubertanr1.model.CarDto;
import com.example.zadankoodhubertanr1.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService{
    private final CarRepository carRepository;

    @Override
    public void addCar(Car car) {
    carRepository.save(car);
    }

    @Override
    public Car findCarById(Long id) {
        return carRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Car with ID: " + id + " does not exist"));
    }

    @Override
    public Page<Car> findAllCars(Pageable pageable) {
        Page<Car> all = carRepository.findAll(pageable);
        return all;
    }

    @Override
    public void deleteCarById(Long id) {
        carRepository.deleteById(id);
    }

    @Override
    public void updateOwner(Long id, Car car) {
        Car carToUpdate = carRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("Car with Id " + id + " does not exist"));
        carToUpdate.setOwner(car.getOwner());
        carRepository.save(carToUpdate);


        //carRepository.findById(id).map(x->x.setOwner(car.getOwner()));
    }

    @Override
    public List<Car> findBmwOwner(String producer) {

        return carRepository.findAllByProducer(producer);
    }

}
