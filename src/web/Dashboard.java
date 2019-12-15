package web;

import DAO.CourseworkProjectDAO;
import DAOImpl.CourseworkProjectDAOImpl;
import Model.CourseworkProject;
import Model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "Dashboard")
public class Dashboard extends HttpServlet {

    private CourseworkProjectDAO courseworkDAO;

    public void init() {
        courseworkDAO = new CourseworkProjectDAOImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("userSessionEmail");

        User existingUser = courseworkDAO.fetchUser(email);

        List<CourseworkProject> listCoursework = courseworkDAO.selectCourseworkByUser(email);
        request.setAttribute("listCoursework", listCoursework);
        request.setAttribute("sizeOfCoursework", listCoursework.size());
        request.setAttribute("authUser", existingUser);


        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/dashboard.jsp");
        dispatcher.forward(request, response);
    }
}
