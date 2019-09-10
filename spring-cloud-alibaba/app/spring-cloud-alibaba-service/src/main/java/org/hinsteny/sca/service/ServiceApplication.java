package org.hinsteny.sca.service;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Hinsteny
 * @version BizApplication: 2019-09-05 09:28 All rights reserved.$
 */
@SpringBootApplication
@NacosPropertySource(dataId = "Hinsteny", autoRefreshed = true)
public class ServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceApplication.class, args);
    }

}
