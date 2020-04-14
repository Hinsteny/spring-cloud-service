package org.hinsteny.scn.common.spring.predicate;

import static org.springframework.web.context.request.RequestAttributes.SCOPE_REQUEST;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractServerPredicate;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.PredicateKey;
import com.netflix.niws.loadbalancer.DiscoveryEnabledServer;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.hinsteny.scn.common.context.TagRouterContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * <p>Filter the server list with same tag value</p>
 * @author hinsteny
 * @version GrayPredicate: 2020-04-13 18:16 All rights reserved.
 */
public class TagPredicate extends AbstractServerPredicate {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  public TagPredicate(IRule rule) {
    super(rule);
  }

  public TagPredicate(IRule rule, IClientConfig clientConfig) {
    super(rule, clientConfig);
  }

  @Override
  public boolean apply(PredicateKey input) {
    Object tagObject = RequestContextHolder.getRequestAttributes().getAttribute(TagRouterContext.SPRING_CLOUD_TAG_KEY,
      SCOPE_REQUEST);
    String tag = null;
    if (null != tagObject){
      tag = (String) tagObject;
    }
    DiscoveryEnabledServer server = (DiscoveryEnabledServer) input.getServer();
    Map<String, String> metadata = server.getInstanceInfo().getMetadata();
    return judgeServerMetadataHasTag(metadata, tag);
  }

  private boolean judgeServerMetadataHasTag(Map<String, String> metadata, String requestTag) {
    String serverTag = metadata.get(TagRouterContext.SPRING_CLOUD_TAG_KEY);
    if (logger.isDebugEnabled()) {
      logger.debug("Request tag--[{}], Server tag--[{}]", requestTag, serverTag);
    }
    if (StringUtils.isBlank(requestTag)) {
      return StringUtils.isBlank(serverTag);
    } else {
      return requestTag.trim().equals(serverTag);
    }
  }

}
