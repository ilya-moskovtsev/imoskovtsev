package ru.job4j.servlets.crud;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

public class AuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        if (req.getRequestURI().contains("/login")) {
            chain.doFilter(req, resp);
        } else {
            HttpSession session = req.getSession();
            if (Objects.isNull(session.getAttribute("login"))) {
                resp.sendRedirect(String.format("%s/login", req.getContextPath()));
                return;
            }
            chain.doFilter(req, resp);
        }
    }
}
