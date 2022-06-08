package io.codoctet.ikea.service;

import io.codoctet.ikea.domain.Food;
import io.codoctet.ikea.repository.FoodRepository;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FoodServiceTest {

    @Mock
    private FoodRepository foodRepository;

    @InjectMocks
    private FoodService foodService;


    @Test
    @DisplayName("food_getAll_returnAllFromDB")
    void food_getAll_returnAllFromDB(){

        List<Food> foodList = new ArrayList<>();
        int intendedSize = 10;
        for(int i = 0; i < intendedSize; i++){
            foodList.add(Food.builder().id((long) i).build());
        }
        when(foodRepository.findAll()).thenReturn(foodList);
        Set<Food> foodSet = foodService.getAll();

        MatcherAssert.assertThat(foodSet.size(), Matchers.equalTo(intendedSize));
        verify(foodRepository).findAll();

    }

}