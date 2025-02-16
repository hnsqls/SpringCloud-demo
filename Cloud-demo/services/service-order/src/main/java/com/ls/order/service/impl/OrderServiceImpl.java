package com.ls.order.service.impl;

import com.ls.order.bean.Order;
import com.ls.order.service.OrderService;
import com.ls.product.bean.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {


    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient   discoveryClient;
    @Override
    public Order getOrder(Long userId, Long productId) {


        Order order = new Order();
        order.setId(2025L);
        order.setUserId(userId);

        order.setUsername("hnsqls");

        order.setAddr("河南商丘");


//  todo 调用商品表计算
        Product product = getProductFromRemote(productId);
        int num = product.getNum();
        BigDecimal price = product.getPrice();
        BigDecimal money = new BigDecimal(num).multiply(price);
        order.setTotalPrice(money);
        List<Product> products = new ArrayList<>();
        products.add(product);
        order.setProducts(products);
        return order;
    }


    //远程调用
    public Product getProductFromRemote(Long productId){


        List<ServiceInstance> instances = discoveryClient.getInstances("service-product");
        ServiceInstance serviceInstance = instances.get(0);
        String url = "http://"+serviceInstance.getHost()+":"+serviceInstance.getPort()+"/product/"+productId;

        log.info("远程调用商品服务，获取商品信息，url:{}",url);
        return restTemplate.getForObject(url, Product.class);

    }
}
