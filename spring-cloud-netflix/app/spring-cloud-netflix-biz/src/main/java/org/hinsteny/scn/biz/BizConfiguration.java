package org.hinsteny.scn.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author Hinsteny
 * @version BizConfiguration: BizConfiguration 2019-07-08 16:33 All rights reserved.$
 */
@Configuration
public class BizConfiguration {

    @Autowired
    private RestTemplateBuilder builder;

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return builder.build();
    }

}
