package web;

import DAO.CourseworkProjectDAO;
import DAO.MilestonesDAO;

import DAOImpl.CourseworkProjectDAOImpl;
import DAOImpl.MilestonesDAOImpl;

import Model.CourseworkProject;
import Model.Milestones;
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
import java.text.ParseException;
import java.util.List;

@WebServlet(name = "MilestonesController")
public class MilestonesController extends HttpServlet {

    private MilestonesDAO milestonesDAO;
    private CourseworkProjectDAO courseworkDAO;

    public void init() {
        milestonesDAO = new MilestonesDAOImpl();
        courseworkDAO = new CourseworkProjectDAOImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String typeOfSubmission = request.getParameter("type-of-submission");

        switch (typeOfSubmission) {
            case "newMilestone":

                try {
                    createMilestone(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
            case "markAs":
                try {
                    isCompleted(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "deleteMilestone":
                try {
                    deleteMilestone(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            default:
                RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/login.jsp");
                dispatcher.forward(request, response);
                break;
        }
    }

    private void createMilestone(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ParseException, ServletException {
        String milestones = request.getParameter("milestone");

        int userID = Integer.parseInt(request.getParameter("auth-user-id"));
        int courseworkID = Integer.parseInt(request.getParameter("coursework-id"));
        int isCompleted = 0;

        Milestones newMilestones = new Milestones(milestones, userID, courseworkID, isCompleted);
        milestonesDAO.createMilestone(newMilestones);

        HttpSession session = request.getSession();

        String email = (String) session.getAttribute("userSessionEmail");


        CourseworkProject existingCoursework = courseworkDAO.selectCoursework(courseworkID);
        User existingUser = courseworkDAO.fetchUser(email);

        List<Milestones> listOfMilestones = milestonesDAO.milestonesByCoursework(userID, courseworkID);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/milestones.jsp");
        request.setAttribute("coursework", existingCoursework);
        request.setAttribute("authUser", existingUser);
        request.setAttribute("milestonesList", listOfMilestones);
        dispatcher.forward(request, response);
    }

    private void isCompleted(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int userID = Integer.parseInt(request.getParameter("auth-user-id"));
        int courseworkID = Integer.parseInt(request.getParameter("coursework-id"));
        int status =  Integer.parseInt(request.getParameter("status"));
        int milestoneID = Integer.parseInt(request.getParameter("milestone-id"));

        Milestones markAs = new Milestones(milestoneID, status, userID, courseworkID);
        milestonesDAO.isCompleted(markAs);

        HttpSession session = request.getSession();

        String email = (String) session.getAttribute("userSessionEmail");


        CourseworkProject existingCoursework = courseworkDAO.selectCoursework(courseworkID);
        User existingUser = courseworkDAO.fetchUser(email);

        List<Milestones> listOfMilestones = milestonesDAO.milestonesByCoursework(userID, courseworkID);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/milestones.jsp");
        request.setAttribute("coursework", existingCoursework);
        request.setAttribute("authUser", existingUser);
        request.setAttribute("milestonesList", listOfMilestones);
        dispatcher.forward(request, response);

    }

    private void deleteMilestone(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {

        int userID = Integer.parseInt(request.getParameter("auth-user-id"));
        int courseworkID = Integer.parseInt(request.getParameter("coursework-id"));

        int milestoneID = Integer.parseInt(request.getParameter("milestone-id"));

        milestonesDAO.deleteMilestone(milestoneID, userID, courseworkID);

        HttpSession session = request.getSession();

        String email = (String) session.getAttribute("userSessionEmail");


        CourseworkProject existingCoursework = courseworkDAO.selectCoursework(courseworkID);
        User existingUser = courseworkDAO.fetchUser(email);

        List<Milestones> listOfMilestones = milestonesDAO.milestonesByCoursework(userID, courseworkID);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/milestones.jsp");
        request.setAttribute("coursework", existingCoursework);
        request.setAttribute("authUser", existingUser);
        request.setAttribute("milestonesList", listOfMilestones);
        dispatcher.forward(request, response);
    }

}