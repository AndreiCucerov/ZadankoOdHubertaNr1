package com.example.zadankoodhubertanr1.controller;

import com.example.zadankoodhubertanr1.model.Car;
import com.example.zadankoodhubertanr1.model.CarDto;
import com.example.zadankoodhubertanr1.Requests.CreateCarRequest;
import com.example.zadankoodhubertanr1.Requests.UpdateCarRequest;
import com.example.zadankoodhubertanr1.service.CarService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/cars")
@RequiredArgsConstructor
public class CarController {

    private final ModelMapper modelMapper;
    private final CarService carService;

    @GetMapping("/get/{id}")
    public ResponseEntity<CarDto> getCarById(@PathVariable("id") Long id) throws EntityNotFoundException {
        CarDto carDto = modelMapper.map(carService.findCarById(id), CarDto.class);
        return ResponseEntity.ok(carDto);
    }

    @PostMapping
    public ResponseEntity<CarDto> addCar(@RequestBody CreateCarRequest createCarRequest) {
        Car mappedCar = modelMapper.map(createCarRequest, Car.class);
        carService.addCar(mappedCar);
        CarDto carDto = modelMapper.map(mappedCar, CarDto.class);
        return ResponseEntity.ok(carDto);
    }

    @GetMapping
    public ResponseEntity<Page<CarDto>> showAllCars ( @PageableDefault Pageable pageable){
        Page<CarDto> carDto = carService.findAllCars(pageable)
                .map(x -> modelMapper.map(x, CarDto.class));
        return ResponseEntity.ok(carDto);
    }

    // /cars?page=&size=5

    @PostMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam long id){
        carService.deleteCarById(id);
        return  ResponseEntity.noContent().build();
    }
    @PutMapping("/updateOwner")
    ResponseEntity<?>updateOwner(@RequestBody UpdateCarRequest updateCarRequest){
        carService.updateOwner(updateCarRequest.getId(),modelMapper.map(updateCarRequest, Car.class));

        return ResponseEntity.noContent().build();
    }


//    @GetMapping("/searchByProducer")
//    ResponseEntity <List<CarDto>>searchByProducer(
//            @RequestParam String producer){
//
//
//        return ResponseEntity.ok(carService.findBmwOwner(producer).stream().map(x->modelMapper.map(x,CarDto.class)).collect(Collectors.toList()));
//    }
    @GetMapping("/searchByProducer")
    ResponseEntity <Page<CarDto>>searchByProducer(
            @RequestParam String producer,
    @PageableDefault Pageable page){
        List<CarDto>cars=carService.findBmwOwner(producer).stream().map(x->modelMapper.map(x,CarDto.class)).collect(Collectors.toList());
        Page<CarDto> carsDto =  new PageImpl<>(cars,page,cars.size());
    return ResponseEntity.ok(carsDto);
    }



        //TODO: metoda znajdująca wszystkie samochody, usuwająca samochód po ID, zmieniająca np. właściciela danego samochodu, znajdująca wszystkie samochody marki bmw


//    @GetMapping("/")
//    String home(){
//        return "index";
//    }
//    @GetMapping("/save")
//    String save(Model model){
//        model.addAttribute("car",new CarDto());
//        return "save";
//    }
//    @PostMapping("/saving")
//    String saving(@ModelAttribute("car")CarDto carDto){
//            return "succed";
//    }
//
//    @GetMapping("/show")
//    ResponseEntity<List<CarDto>> show(){
//        List<CarDto>cars= carRepository.findAll().stream()
//                .map(x->modelMapper.map(x, CarDto.class)).collect(Collectors.toList());
//        if(cars!=null){
//            return ResponseEntity.ok(cars);
//        }else return ResponseEntity.notFound().build();
//
//    }
//

}
