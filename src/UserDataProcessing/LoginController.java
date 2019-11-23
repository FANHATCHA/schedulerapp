package UserDataProcessing;

import java.io.IOException;
import java.io.PrintWriter;
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
import DAOImpl.CourseworkProjectDAOImpl;
import Model.CourseworkProject;
import Model.LoginBean;


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
        authenticate(request, response);
    }
    protected void authenticate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        LoginBean loginBean = new LoginBean();
        loginBean.setEmail(email);
        loginBean.setPassword(password);

        try {
            if (loginDao.validate(loginBean)) {
//                PrintWriter out = new PrintWriter(System.out);
                List<CourseworkProject> listCoursework = courseworkProjectDAO.selectAllCourseworks();
                request.setAttribute("listCoursework", listCoursework);
                request.setAttribute("sizeOfCoursework", listCoursework.size());
                RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/dashboard.jsp");
                dispatcher.forward(request, response);
            } else {
                HttpSession session = request.getSession();
                 session.setAttribute("user", email);
                 response.sendRedirect("index.jsp");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


}
