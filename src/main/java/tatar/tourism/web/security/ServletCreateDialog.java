package tatar.tourism.web.security;

import org.apache.log4j.Logger;
import tatar.tourism.dao.DaoFactory;
import tatar.tourism.dao.DialogsDao;
import tatar.tourism.pojo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

/**
 * Created by Andrey on 03.11.2016.
 */
@WebServlet("/ServletCreateDialog")
public class ServletCreateDialog extends HttpServlet {

    static Logger log = Logger.getLogger(ServletRegistration.class);
    static DialogsDao dialogsDao = DaoFactory.getDAOFactory(1).getDialogsDao();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
//        String referer = request.getHeader("Referer");

        log.info(request.getSession().getAttribute("user") + "____________!!!!!!!!!!!");
        if (request.getSession().getAttribute("user") != null && !request.getParameter("username").equals("")){
            User currentUser = (User) request.getSession().getAttribute("user");
            response.sendRedirect("dialogs");
//            getServletContext().getRequestDispatcher("/dialogs").forward(request, response);
            try {
                dialogsDao.create(request.getParameter("username"), currentUser.getUsername());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response  );
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
