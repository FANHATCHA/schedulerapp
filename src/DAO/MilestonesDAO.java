package DAO;

import Model.Milestones;

import java.sql.SQLException;
import java.util.List;

public interface MilestonesDAO {
    void createMilestone(Milestones Milestones) throws SQLException;
    List<Milestones> milestonesByCoursework(int user_id, int coursework_id);
    boolean deleteMilestone(long id, int user_id, int coursework_id) throws SQLException;
    boolean isCompleted(Milestones milestone) throws SQLException;
}
