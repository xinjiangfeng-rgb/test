package com.xwtech.xwecp.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * spring上下文
 * Created by lv on 2016/4/21.
 */
@Component
public class SpringUtil implements ApplicationContextAware {
    private static final Logger LOGGER = LoggerFactory.getLogger(SpringUtil.class);
    //spring上下文
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringUtil.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }


    /**
     * 获取某个bean
     *
     * @param name
     * @param <T>
     * @return
     * @throws BeansException
     */
    public static <T> T getBean(String name) throws BeansException {
        return (T) applicationContext.getBean(name);
    }

    public static Object getBean(Object name) throws BeansException {
        return  applicationContext.getBean(name.getClass());
    }

}
