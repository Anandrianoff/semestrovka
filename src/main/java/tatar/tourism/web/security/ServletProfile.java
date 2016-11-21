package tatar.tourism.web.security;

import org.apache.log4j.Logger;
import tatar.tourism.dao.DaoFactory;
import tatar.tourism.dao.UserDao;
import tatar.tourism.pojo.Musician;
import tatar.tourism.pojo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Andrey on 12.11.2016.
 */
@WebServlet("/profile")
public class ServletProfile extends HttpServlet {

    Logger lg = Logger.getLogger(ServletProfile.class);
    UserDao userDao = DaoFactory.getDAOFactory(1).getUserDao();

    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User currentUser = (User) request.getSession().getAttribute("user");
        User user;
        if (request.getParameter("status").equals("musician")) {
            user = new Musician();
            user.setDialogs(currentUser.getDialogs());
            user.setActive(currentUser.isActive());
            user.setConfirmed(currentUser.isConfirmed());
            user.setUsername(currentUser.getUsername());
            user.setEmail(currentUser.getEmail());
            user.setFirstname(currentUser.getFirstname());
            user.setLastname(currentUser.getLastname());
        }
        else
            user = (User) request.getSession().getAttribute("user");
        try {
            user.setPassword(request.getParameter("password"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        userDao.update(user);
        lg.info("Password updated");
        getServletContext().getRequestDispatcher("/profile.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcess(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/profile.jsp").forward(request, response);
        lg.info("get");
    }
}
