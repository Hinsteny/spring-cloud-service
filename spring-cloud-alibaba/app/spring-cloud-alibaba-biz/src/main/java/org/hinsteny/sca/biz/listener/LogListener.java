package org.hinsteny.sca.biz.listener;

import java.util.Collection;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author Hinsteny
 * @version LogListener: 2019-09-11 17:07 All rights reserved.$
 */
@Component
public class LogListener implements ApplicationListener<ApplicationReadyEvent> {

    private org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

    private Collection<Logger> allLoggers;

    /**
     * Handle an application event.
     *
     * @param event the event to respond to
     */
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        this.initLoggers();
    }

    /**
     * 修改应用日志级别
     *
     * @param level 要修改的级别
     */
    public void changeLevel(Level level) {
        allLoggers.stream().forEach(item -> {
            if (item.getName().startsWith("org.hinsteny.sca.biz")) {
                item.setLevel(level);
            }
        });
    }

    private void initLoggers() {
        allLoggers = LoggerContext.getContext().getLoggers();
        allLoggers.addAll(LoggerContext.getContext(false).getLoggers());
        logger.info("LogListener init succeeded...");
    }

}
