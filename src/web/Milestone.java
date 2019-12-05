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
import java.util.List;

@WebServlet(name = "Milestone")
public class Milestone extends HttpServlet {
    private CourseworkProjectDAO courseworkDAO;
    private MilestonesDAO milestonesDAO;

    public void init() {

        courseworkDAO = new CourseworkProjectDAOImpl();
        milestonesDAO = new MilestonesDAOImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int courseworkID = Integer.parseInt(request.getParameter("id"));
        String email = (String) session.getAttribute("userSessionEmail");
        CourseworkProject existingCoursework = courseworkDAO.selectCoursework(courseworkID);
        User existingUser = courseworkDAO.fetchUser(email);
        int userID = Integer.parseInt(request.getParameter("i"));

        List<Milestones> listOfMilestones = milestonesDAO.milestonesByCoursework(userID, courseworkID);

        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/milestones.jsp");
        request.setAttribute("coursework", existingCoursework);
        request.setAttribute("authUser", existingUser);
        request.setAttribute("milestonesList", listOfMilestones);
        dispatcher.forward(request, response);
    }
}