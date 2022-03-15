package com.capitanperegrina.common.spring;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * The Class ApplicationContextUtils.
 */
public class ApplicationContextUtils implements ApplicationContextAware {

    /** The log. */
    static Logger log = Logger.getLogger(ApplicationContextUtils.class);

    /** The ctx. */
    private static ApplicationContext ctx;

    /**
     * Sets the application context.
     *
     * @param appContext the new application context
     */
    @Override
    public void setApplicationContext(final ApplicationContext appContext) {
	ctx = appContext;
    }

    /**
     * Gets the application context.
     *
     * @return the application context
     */
    public static ApplicationContext getApplicationContext() {
	return ctx;
    }
}