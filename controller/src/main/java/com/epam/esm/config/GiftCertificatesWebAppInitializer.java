package com.epam.esm.config;

import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class GiftCertificatesWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{RootConfig.class};
    }

    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class};
    }

    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected WebApplicationContext createRootApplicationContext() {

        WebApplicationContext context = super.createRootApplicationContext();
        ((ConfigurableEnvironment) context.getEnvironment()).setActiveProfiles("dev");

        return context;
    }
}
