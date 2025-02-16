package com.ls.product.service.impl;

import com.ls.product.bean.Product;
import com.ls.product.service.ProductService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
public class ProductServiceImpl implements ProductService {


    @Override
    public Product getProduct(long productId) {
        Product product = new Product();

        product.setId(productId);
        product.setPrice(BigDecimal.valueOf(100));
        product.setProductName("小苹果");
        product.setNum(5);

        return product;
    }
}
