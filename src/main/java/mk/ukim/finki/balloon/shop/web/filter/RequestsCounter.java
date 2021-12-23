package mk.ukim.finki.balloon.shop.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter
public class RequestsCounter implements Filter {

    private Integer counter = 0;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        counter++;
        request.getSession().setAttribute("reqCounter", counter);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }

}
