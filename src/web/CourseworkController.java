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
import utilities.JDBCUtils;

@WebServlet(name = "CourseworkController")
public class CourseworkController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CourseworkProjectDAO courseworkDAO;

    public void init() {
        courseworkDAO = new CourseworkProjectDAOImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        doGet(request, response);
        String typeOfSubmission = request.getParameter("submit");
        try {
            switch (typeOfSubmission) {
                case "Save Coursework":
//                    showNewForm(request, response);
                    storeCoursework(request, response);
                    break;
                case "/insert":
                    storeCoursework(request, response);
                    break;
                case "/delete":
                    // deleteTodo(request, response);
                    storeCoursework(request, response);
                    break;
                case "/edit":
                    // showEditForm(request, response);
                    storeCoursework(request, response);
                    break;
                case "/update":
//                    updateTodo(request, response);
                    storeCoursework(request, response);
                    break;
                case "/list":
//                    listTodo(request, response);
                    storeCoursework(request, response);
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

//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        String action = request.getServletPath();
//
//
//    }


    private void storeCoursework(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ParseException {

        String courseworkTitle = request.getParameter("coursework-title");
        String moduleTitle = request.getParameter("module-title");
        int userID = 1;

        System.out.println("333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333");
        System.out.println(request.getParameter("intended-due-date"));

//		DateTimeFormatter df = DateTimeFormatter.ofPattern("mm/dd/YYYY");
//		LocalDate intendedDueDate = LocalDate.parse(request.getParameter("intended-due-date"),df);
//        LocalDate actualCompletionDate = LocalDate.parse(request.getParameter("actual-completion-date"),df);

        Calendar intendedDueDate = JDBCUtils.getDateCalendar(request.getParameter("intended-due-date"));
        Calendar actualCompletionDate = JDBCUtils.getDateCalendar(request.getParameter("actual-completion-date"));
        //boolean isDone = Boolean.valueOf(request.getParameter("isDone"));
        boolean isDone = false;
        CourseworkProject newCoursework = new CourseworkProject(courseworkTitle, moduleTitle, intendedDueDate, actualCompletionDate, userID, isDone);
        courseworkDAO.storeCoursework(newCoursework);
        response.sendRedirect("list");
    }



}
