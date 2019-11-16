package DB;

import DB.Provider;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionProvider implements Provider {

    static Connection con = null;

    public static Connection getCon(){

        try {
            //Java Database Connectivity
            Class.forName("com.mysql.cj.jdbc.Driver");
//            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(connectorUrl, username, password);

        }catch (Exception e){
            System.out.println(e);
        }
        return con;

    }
}
