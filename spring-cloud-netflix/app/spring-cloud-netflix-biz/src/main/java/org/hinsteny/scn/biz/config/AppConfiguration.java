package org.hinsteny.scn.biz.config;

import com.netflix.loadbalancer.IRule;
import feign.Client;
import feign.RequestInterceptor;
import org.hinsteny.scn.common.spring.client.CustomerLoadBalanceClient;
import org.hinsteny.scn.common.servlet.interceptor.HttpTagHandlerInterceptor;
import org.hinsteny.scn.common.spring.interceptor.TagCallInterceptor;
import org.hinsteny.scn.common.spring.rule.TagRule;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;
import org.springframework.cloud.openfeign.ribbon.CachingSpringLoadBalancerFactory;
import org.springframework.cloud.openfeign.ribbon.LoadBalancerFeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author hinsteny
 * @version AppConfiguration: 2020-04-14 10:04 All rights reserved.
 */
@Configuration
public class AppConfiguration implements WebMvcConfigurer {

  @Bean
  public IRule ribbonRule() {
    return new TagRule();
  }

//  @Bean
  public LoadBalancerFeignClient customerLoadBalanceClient(Client delegate, CachingSpringLoadBalancerFactory lbClientFactory,
    SpringClientFactory clientFactory) {
    return new CustomerLoadBalanceClient(delegate, lbClientFactory, clientFactory);
  }

  @Bean
  public HandlerInterceptor httpHandlerInterceptor() {
    return new HttpTagHandlerInterceptor();
  }

  @Bean
  public RequestInterceptor TagCallInterceptor() {
    return new TagCallInterceptor();
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(httpHandlerInterceptor());
  }

}
