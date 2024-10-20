package com.paperless.interceptor;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfigs {

    @Bean
    @ConditionalOnMissingBean(name = "requestContextFilters")
    public FilterRegistrationBean<RequestContextFilters> requestContextFilter() {
        FilterRegistrationBean<RequestContextFilters> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new RequestContextFilters());
        registrationBean.addUrlPatterns("/*");  // Apply to all URL patterns

        return registrationBean;
    }

}
