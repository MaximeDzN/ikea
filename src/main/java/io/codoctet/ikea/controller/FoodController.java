package io.codoctet.ikea.controller;

import io.codoctet.ikea.domain.Food;
import io.codoctet.ikea.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("food")
public class FoodController {

    private FoodService foodService;

    @Autowired
    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Food> get(Long id){
        return new ResponseEntity<>(foodService.get(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Set<Food>> getAll(){
        return new ResponseEntity<>(foodService.getAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Food> create(@RequestBody Food food){
        return new ResponseEntity<>(foodService.create(food), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Food> update(@RequestBody Food food){
        return new ResponseEntity<>(foodService.update(food), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(Long id){
        foodService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
