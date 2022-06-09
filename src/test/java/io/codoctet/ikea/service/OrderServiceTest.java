package io.codoctet.ikea.service;

import io.codoctet.ikea.domain.Order;
import io.codoctet.ikea.repository.OrderRepository;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    private static Date date;

    @BeforeAll
    static void beforeAll(){
        date = new Date(0);
    }

    @Test
    @DisplayName("order_getAll_returnAllFromDB")
    void order_getAll_returnAllFromDB(){

        List<Order> orderList = new ArrayList<>();
        int intendedSize = 10;
        for(int i = 0; i < intendedSize; i++){
            orderList.add(Order.builder().id((long) i).build());
        }
        when(orderRepository.findAll()).thenReturn(orderList);
        Set<Order> orderSet = orderService.getAll();

        MatcherAssert.assertThat(orderSet.size(), Matchers.equalTo(intendedSize));
        verify(orderRepository).findAll();
    }

    @Test
    @DisplayName("order_getOne_returnOneFromDB")
    void order_getOne_returnOneFromDB(){

        Long expectedId = 1L;
        Optional<Order> expectedOrder = Optional.ofNullable(Order.builder().id(expectedId).date(date).build());

        when(orderRepository.findById(expectedId)).thenReturn(expectedOrder);
        Order order = orderService.get(1L);

        MatcherAssert.assertThat(order, Matchers.hasToString(expectedOrder.toString()));
        verify(orderRepository).findById(expectedId);
    }

    @Test
    @DisplayName("order_create_insertInDB")
    void order_create_insertInDB(){

        Order inputOrder = Order.builder().date(date).build();
        Order expectedOrder = Order.builder().id(1L).date(date).build();

        when(orderRepository.save(inputOrder)).thenReturn(expectedOrder);
        Order order = orderService.create(inputOrder);

        MatcherAssert.assertThat(order.getId(), Matchers.equalTo(expectedOrder.getId()));
        verify(orderRepository).save(inputOrder);
    }

    @Test
    @DisplayName("order_update_insertInDB")
    void order_update_insertInDB(){


        Order expectedOrder = Order.builder().id(1L).date(date).build();

        when(orderRepository.save(expectedOrder)).thenReturn(expectedOrder);
        Order order = orderService.update(expectedOrder);

        MatcherAssert.assertThat(order.getId(), Matchers.equalTo(expectedOrder.getId()));
        verify(orderRepository).save(expectedOrder);
    }

    @Test
    @DisplayName("order_delete_deleteFromDB")
    void order_delete_deleteFromDB(){

        Long deleteId = 1L;
        doNothing().when(orderRepository).deleteById(deleteId);
        orderService.delete(deleteId);
        verify(orderRepository).deleteById(deleteId);
    }


}