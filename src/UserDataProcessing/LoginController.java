package UserDataProcessing;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.CourseworkProjectDAO;
import DAO.LoginDAO;
import DAO.UserDAO;
import DAOImpl.CourseworkProjectDAOImpl;
import DAOImpl.UserDAOImpl;
import Model.CourseworkProject;
import Model.LoginBean;
import Model.User;
import utilities.BCrypt;


//@WebServlet("LoginController")
@WebServlet(name = "LoginController")
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private LoginDAO loginDao;
    private CourseworkProjectDAO courseworkProjectDAO;


    public void init() {

        loginDao = new LoginDAO();
        courseworkProjectDAO = new CourseworkProjectDAOImpl();
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            authenticate(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    protected void authenticate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User existingUser = courseworkProjectDAO.fetchUser(email);

//        System.out.println("Existing password " + existingUser.getPassword());
//        System.out.println("Generated password " + password);
        boolean matched = BCrypt.checkpw(password, existingUser.getPassword());
       if(matched == true){
           System.out.println("Matching status " + matched);
           LoginBean loginBean = new LoginBean();
           loginBean.setEmail(email);
           loginBean.setPassword(password);

           try {
               if (loginDao.validate(loginBean)) {

                   List<CourseworkProject> listCoursework = courseworkProjectDAO.selectCourseworkByUser(email);
                   request.setAttribute("listCoursework", listCoursework);
                   request.setAttribute("sizeOfCoursework", listCoursework.size());
                   request.setAttribute("authUser", existingUser);

                   HttpSession session = request.getSession();
                   session.setAttribute("userSessionEmail", email);

                   RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/dashboard.jsp");
                   dispatcher.forward(request, response);
               } else {
                   response.sendRedirect("index.jsp");
               }
           } catch (ClassNotFoundException e) {
               e.printStackTrace();
           }
       }else{
           System.out.println("Matching status: " + matched);
           response.sendRedirect("index.jsp");
       }

    }


}
