package org.hinsteny.scn.common.spring.transformer;

import static org.springframework.web.context.request.RequestAttributes.SCOPE_REQUEST;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.hinsteny.scn.common.context.TagRouterContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerRequestTransformer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * @author hinsteny
 * @version MyLoadBalancerRequestTransformer: 2020-04-14 19:23 All rights reserved.
 */
public class MyLoadBalancerRequestTransformer implements LoadBalancerRequestTransformer {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Override
  public HttpRequest transformRequest(HttpRequest request, ServiceInstance instance) {
    Object tagObject = RequestContextHolder
      .getRequestAttributes().getAttribute(TagRouterContext.SPRING_CLOUD_TAG_KEY,  SCOPE_REQUEST);
    if (null != tagObject && StringUtils.isNotBlank((String) tagObject)) {
      request.getHeaders().add(TagRouterContext.SPRING_CLOUD_TAG_KEY, (String) tagObject);
    }
    HttpHeaders headers = request.getHeaders();
    if (logger.isDebugEnabled()) {
      logger.debug(JSON.toJSONString(headers));
    }
    return request;
  }

}
