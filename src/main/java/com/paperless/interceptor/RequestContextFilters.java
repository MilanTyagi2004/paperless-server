package com.paperless.interceptor;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component
@Slf4j
public class RequestContextFilters implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        try {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            RequestContext.setRequest(httpRequest);
            log.info("API Hit - Method: {}", httpRequest.getRequestURI());
            chain.doFilter(request, response);
        } finally {
            RequestContext.clear();
        }
    }
}

