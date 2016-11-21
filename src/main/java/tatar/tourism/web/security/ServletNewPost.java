package tatar.tourism.web.security;

import org.apache.log4j.Logger;
import tatar.tourism.dao.DaoFactory;
import tatar.tourism.dao.PostsDao;
import tatar.tourism.pojo.Post;
import tatar.tourism.pojo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

/**
 * Created by Andrey on 10.11.2016.
 */
@WebServlet("/newPost")
public class ServletNewPost extends HttpServlet {

    Logger lg = Logger.getLogger(ServletNewPost.class);
    PostsDao postsDao = DaoFactory.getDAOFactory(1).getPostsDao();

    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        if(request.getSession().getAttribute("user") == null){
            response.sendRedirect("/vazilon/");
        }else {
            if(((User) request.getSession().getAttribute("user")).isMusician()) {
                Post post = new Post();
                post.setAuthor(((User) request.getSession().getAttribute("user")).getUsername());
                post.setDate(new Date());
                post.setText(request.getParameter("comment"));
                post.setHeader(request.getParameter("header"));
                post.setTags(request.getParameter("tags"));
                postsDao.add(post);
                lg.info("Post saved");
                response.sendRedirect("/vazilon/");
            }else {
                response.sendRedirect("/vazilon/");
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        lg.info("Creating post");
        try {
            doProcess(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getSession().getAttribute("user") != null && ((User) request.getSession().getAttribute("user")).isMusician())
            getServletContext().getRequestDispatcher("/newPost.jsp").forward(request, response);
        else
            response.sendRedirect("/vazilon/");
    }
}
