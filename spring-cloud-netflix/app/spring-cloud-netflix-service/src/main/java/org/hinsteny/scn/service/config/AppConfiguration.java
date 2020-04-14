package org.hinsteny.scn.service.config;

import feign.RequestInterceptor;
import org.hinsteny.scn.common.servlet.interceptor.HttpTagHandlerInterceptor;
import org.hinsteny.scn.common.spring.interceptor.TagCallInterceptor;
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
  public RequestInterceptor TagCallInterceptor() {
    return new TagCallInterceptor();
  }

  @Bean
  public HandlerInterceptor httpHandlerInterceptor() {
    return new HttpTagHandlerInterceptor();
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(httpHandlerInterceptor());
  }

}
