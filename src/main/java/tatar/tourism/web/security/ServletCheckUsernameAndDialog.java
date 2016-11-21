package tatar.tourism.web.security;

import org.apache.log4j.Logger;
import tatar.tourism.dao.DaoFactory;
import tatar.tourism.dao.DialogsDao;
import tatar.tourism.dao.UserDao;
import tatar.tourism.pojo.Dialog;
import tatar.tourism.pojo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Andrey on 04.11.2016.
 */
@WebServlet("/ServletCheckUsernameAndDialog")
public class ServletCheckUsernameAndDialog extends HttpServlet {

    static UserDao userDao = DaoFactory.getDAOFactory(1).getUserDao();
    Logger lg = Logger.getLogger(ServletCheckUsernameAndDialog.class);
    static DialogsDao dialogsDao = DaoFactory.getDAOFactory(1).getDialogsDao();


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcess(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcess(request, response);
    }


    public void doProcess(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        String username = request.getParameter("username");
        User result = userDao.read(username);
        User sesUser = (User) request.getSession().getAttribute("user");
        if(sesUser != null)
            lg.info(sesUser.getUsername());
        Dialog d = dialogsDao.getDialog(username, sesUser.getUsername());
//        lg.info(d.getUser1() + "    " + d.getUser2());
        lg.info(sesUser.getUsername()+ "!!!!!!!!!!!!!!!!!!!!!!!!");
        if (result != null && d == null && !username.equals(sesUser.getUsername())) {
            response.setStatus(200);
            response.getWriter().write("true");
            lg.info("true");
        } else {
            response.setStatus(200);
            response.getWriter().write("false");
            lg.info("false");
        }

    }
}
