package tatar.tourism.web.security;

import org.apache.log4j.Logger;
import tatar.tourism.dao.MySQLDaoFactory;
import tatar.tourism.dao.UserDao;
import tatar.tourism.pojo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Andrey on 12.11.2016.
 */
@WebServlet("/checkpassword")
public class ServletCheckPassword extends HttpServlet {

    UserDao userDao = MySQLDaoFactory.getDAOFactory(1).getUserDao();
    Logger lg = Logger.getLogger(ServletCheckPassword.class);

    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = (User) request.getSession().getAttribute("user");
        String password = request.getParameter("oldpassword");
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        md.update(password.getBytes());
        byte byteData[] = md.digest();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++)
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        User result = userDao.read(user.getUsername(), password);
        lg.info(sb.toString());
        if (result == null) {
            response.getWriter().write("false");
            lg.info("false");
        }else {
            lg.info("true");
            response.getWriter().write("true");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcess(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcess(request, response);
    }
}
