package tatar.tourism.web.security;

import org.apache.log4j.Logger;
import tatar.tourism.dao.MessagesDao;
import tatar.tourism.dao.MySQLDaoFactory;
import tatar.tourism.pojo.Message;
import tatar.tourism.pojo.User;

import javax.jws.soap.SOAPBinding;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by Andrey on 09.11.2016.
 */
@WebServlet("/ServletLoadNewMessages")
public class ServletLoadNewMessages extends HttpServlet {

    static MessagesDao messagesDao = MySQLDaoFactory.getDAOFactory(1).getMessagesDao();
    static Logger lg = Logger.getLogger(ServletLoadNewMessages.class);

//    <c:if test="${user.username==myitem.author}">
//    <tr class="active">
//    <td align="right"><p>${myitem.message}</p>${myitem.date}
//    </td>
//    </tr>
//    </c:if>
//    <c:if test="${user.username!=myitem.author}">
//    <tr class="success">
//    <td align="left"><p>${myitem.message}</p>${myitem.date}
//    </td>
//    </tr>
//    </c:if>


    protected void process(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Timestamp d = new Timestamp(Long.parseLong(request.getParameter("time")));
        User user = (User) request.getSession().getAttribute("user");
        List<Message> newMessages = messagesDao.getMessageLatestTimestamp(d, user.getCurrentDialog().getId());
        StringBuilder sb = new StringBuilder();

        lg.info("new messages: " + newMessages.size());
        for (Message m  : newMessages){

            if(user.getUsername().equals(m.getAuthor())){
                sb.append("<tr class=\"active\">\n" +
                        "    <td align=\"right\"><p>"+m.getMessage()+"</p>"+ m.getDate() +
                        "    </td>\n" +
                        "    </tr>");

            }else {
                sb.append("<tr class=\"success\">\n" +
                        "    <td align=\"left\"><p>"+m.getMessage()+"</p>"+ m.getDate() +
                        "    </td>\n" +
                        "    </tr>");
            }

        }
        response.getWriter().write(sb.toString());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
