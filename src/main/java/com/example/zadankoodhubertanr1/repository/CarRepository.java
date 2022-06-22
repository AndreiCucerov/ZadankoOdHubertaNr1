package com.example.zadankoodhubertanr1.repository;

import com.example.zadankoodhubertanr1.model.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car,Long> {
    public List<Car> findAllByProducer(String producer);
}
