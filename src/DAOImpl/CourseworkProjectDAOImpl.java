package DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import DAO.CourseworkProjectDAO;
import Model.CourseworkProject;
import Model.User;
import utilities.JDBCUtils;

public class CourseworkProjectDAOImpl implements CourseworkProjectDAO  {

    private static final String INSERT_COURSEWORK_SQL = "INSERT INTO coursework_projects"
            + "  (courseworkTitle,moduleTitle,user_id,intended_due_date,actual_completion_date,is_done) VALUES " + " (?, ?, ?, ?, ?, ?);";

    private static final String SELECT_COURSEWORK_BY_ID = "SELECT id,courseworkTitle,moduleTitle,user_id,intended_due_date,actual_completion_date,is_done FROM coursework_projects where id =?";
    private static final String SELECT_ALL_COURSEWORKS = "SELECT * FROM coursework_projects";
    private static final String SELECT_COURSEWORKS_BY_USERID = "SELECT *  FROM coursework_projects INNER JOIN users ON coursework_projects.user_id=users.id where users.email =?";
    private static final String FETCH_BY_UNIQUE_EMAIL = "SELECT name, id, email, password FROM users where email =?";
    private static final String DELETE_COURSEWORK_BY_ID = "DELETE FROM coursework_projects where id = ?";
    private static final String UPDATE_COURSEWORK = "UPDATE coursework_projects SET courseworkTitle = ?, moduleTitle= ?, user_id =?, intended_due_date =?, actual_completion_date =?, is_done = ? where id = ?;";

    public CourseworkProjectDAOImpl() {
    }

    @Override
    public void storeCoursework(CourseworkProject coursework) throws SQLException {
        System.out.println(INSERT_COURSEWORK_SQL);
        // try-with-resource statement will auto close the connection.
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_COURSEWORK_SQL)) {
            preparedStatement.setString(1, coursework.getCourseworkTitle());
            preparedStatement.setString(2, coursework.getModuleTitle());
            preparedStatement.setInt(3, coursework.getUserID());
            preparedStatement.setString(4, JDBCUtils.getDateString(coursework.getIntendedDueDate()));
            preparedStatement.setString(5, JDBCUtils.getDateString(coursework.getActualCompletionDate()));
            preparedStatement.setBoolean(6,coursework.getStatus());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            JDBCUtils.printSQLException(exception);
        }
    }


    @Override
    public CourseworkProject selectCoursework(long courseworkId) {

        CourseworkProject coursework = null;
        // Step 1: Establishing a Connection
        try (Connection connection = JDBCUtils.getConnection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_COURSEWORK_BY_ID);) {
            preparedStatement.setLong(1, courseworkId);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                long id = rs.getLong("id");
                String courseworkTitle = rs.getString("courseworkTitle");
                String moduleTitle = rs.getString("moduleTitle");
                int userID = rs.getInt("user_id");
                Calendar intendedDueDate = JDBCUtils.getDateCalendar(rs.getString("intended_due_Date"));
                Calendar actualCompletionDate = JDBCUtils.getDateCalendar(rs.getString("actual_completion_date"));
                boolean isDone = rs.getBoolean("is_done");
                coursework = new CourseworkProject(id, courseworkTitle, moduleTitle, intendedDueDate, actualCompletionDate, userID, isDone);
            }
        } catch (SQLException | ParseException exception) {
            JDBCUtils.printSQLException((SQLException) exception);
        }
        return coursework;
    }
    @Override
    public List<CourseworkProject> selectCourseworkByUser(String userEmail) {

        List<CourseworkProject> courseworks = new ArrayList<>();
        // Step 1: Establishing a Connection
        try (Connection connection = JDBCUtils.getConnection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_COURSEWORKS_BY_USERID );) {
            preparedStatement.setString(1, userEmail);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                long id = rs.getLong("id");
                String courseworkTitle = rs.getString("coursework_projects.courseworkTitle");
                String moduleTitle = rs.getString("moduleTitle");
                int userID = rs.getInt("user_id");
                Calendar intendedDueDate = JDBCUtils.getDateCalendar(rs.getString("intended_due_Date"));
                Calendar actualCompletionDate = JDBCUtils.getDateCalendar(rs.getString("actual_completion_date"));
                boolean isDone = rs.getBoolean("is_done");
//                System.out.println("----Start");
                courseworks.add(new CourseworkProject(id, courseworkTitle, moduleTitle, intendedDueDate, actualCompletionDate, userID, isDone));
                System.out.println(courseworks);
            }
        } catch (SQLException | ParseException exception) {
            JDBCUtils.printSQLException((SQLException) exception);
        }
        return courseworks;
    }
    @Override
    public List<CourseworkProject> selectAllCourseworks(){

        // using try-with-resources to avoid closing resources (boiler plate code)
        List<CourseworkProject> courseworks = new ArrayList<>();

        // Step 1: Establishing a Connection
        try (Connection connection = JDBCUtils.getConnection();

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_COURSEWORKS );) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                long id = rs.getLong("id");
                String courseworkTitle = rs.getString("courseworkTitle");
                String moduleTitle = rs.getString("moduleTitle");
                int userID = rs.getInt("user_id");
                Calendar intendedDueDate = JDBCUtils.getDateCalendar(rs.getString("intended_due_Date"));
                Calendar actualCompletionDate = JDBCUtils.getDateCalendar(rs.getString("actual_completion_date"));
                boolean isDone = rs.getBoolean("is_done");
                courseworks.add(new CourseworkProject(id, courseworkTitle, moduleTitle, intendedDueDate, actualCompletionDate, userID, isDone));
            }
        } catch (SQLException | ParseException exception) {
            JDBCUtils.printSQLException((SQLException) exception);
        }
        return courseworks;
    }

    @Override
    public boolean deleteCoursework(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_COURSEWORK_BY_ID);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    @Override
    public boolean updateCoursework(CourseworkProject courseworkproject) throws SQLException {

        boolean rowUpdated;
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_COURSEWORK);) {
            statement.setString(1, courseworkproject.getCourseworkTitle());
            statement.setString(2, courseworkproject.getModuleTitle());
            statement.setInt(3, courseworkproject.getUserID());
            statement.setString(4, JDBCUtils.getDateString(courseworkproject.getIntendedDueDate()));
            statement.setString(5, JDBCUtils.getDateString(courseworkproject.getActualCompletionDate()));
            statement.setBoolean(6, courseworkproject.getStatus());
            statement.setLong(7, courseworkproject.getId());
            rowUpdated = statement.executeUpdate() > 0;
        }

        return rowUpdated;
    }

    @Override
    public User fetchUser(String userEmail) {

        User user = null;
        // Step 1: Establishing a Connection
        try (Connection connection = JDBCUtils.getConnection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(FETCH_BY_UNIQUE_EMAIL);) {
            preparedStatement.setString(1, userEmail);
            System.out.println("1st st"+ preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                long id = rs.getLong("id");
                String email = rs.getString("email");
                String name = rs.getString("name");
                String password = rs.getString("password");

                user = new User(id, email, name, password);
            }
        } catch (SQLException exception) {
            JDBCUtils.printSQLException((SQLException) exception);
        }
        return user;
    }


}