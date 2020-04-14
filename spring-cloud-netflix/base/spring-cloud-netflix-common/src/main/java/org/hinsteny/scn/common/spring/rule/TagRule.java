package org.hinsteny.scn.common.spring.rule;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractServerPredicate;
import com.netflix.loadbalancer.CompositePredicate;
import com.netflix.loadbalancer.PredicateBasedRule;
import org.hinsteny.scn.common.spring.predicate.NoTagPredicate;
import org.hinsteny.scn.common.spring.predicate.TagPredicate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>Select server by tag value</p>
 * the parameter of method choose is the input tag value of current request; 1. It will choose one server from those
 * haven't the service metadata (spring.tags) if the key is null; 2. It will choose one server from those have the same
 * service metadata (spring.tags) value equals the key;
 *
 * @author hinsteny
 * @version GrayRule: 2020-04-14 10:01 All rights reserved.
 */
public class TagRule extends PredicateBasedRule {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  private CompositePredicate compositePredicate;

  public TagRule() {
    super();
    TagPredicate tagPredicate = new TagPredicate(this);
    NoTagPredicate noTagPredicate = new NoTagPredicate(this);
    compositePredicate = createCompositePredicate(tagPredicate, noTagPredicate);
  }

  @Override
  public void initWithNiwsConfig(IClientConfig clientConfig) {
    TagPredicate tagPredicate = new TagPredicate(this, clientConfig);
    NoTagPredicate noTagPredicate = new NoTagPredicate(this, clientConfig);
    compositePredicate = createCompositePredicate(tagPredicate, noTagPredicate);
  }

  @Override
  public AbstractServerPredicate getPredicate() {
    return compositePredicate;
  }

  private CompositePredicate createCompositePredicate(TagPredicate p1, NoTagPredicate p2) {
    return CompositePredicate.withPredicates(p1)
      .addFallbackPredicate(p2)
      .build();
  }

}
