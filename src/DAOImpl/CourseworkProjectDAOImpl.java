package DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import DAO.CourseworkProjectDAO;
import Model.CourseworkProject;
import utilities.JDBCUtils;

public class CourseworkProjectDAOImpl implements CourseworkProjectDAO {

    private static final String INSERT_COURSEWORK_SQL = "INSERT INTO coursework_projects"
            + "  (courseworkTitle,moduleTitle,user_id,intended_due_date,actual_completion_date,is_done) VALUES " + " (?, ?, ?, ?, ?, ?);";

    private static final String SELECT_COURSEWORK_BY_ID = "SELECT id,courseworkTitle,moduleTitle,user_id,intended_due_date,actual_completion_date,is_done FROM coursework_projects where id =?";
    private static final String SELECT_ALL_COURSEWORKS = "SELECT * FROM coursework_projects";
    private static final String DELETE_COURSEWORK_BY_ID = "DELETE FROM coursework_projects where id = ?;";
    private static final String UPDATE_COURSEWORK = "UPDATE coursework_projects set coursework_title = ?, module_title= ?, user_id =?, intended_due_date =?, actual_completion_date =?, is_done = ? where id = ?;";

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
        return null;
    }

    @Override
    public List<CourseworkProject> selectAllCourseworks() {
        return null;
    }

    @Override
    public boolean deleteCoursework(int id) throws SQLException {
        return false;
    }

    @Override
    public boolean updateCoursework(CourseworkProject courseworkproject) throws SQLException {
        return false;
    }
}