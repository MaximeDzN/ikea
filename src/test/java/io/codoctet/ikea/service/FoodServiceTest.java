package io.codoctet.ikea.service;

import io.codoctet.ikea.domain.Food;
import io.codoctet.ikea.repository.FoodRepository;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class FoodServiceTest {

    @Mock
     private FoodRepository foodRepository;

    @InjectMocks
    private FoodService foodService;


    @Test
    @DisplayName("food_getAll_returnAllFromDB")
    public void food_getAll_returnAllFromDB(){

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

    @Test
    @DisplayName("food_getOne_returnOneFromDB")
    public void food_getOne_returnOneFromDB(){

        Long expectedId = 1L;
        Optional<Food> expectedFood = Optional.ofNullable(Food.builder().id(expectedId).name("Boulette suédoise").price(15).build());

        when(foodRepository.findById(expectedId)).thenReturn(expectedFood);
        Food food = foodService.get(1L);

        MatcherAssert.assertThat(food, Matchers.hasToString("Food(id=1, price=15.0, name=Boulette suédoise)"));
        verify(foodRepository).findById(expectedId);
    }

    @Test
    @DisplayName("food_create_insertInDB")
    public void food_create_insertInDB(){

        Food inputFood = Food.builder().name("Boulette suédoise").price(15).build();
        Food expectedFood = Food.builder().id(1L).name("Boulette suédoise").price(15).build();

        when(foodRepository.save(inputFood)).thenReturn(expectedFood);
        Food food = foodService.create(inputFood);

        MatcherAssert.assertThat(food.getId(), Matchers.equalTo(expectedFood.getId()));
        verify(foodRepository).save(inputFood);
    }

    @Test
    @DisplayName("food_update_insertInDB")
    public void food_update_insertInDB(){


        Food expectedFood = Food.builder().id(1L).name("Boulette marocaine").price(39).build();

        when(foodRepository.save(expectedFood)).thenReturn(expectedFood);
        Food food = foodService.update(expectedFood);

        MatcherAssert.assertThat(food.getId(), Matchers.equalTo(expectedFood.getId()));
        verify(foodRepository).save(expectedFood);
    }

    @Test
    @DisplayName("food_delete_deleteFromDB")
    public void food_delete_deleteFromDB(){

        Long deleteId = 1L;
        doNothing().when(foodRepository).deleteById(deleteId);
        foodService.delete(deleteId);
        verify(foodRepository).deleteById(deleteId);
    }


}