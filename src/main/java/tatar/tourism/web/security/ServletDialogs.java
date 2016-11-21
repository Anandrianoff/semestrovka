package tatar.tourism.web.security;

import org.apache.log4j.Logger;
import tatar.tourism.dao.DialogsDao;
import tatar.tourism.dao.MySQLDaoFactory;
import tatar.tourism.dao.MySQLDialogsDao;
import tatar.tourism.pojo.Dialog;
import tatar.tourism.pojo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Andrey on 05.11.2016.
 */
@WebServlet("/dialogs")
public class ServletDialogs extends HttpServlet {
    static Logger lg = Logger.getLogger(ServletDialogs.class);
    DialogsDao dialogsDao = MySQLDaoFactory.getDAOFactory(1).getDialogsDao();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
//        String referer = request.getHeader("Referer");

        lg.info(request.getSession().getAttribute("user") + "____________!!!!!!!!!!!");
        if (request.getSession().getAttribute("user") == null){
            lg.info("redirect");
            response.sendRedirect("/vazilon/");
        }else{
            User curUser = (User)request.getSession().getAttribute("user");
            curUser.setDialogs(dialogsDao.getDialogs(curUser.getUsername()));
            for(Dialog d : curUser.getDialogs())
                lg.info(d.getId());
            lg.info(curUser.getDialogs().isEmpty());
            getServletContext().getRequestDispatcher("/dialogs.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
