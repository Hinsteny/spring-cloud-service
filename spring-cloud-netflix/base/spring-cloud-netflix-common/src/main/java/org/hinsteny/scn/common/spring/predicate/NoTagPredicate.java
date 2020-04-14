package org.hinsteny.scn.common.spring.predicate;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractServerPredicate;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.PredicateKey;
import com.netflix.niws.loadbalancer.DiscoveryEnabledServer;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.hinsteny.scn.common.context.TagRouterContext;

/**
 * @author hinsteny
 * @version NoTagPredicate: 2020-04-14 16:20 All rights reserved.
 */
public class NoTagPredicate extends AbstractServerPredicate {

  public NoTagPredicate(IRule rule) {
    super(rule);
  }

  public NoTagPredicate(IRule rule, IClientConfig clientConfig) {
    super(rule, clientConfig);
  }

  @Override
  public boolean apply(PredicateKey input) {
    DiscoveryEnabledServer server = (DiscoveryEnabledServer) input.getServer();
    Map<String, String> metadata = server.getInstanceInfo().getMetadata();
    return StringUtils.isBlank(metadata.get(TagRouterContext.SPRING_CLOUD_TAG_KEY));
  }

}
