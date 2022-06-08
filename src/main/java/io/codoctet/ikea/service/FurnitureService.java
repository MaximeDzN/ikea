package io.codoctet.ikea.service;


import io.codoctet.ikea.domain.Food;
import io.codoctet.ikea.domain.Furniture;
import io.codoctet.ikea.repository.FurnitureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.Set;

@Service
public class FurnitureService {
    private FurnitureRepository furnitureRepository;

    @Autowired
    public FurnitureService(FurnitureRepository furnitureRepository) {
        this.furnitureRepository = furnitureRepository;
    }

    public Furniture create(Furniture furniture){
        return furnitureRepository.save(furniture);
    }

    public Furniture update(Furniture furniture){
        return furnitureRepository.save(furniture);
    }

    public void delete(Long id) {
        furnitureRepository.deleteById(id);
    }

    public Furniture get(Long id){
        return furnitureRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Set<Furniture> getAll(){
        return new HashSet<>(furnitureRepository.findAll());
    }


}
