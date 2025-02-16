package com.ls.order.service;

import com.ls.order.bean.Order;

public interface OrderService {
    Order getOrder(Long userId, Long productId);
}
