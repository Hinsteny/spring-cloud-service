package org.hinsteny.scn.common.spring.interceptor;

import static org.springframework.web.context.request.RequestAttributes.SCOPE_REQUEST;

import com.alibaba.fastjson.JSON;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import java.util.Collection;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.hinsteny.scn.common.context.TagRouterContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * @author hinsteny
 * @version TagCallInterceptor: 2020-04-14 10:10 All rights reserved.
 */
public class TagCallInterceptor implements RequestInterceptor {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Override
  public void apply(RequestTemplate template) {
    Object tagObject = RequestContextHolder.getRequestAttributes().getAttribute(TagRouterContext.SPRING_CLOUD_TAG_KEY,  SCOPE_REQUEST);
    if (null != tagObject && StringUtils.isNotBlank((String) tagObject)) {
      template.header(TagRouterContext.SPRING_CLOUD_TAG_KEY, (String) tagObject);
    }
    Map<String, Collection<String>> headers = template.headers();
    if (logger.isDebugEnabled()) {
      logger.debug(JSON.toJSONString(headers));
    }
  }

}
