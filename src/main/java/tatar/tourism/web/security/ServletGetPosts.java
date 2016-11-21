package tatar.tourism.web.security;

import org.apache.log4j.Logger;
import tatar.tourism.dao.DaoFactory;
import tatar.tourism.dao.PostsDao;
import tatar.tourism.pojo.Post;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * Created by Andrey on 09.11.2016.
 */
@WebServlet("")
public class ServletGetPosts extends HttpServlet {
    Logger lg = Logger.getLogger(ServletGetPosts.class);
    PostsDao postsDao = DaoFactory.getDAOFactory(1).getPostsDao();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Post> posts = postsDao.getAll();
        Collections.sort(posts);
        Collections.reverse(posts);
        request.setAttribute("posts", posts);
        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
