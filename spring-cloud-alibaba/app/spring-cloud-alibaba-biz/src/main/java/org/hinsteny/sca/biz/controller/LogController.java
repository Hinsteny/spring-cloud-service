package org.hinsteny.sca.biz.controller;

import javax.annotation.Resource;
import org.apache.logging.log4j.Level;
import org.hinsteny.sca.biz.listener.LogListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Hinsteny
 * @version LogController: 2019-09-11 16:37 All rights reserved.$
 */
@RestController
@RequestMapping("/log")
public class LogController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private LogListener logListener;

    @GetMapping("/{level}")
    public void sayHelloWorldToDubbo(@PathVariable("level") String levelStr) {
        logger.info("log level: {}", levelStr);
        Level level = Level.getLevel(levelStr.toUpperCase());
        if (null != level) {
            logListener.changeLevel(level);
        }
    }

}
