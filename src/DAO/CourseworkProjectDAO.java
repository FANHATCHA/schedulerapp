package DAO;

import Model.CourseworkProject;

import java.sql.SQLException;
import java.util.List;
import Model.User;

public interface CourseworkProjectDAO {


    void storeCoursework(CourseworkProject courseworkproject) throws SQLException;

    CourseworkProject selectCoursework(long courseworkId);

    List<CourseworkProject> selectCourseworkByUser(String userEmail);

    User fetchUser(String userEmail);

    List<CourseworkProject> selectAllCourseworks();

    boolean deleteCoursework(int id) throws SQLException;

    boolean updateCoursework(CourseworkProject courseworkproject) throws SQLException;
}
