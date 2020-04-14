package org.hinsteny.scn.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Hinsteny
 * @version Application: Application 2019-07-03 15:51 All rights reserved.$
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ServiceApplicationGray {

    public static void main(String[] args) {
        SpringApplication.run(ServiceApplicationGray.class, args);
    }

}

