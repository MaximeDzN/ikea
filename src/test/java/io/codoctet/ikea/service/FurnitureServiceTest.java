package io.codoctet.ikea.service;

import io.codoctet.ikea.domain.Food;
import io.codoctet.ikea.domain.Furniture;
import io.codoctet.ikea.repository.FoodRepository;
import io.codoctet.ikea.repository.FurnitureRepository;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FurnitureServiceTest {
    @Mock
    private FurnitureRepository furnitureRepository;

    @InjectMocks
    private FurnitureService furnitureService;


    @Test
    @DisplayName("furniture_getAll_returnAllFromDB")
    public void furniture_getAll_returnAllFromDB(){

        List<Furniture> furnitureList = new ArrayList<>();
        int intendedSize = 10;
        for(int i = 0; i < intendedSize; i++){
            furnitureList.add(Furniture.builder().id((long) i).build());
        }
        when(furnitureRepository.findAll()).thenReturn(furnitureList);
        Set<Furniture> furnitureSet = furnitureService.getAll();

        MatcherAssert.assertThat(furnitureSet.size(), Matchers.equalTo(intendedSize));
        verify(furnitureRepository).findAll();
    }

    @Test
    @DisplayName("furniture_getOne_returnOneFromDB")
    public void furniture_getOne_returnOneFromDB(){

        Furniture furnitureInit = Furniture.builder().id((long) 1L).build();
        when(furnitureRepository.findById(1L)).thenReturn(Optional.of(furnitureInit));
        Furniture furniture = furnitureService.get(1L);

        MatcherAssert.assertThat(furniture.toString(), Matchers.equalTo(furnitureInit.toString()));
        verify(furnitureRepository).findById(1L);
    }

    @Test
    @DisplayName("furniture_update_updateDB")
    public void furniture_update_updateDB(){

        Furniture furnitureInit = Furniture.builder().id((long) 1L).name("lit").build();
        when(furnitureRepository.findById(1L)).thenReturn(Optional.of(furnitureInit));
        Furniture furniture = furnitureService.get(1L);
        furniture.setName("table");
        furnitureService.update(furniture);

        MatcherAssert.assertThat(furniture.getName(), Matchers.equalTo("table"));
        verify(furnitureRepository).findById(1L);
        verify(furnitureRepository).save(furniture);
    }

    @Test
    @DisplayName("furniture_create_insertDB")
    public void furniture_create_insertDB(){

        Furniture furnitureInit = Furniture.builder().name("lit").build();
        when(furnitureRepository.save(furnitureInit)).thenReturn(furnitureInit);
        Furniture furniture = furnitureService.create(furnitureInit);
        MatcherAssert.assertThat(furniture.getName(), Matchers.equalTo("lit"));
        verify(furnitureRepository).save(furniture);
    }

    @Test
    @DisplayName("furniture_delete_deleteDB")
    public void furniture_delete_deleteDB(){
        Long id = 1L;
        doNothing().when(furnitureRepository).deleteById(id);
        furnitureService.delete(id);
        verify(furnitureRepository).deleteById(id);
    }

}