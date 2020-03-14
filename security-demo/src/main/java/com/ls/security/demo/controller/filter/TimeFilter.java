package com.ls.security.demo.controller.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * @className: TimeFilter
 * @description: 过滤器
 * @author: liusCoding
 * @create: 2020-03-12 15:41
 */

public class TimeFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("time filter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("time filter start");
        long start = System.currentTimeMillis();
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println(System.currentTimeMillis()-start);
        System.out.println("time filter finish");
    }

    @Override
    public void destroy() {
        System.out.println("time filter destroy");
    }
}
