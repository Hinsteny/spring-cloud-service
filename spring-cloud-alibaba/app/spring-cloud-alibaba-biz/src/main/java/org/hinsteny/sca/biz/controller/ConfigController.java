package org.hinsteny.sca.biz.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import org.apache.dubbo.config.annotation.Reference;
import org.hinsteny.sca.api.facade.ConfigFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Hinsteny
 * @version ConfigController: 2019-09-10 10:35 All rights reserved.$
 */
@RestController
@RequestMapping("/config")
public class ConfigController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @NacosValue(value = "${useLocalCache:false}", autoRefreshed = true)
    private boolean useLocalCache;

    @Reference(version = "1.0.0")
    private ConfigFacade configFacade;

    @GetMapping("/useLocalCache")
    public boolean isUseLocalCache() {
        logger.info("getConfigValue: useLocalCache");
        return useLocalCache;
    }

    @GetMapping("/app")
    public String getAppName() {
        logger.info("getConfigValue: appName");
        return configFacade.queryAppName();
    }

}
