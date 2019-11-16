package DAO;

import Model.CourseworkProject;

import java.sql.SQLException;
import java.util.List;

public interface CourseworkProjectDAO {


    void storeCoursework(CourseworkProject courseworkproject) throws SQLException;

    CourseworkProject selectCoursework(long courseworkId);

    List<CourseworkProject> selectAllCourseworks();

    boolean deleteCoursework(int id) throws SQLException;

    boolean updateCoursework(CourseworkProject courseworkproject) throws SQLException;
}
