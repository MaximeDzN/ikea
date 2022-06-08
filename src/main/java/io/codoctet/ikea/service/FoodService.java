package io.codoctet.ikea.service;


import io.codoctet.ikea.domain.Food;
import io.codoctet.ikea.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.Set;

@Service
public class FoodService {
    private FoodRepository foodRepository;

    @Autowired
    public FoodService(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    public Food create(Food food){
        return foodRepository.save(food);
    }

    public Food update(Food food){
        return foodRepository.save(food);
    }

    public void delete(Long id) {
        foodRepository.deleteById(id);
    }

    public Food get(Long id){
        return foodRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Set<Food> getAll(){
        return new HashSet<>(foodRepository.findAll());
    }


}
