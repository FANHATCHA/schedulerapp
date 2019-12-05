package UserDataProcessing;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.CourseworkProjectDAO;
import DAOImpl.CourseworkProjectDAOImpl;
import DAOImpl.UserDAOImpl;
import Model.User;
import utilities.BCrypt;



//@WebServlet("UserController")
@WebServlet(name = "UserController")
public class UserController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CourseworkProjectDAO courseworkDAO;
    private UserDAOImpl userDao;

    public void init() {
        courseworkDAO = new CourseworkProjectDAOImpl();
        userDao = new UserDAOImpl();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            register(request, response);
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
    protected void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, InvalidKeySpecException, NoSuchAlgorithmException {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirm-password");

        //Fetch users
        User existingUser = courseworkDAO.fetchUser(email);
        if(!password.equals(confirmPassword)){
            request.setAttribute("NOTIFICATION", "Passwords do not match !");
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/register.jsp");
            dispatcher.forward(request, response);

        }
        else{
            if(existingUser == null) {

                String generatedSecuredPasswordHash = BCrypt.hashpw(password, BCrypt.gensalt(12));
                User user = new User();
                user.setName(name);
                user.setEmail(email);
                user.setPassword(generatedSecuredPasswordHash);

                try {
                    int result = userDao.registerUser(user);
                    if (result == 1) {
                        request.setAttribute("NOTIFICATION", "User Registered Successfully!");
                    }

                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/register.jsp");
                dispatcher.forward(request, response);
            }else{
                request.setAttribute("NOTIFICATION", "User Already exists! Login");
                RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/register.jsp");
                dispatcher.forward(request, response);
            }

        }


    }

}
