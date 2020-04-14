package org.hinsteny.scn.common.servlet.interceptor;

import static org.springframework.web.context.request.RequestAttributes.SCOPE_REQUEST;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.hinsteny.scn.common.context.TagRouterContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @author hinsteny
 * @version HttpTagHandlerInterceptor: 2020-04-14 11:21 All rights reserved.
 */
public class HttpTagHandlerInterceptor implements HandlerInterceptor{

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    if (logger.isInfoEnabled()) {
      logger.info("Current request tag is: {}", request.getHeader(TagRouterContext.SPRING_CLOUD_TAG_KEY));
    }
    if (StringUtils.isNotBlank(request.getHeader(TagRouterContext.SPRING_CLOUD_TAG_KEY))) {
      RequestContextHolder.getRequestAttributes().setAttribute(TagRouterContext.SPRING_CLOUD_TAG_KEY,
        request.getHeader(TagRouterContext.SPRING_CLOUD_TAG_KEY), SCOPE_REQUEST);
    }
    return true;
  }
}
