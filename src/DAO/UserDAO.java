package DAO;

import Model.User;

import java.sql.SQLException;

public interface UserDAO  {

    public int registerUser(User user) throws ClassNotFoundException;
}
