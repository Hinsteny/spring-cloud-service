package org.hinsteny.scn.common.spring.client;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.reactive.LoadBalancerCommand;
import feign.Client;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;
import org.springframework.cloud.openfeign.ribbon.CachingSpringLoadBalancerFactory;
import org.springframework.cloud.openfeign.ribbon.LoadBalancerFeignClient;

/**
 * @author hinsteny
 * @version CustomerLoadBalanceClient: 2020-04-14 10:40 All rights reserved.
 */
public class CustomerLoadBalanceClient extends LoadBalancerFeignClient {

  public CustomerLoadBalanceClient(Client delegate, CachingSpringLoadBalancerFactory lbClientFactory,
    SpringClientFactory clientFactory) {
    super(delegate, lbClientFactory, clientFactory);
  }

}
