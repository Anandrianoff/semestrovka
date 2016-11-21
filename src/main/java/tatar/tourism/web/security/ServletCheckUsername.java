package tatar.tourism.web.security;

import org.apache.log4j.Logger;
import tatar.tourism.dao.DaoFactory;
import tatar.tourism.dao.UserDao;
import tatar.tourism.pojo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Created by Andrey on 27.10.2016.
 */
@WebServlet("/checkusername")
public class ServletCheckUsername extends HttpServlet {
    static UserDao userDao = DaoFactory.getDAOFactory(1).getUserDao();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doProcess(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doProcess(request, response);
    }

    public void doProcess(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        String username = request.getParameter("username");
        User result = userDao.read(username);
        Logger lg = Logger.getLogger(ServletCheckUsername.class);
        lg.info(request.getParameter("type")+ "!!!!!!!!!!!!!!!!!!!!!!!!");
        if (result == null) {
            response.setStatus(200);
//            if(request.getParameter("type").equals("dialog"))
//                response.getWriter().write("false");
//            else{
                response.getWriter().write("true"); //json format );
                lg.info("Yes");
//            }

        } else {
            response.setStatus(200);
            //response.getWriter().write("\"User already exists\""); //json format );
//            if(request.getParameter("type").equals("dialog"))
//                response.getWriter().write("true"); //json format );
//            else
                response.getWriter().write("false");
            lg.info("Noooooooo");
        }

    }
}