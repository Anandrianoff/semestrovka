package tatar.tourism.web.security;

import org.apache.log4j.Logger;
import tatar.tourism.dao.DialogsDao;
import tatar.tourism.dao.MessagesDao;
import tatar.tourism.dao.MySQLDaoFactory;
import tatar.tourism.pojo.Dialog;
import tatar.tourism.pojo.Message;
import tatar.tourism.pojo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * Created by Andrey on 05.11.2016.
 */
@WebServlet("/messages")
public class ServletMessages extends HttpServlet {

    static Logger lg = Logger.getLogger(ServletMessages.class);
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (request.getSession().getAttribute("user") == null) {
            lg.info("redirect");
            response.sendRedirect("/vazilon/");
        } else {
            User currentUser = (User) request.getSession().getAttribute("user");
            Dialog d = null;
            if (request.getParameter("dialog") == null) {
                lg.info("no dialog id");
                response.sendError(404);
            } else {
                DialogsDao dialogsDao = MySQLDaoFactory.getDAOFactory(1).getDialogsDao();
                d = dialogsDao.getDialog(Integer.parseInt(request.getParameter("dialog")));
                if (!(d.getUser2().equals(currentUser.getUsername()) || (d.getUser1().equals(currentUser.getUsername())))) {
                    lg.info("incorrect dialog id");
                    response.sendError(404);
                }
            }
            MessagesDao messagesDao = MySQLDaoFactory.getDAOFactory(1).getMessagesDao();
            List<Message> newList = (messagesDao.getAllMessages(Integer.parseInt(request.getParameter("dialog"))));
            Collections.sort(newList);
            Collections.reverse(newList);
            d.setMessages(newList);
            currentUser.setCurrentDialog(d);
            getServletContext().getRequestDispatcher("/messages.jsp").forward(request, response);

        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
