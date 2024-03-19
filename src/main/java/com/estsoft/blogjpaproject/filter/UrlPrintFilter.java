package com.estsoft.blogjpaproject.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class UrlPrintFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("UrlPrintFilter init - {} ", filterConfig.getFilterName());
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        request.setAttribute("do filter {}", httpServletRequest.getRequestURL().toString());

        log.info("UrlPrintFilter before");
        chain.doFilter(request, response);
        log.info("UrlPrintFilter after");
    }

    @Override
    public void destroy() {
        log.info("UrlPrintFilter destroy()");
    }
}
