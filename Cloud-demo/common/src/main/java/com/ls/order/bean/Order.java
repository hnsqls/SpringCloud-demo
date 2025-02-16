package com.ls.order.bean;

import com.ls.product.bean.Product;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.PrimitiveIterator;

/**
 * 订单
 */
@Data
public class Order {

    private Long id;

    private Long userId;

    private String username;

    private BigDecimal totalPrice;
    private List<Product> products;

    private  String addr;


}