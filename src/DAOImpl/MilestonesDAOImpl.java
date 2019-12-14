package DAOImpl;

import DAO.MilestonesDAO;
import Model.Milestones;
import utilities.JDBCUtils;
import web.Milestone;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class MilestonesDAOImpl implements MilestonesDAO {


    private static final String INSERT_MILESTONE_SQL = "INSERT INTO milestones"
            + "  (milestones,user_id,coursework_id,is_completed) VALUES " + " (?, ?, ?, ?);";
    private static final String SELECT_MILESTONES_BY_COURSEWORK_SQL = "SELECT id, milestones, coursework_id, user_id, is_completed FROM milestones where user_id =? AND coursework_id=? ORDER BY milestones.id DESC";

    private static final String MARK_AS_COMPLETED_SQL = "UPDATE milestones SET is_completed = ? where id = ? AND user_id = ? AND coursework_id=?";

    private static final String DELETE_MILESTONE_SQL = "DELETE FROM milestones where id=? AND user_id=? AND coursework_id=?";

    public MilestonesDAOImpl(){

    }
    @Override
    public void createMilestone(Milestones milestone) throws SQLException {

        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_MILESTONE_SQL)) {
            preparedStatement.setString(1, milestone.getMilestone());
            preparedStatement.setInt(2, milestone.getUserID());
            preparedStatement.setInt(3, milestone.getCourseworkID());
            preparedStatement.setInt(4, milestone.getIsCompleted());
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            JDBCUtils.printSQLException(exception);
        }

    }

    @Override
    public List<Milestones> milestonesByCoursework(int user_id, int coursework_id) {
        List<Milestones> milestonesList = new ArrayList<>();
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MILESTONES_BY_COURSEWORK_SQL);) {
            preparedStatement.setInt(1, user_id);
            preparedStatement.setInt(2, coursework_id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                long id = rs.getLong("id");
                String milestones = rs.getString("milestones");
                int userID = rs.getInt("user_id");
                int courseworkID = rs.getInt("coursework_id");
                int isCompleted = rs.getInt("is_completed");
                milestonesList.add(new Milestones(id, milestones, userID, courseworkID, isCompleted));
            }
        } catch (SQLException exception) {
            JDBCUtils.printSQLException((SQLException) exception);
        }
        return milestonesList;
    }

    @Override
    public boolean deleteMilestone(long id, int user_id, int coursework_id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_MILESTONE_SQL);) {
            statement.setLong(1, id);
            statement.setInt(2, user_id);
            statement.setInt(3, coursework_id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    @Override
    public boolean isCompleted(Milestones milestone) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(MARK_AS_COMPLETED_SQL);) {
            statement.setLong(2, milestone.getId());
            statement.setInt(1, milestone.getIsCompleted());
            statement.setInt(3, milestone.getUserID());
            statement.setInt(4, milestone.getCourseworkID());
            rowUpdated = statement.executeUpdate() > 0;
        }

        return rowUpdated;
    }


}