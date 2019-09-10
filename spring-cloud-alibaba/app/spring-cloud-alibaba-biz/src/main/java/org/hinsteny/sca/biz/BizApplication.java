package org.hinsteny.sca.biz;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Hinsteny
 * @version BizApplication: 2019-09-05 09:28 All rights reserved.$
 */
@SpringBootApplication
@NacosPropertySource(dataId = "Hinsteny", autoRefreshed = true)
public class BizApplication {

    public static void main(String[] args) {
        SpringApplication.run(BizApplication.class, args);
    }

}
