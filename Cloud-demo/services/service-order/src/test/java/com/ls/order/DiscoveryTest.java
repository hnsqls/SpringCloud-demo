package com.ls.order;


import com.alibaba.cloud.nacos.discovery.NacosDiscoveryClient;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import java.util.List;

@SpringBootTest
public class DiscoveryTest {


    @Autowired
     DiscoveryClient discoveryClient;

    @Resource
    NacosDiscoveryClient nacosDiscoveryClient;

    @Test
    public void DiscoveryClientTest() {

        //获得服务名
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            System.out.println(service);
            //根据服务名 获得服务实例信息
            List<ServiceInstance> instances = discoveryClient.getInstances(service);
            for (ServiceInstance instance : instances) {
                System.out.println(instance.getHost());
                System.out.println(instance.getPort());
            }
        }

    }


    @Test
    public void NacosDiscoveryClientTest() {
        List<String> services = nacosDiscoveryClient.getServices();
        for (String service : services) {
            System.out.println(service);
            nacosDiscoveryClient.getInstances(service).forEach(instance -> {
                System.out.println(instance.getHost());
                System.out.println(instance.getPort());
            });
        }
    }


}
