package org.hinsteny.sca.service.config;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import org.apache.dubbo.config.annotation.Service;
import org.hinsteny.sca.api.facade.ConfigFacade;

/**
 * @author Hinsteny
 * @version ConfigService: 2019-09-10 11:03 All rights reserved.$
 */
@Service(version = "1.0.0")
public class ConfigService implements ConfigFacade {

    @NacosValue(value = "${service.name:spring-cloud-alibaba-service}", autoRefreshed = true)
    private String serviceName;

    @Override
    public String queryAppName() {
        return serviceName;
    }

}
