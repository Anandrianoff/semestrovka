package tatar.tourism.security;

import tatar.tourism.pojo.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileFilter;
import java.io.IOException;

/**
 * Created by Andrey on 09.11.2016.
 */
@WebFilter(filterName = "loggedInFilter", urlPatterns = {"/register", "/register.jsp", "/login", "/login.jsp",
        "/messages", "/dialogs", "/messages.jsp", "/dialogs.jsp", "/newPost"})
public class LoggedFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        if (((HttpServletRequest) request).getSession().getAttribute("user") == null) {
//            ((HttpServletResponse) response).sendRedirect("/vazilon/");
            chain.doFilter(request, response);
        }else
        ((HttpServletRequest) request).getServletContext().getRequestDispatcher("/vazilon/").forward(httpRequest, httpResponse);
    }

    @Override
    public void destroy() {

    }
}
