package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import DAO.CourseworkProjectDAO;
import DAOImpl.CourseworkProjectDAOImpl;
import Model.CourseworkProject;
import Model.User;
import utilities.JDBCUtils;

@WebServlet(name = "CourseworkController")
public class CourseworkController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CourseworkProjectDAO courseworkDAO;

    public void init() {
        courseworkDAO = new CourseworkProjectDAOImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String typeOfSubmission = request.getParameter("submit");
        try {
            switch (typeOfSubmission) {
                case "Save Coursework":
                    storeCoursework(request, response);
                    break;
                case "Update Coursework":
                    updateCoursework(request, response);
                    break;
                default:
                    RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/login.jsp");
                    dispatcher.forward(request, response);
                    break;
            }
        } catch (SQLException | ParseException ex) {
            throw new ServletException(ex);
        }
    }

    private void storeCoursework(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ParseException, ServletException {
        String email = request.getParameter("email");
        String courseworkTitle = request.getParameter("coursework-title");
        String moduleTitle = request.getParameter("module-title");
        int userID = Integer.parseInt(request.getParameter("auth-user-id"));
        Calendar intendedDueDate = JDBCUtils.getDateCalendar(request.getParameter("intended-due-date"));
        Calendar actualCompletionDate = JDBCUtils.getDateCalendar(request.getParameter("actual-completion-date"));

        boolean isDone = false;
        CourseworkProject newCoursework = new CourseworkProject(courseworkTitle, moduleTitle, intendedDueDate, actualCompletionDate, userID, isDone);
        courseworkDAO.storeCoursework(newCoursework);

        List<CourseworkProject> listCoursework = courseworkDAO.selectCourseworkByUser(email);
        User existingUser = courseworkDAO.fetchUser(email);
        request.setAttribute("listCoursework", listCoursework);
        request.setAttribute("sizeOfCoursework", listCoursework.size());
        request.setAttribute("authUser", existingUser);

        this.getServletContext().getRequestDispatcher("/WEB-INF/dashboard.jsp").forward(request, response);
    }

    private void updateCoursework(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ParseException, ServletException {

        int id = Integer.parseInt(request.getParameter("id"));
        int userID = Integer.parseInt(request.getParameter("auth-user-id"));
        String courseworkTitle = request.getParameter("coursework-title");
        String moduleTitle = request.getParameter("module-title");
        String email = request.getParameter("email");
        Calendar intendedDueDate = JDBCUtils.getDateCalendar(request.getParameter("intended-due-date"));
        Calendar actualCompletionDate = JDBCUtils.getDateCalendar(request.getParameter("actual-completion-date"));

        boolean isDone = false;

        CourseworkProject updatedCoursework = new CourseworkProject(id, courseworkTitle, moduleTitle, intendedDueDate, actualCompletionDate, userID, isDone);
        courseworkDAO.updateCoursework(updatedCoursework);
        List<CourseworkProject> listCoursework = courseworkDAO.selectCourseworkByUser(email);
        User existingUser = courseworkDAO.fetchUser(email);
        request.setAttribute("authUser", existingUser);
        request.setAttribute("listCoursework", listCoursework);
        request.setAttribute("sizeOfCoursework", listCoursework.size());
        this.getServletContext().getRequestDispatcher("/WEB-INF/dashboard.jsp").forward(request, response);
    }




}
