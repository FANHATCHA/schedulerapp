package UserDataProcessing;

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
import java.sql.SQLException;

@WebServlet(name = "EditCourseworkController")
public class EditCourseworkController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private CourseworkProjectDAO courseworkDAO;

    public void init() {
        courseworkDAO = new CourseworkProjectDAOImpl();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            editCoursework(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void editCoursework(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession();
        int id = Integer.parseInt(request.getParameter("id"));
        String email = (String) session.getAttribute("userSessionEmail");
        CourseworkProject existingCoursework = courseworkDAO.selectCoursework(id);
        User existingUser = courseworkDAO.fetchUser(email);
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/coursework-form.jsp");
        request.setAttribute("coursework", existingCoursework);
        request.setAttribute("authUser", existingUser);
        dispatcher.forward(request, response);

    }
}
