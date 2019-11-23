package UserDataProcessing;

import DAO.CourseworkProjectDAO;
import DAOImpl.CourseworkProjectDAOImpl;
import Model.CourseworkProject;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "DeleteCourseworkController")
public class DeleteCourseworkController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CourseworkProjectDAO courseworkDAO;

    public void init() {
        courseworkDAO = new CourseworkProjectDAOImpl();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            deleteCoursework(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteCoursework(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        courseworkDAO.deleteCoursework(id);
        List<CourseworkProject> listCoursework = courseworkDAO.selectAllCourseworks();
        request.setAttribute("listCoursework", listCoursework);
        request.setAttribute("sizeOfCoursework", listCoursework.size());
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/dashboard.jsp");
        dispatcher.forward(request, response);
    }
}
