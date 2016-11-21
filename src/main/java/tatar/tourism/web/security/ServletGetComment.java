package tatar.tourism.web.security;

import org.apache.log4j.Logger;
import tatar.tourism.dao.CommentsDao;
import tatar.tourism.dao.DaoFactory;
import tatar.tourism.pojo.Comment;
import tatar.tourism.pojo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

/**
 * Created by Andrey on 11.11.2016.
 */
@WebServlet("/ServletGetComment")
public class ServletGetComment extends HttpServlet {

    Logger lg = Logger.getLogger(ServletGetComment.class);
    CommentsDao commentsDao = DaoFactory.getDAOFactory(1).getCommentsDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getSession().getAttribute("user") == null){
            response.sendRedirect("login.jsp");
        }else{
            Comment newCom = new Comment();
            newCom.setAuthor(((User) request.getSession().getAttribute("user")).getUsername());
            newCom.setText(request.getParameter("comment"));
            newCom.setPostId(Integer.parseInt(request.getParameter("p")));
            try {
                commentsDao.writeNew(newCom);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            lg.info("Comment written");
            response.sendRedirect("");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        lg.info("readinig comments");
        int post = Integer.parseInt(request.getParameter("id"));
        List<Comment> comments = commentsDao.getAll(post);
        Collections.sort(comments);
//        request.setAttribute("comments", comments);
        StringBuilder sb = new StringBuilder();

        for(Comment c : comments){
            sb.append("<div class=\"row\"><div class=\"col-md-11\">\n" +
                    "<h5 style=\"color: darkturquoise\">" + c.getAuthor());
            sb.append(":</h5>\n" +
                    "<p style=\"font-size: medium\">" + c.getText());
            sb.append("</p>\n" +
                    "</div></div>");
        }
        sb.append("<form action=\"ServletGetComment?p="+post+"\" method=post class=\"form-control-static\">");
        sb.append("<textarea name=\"comment\" class=\"form-control\" rows=\"4\" cols=\"40\" placeholder=\"Comment\" style=\"font-size: large; width: 90%\"></textarea><br>\n" +
                "<div class=\"row\">\n" +
                "    <div class=\"col-md-6 col-sm-10 col-xs-12\">\n" +
                "        <input type=\"submit\" value=\"Done\" class=\"btn btn-lg btn-primary btn-block\">\n" +
                "    </div>\n" +
                "</div>");
        sb.append("</form>");
        lg.info("Good");
        response.getWriter().write(sb.toString());
    }
}
