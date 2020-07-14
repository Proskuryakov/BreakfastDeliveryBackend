package ru.relex.delivery.services.internal.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.relex.delivery.services.internal.OrderService;
import ru.relex.delivery.services.mapper.OrderMapper;
import ru.relex.delivery.services.model.order.CreatedOrder;
import ru.relex.delivery.services.model.order.NewOrder;

import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class OrderServiceImpl implements OrderService {
    private final Map<Long, CreatedOrder> ORDERS = new ConcurrentHashMap<>();

    private final AtomicLong lastId = new AtomicLong(0);

    private final OrderMapper orderMapper;

    @Autowired
    public OrderServiceImpl( final OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    @Override
    public CreatedOrder createOrder(NewOrder order) {
        // Выделить ID для пользователя
        //позже я добавлю проход по списку блюд с ценами
        double check = 0;
        long newId = lastId.addAndGet(1);
String currTime = Instant.now().toString();
        // Преобразовать NewUser в ExistingUser
        CreatedOrder createdOrder = orderMapper.fromNewOrder(order, newId, currTime, check  );

        // Сохранить в HashMap
        ORDERS.put(newId, createdOrder);

        // Вернуть ExistingUser клиенту
        return createdOrder;
    }

    @Override
    public CreatedOrder getOrderById(long id) {
        return ORDERS.get(id);
    }

    @Override
    public boolean deleteOrderById(long id) {
              return   ORDERS.remove(id) != null;
    }
}
