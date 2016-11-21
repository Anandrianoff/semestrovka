package tatar.tourism.web.security;

import org.apache.log4j.Logger;
import tatar.tourism.dao.DaoFactory;
import tatar.tourism.dao.MessagesDao;
import tatar.tourism.pojo.Message;
import tatar.tourism.pojo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Date;

/**
 * Created by Andrey on 06.11.2016.
 */
@WebServlet("/ServletNewMessage")
public class ServletNewMessage extends HttpServlet {

    static Logger lg = Logger.getLogger(ServletNewMessage.class);
    static MessagesDao messagesDao = DaoFactory.getDAOFactory(1).getMessagesDao();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (request.getSession().getAttribute("user") == null) {
            lg.info("redirect");
            response.sendRedirect("/vazilon");
        }else {
            lg.info(request.getParameter("text"));
            Message msg = new Message();
            User user = (User) request.getSession().getAttribute("user");
            lg.info("dialog id - " + user.getCurrentDialog().getId());
            msg.setId_dialog(user.getCurrentDialog().getId());
            msg.setAuthor(user.getUsername());
            msg.setMessage(request.getParameter("text"));
            java.util.Date d =new java.util.Date();
            long t = d.getTime();
            msg.setDate(new Date(t));
            try {
                messagesDao.writeNewMessage(msg);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            lg.info("message saved");
            String disp = "/vazilon/messages?dialog=" + user.getCurrentDialog().getId();
            response.getWriter().write("true");
//            response.sendRedirect(disp);
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
