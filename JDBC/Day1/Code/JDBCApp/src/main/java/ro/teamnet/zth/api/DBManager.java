package ro.teamnet.zth.api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by ramona.arsene on 7/13/2017.
 */
public class DBManager {

    private static final String CONNECTION_STRING= "jdbc:oracle:thin:@" + DBProperties.IP + ":" + DBProperties.PORT;

    DBManager() throws UnsupportedOperationException{

    }

    private static void registerDriver(){

        try {
            Class.forName(DBProperties.DRIVER_CLASS);
        }
        catch(ClassNotFoundException ex) {
            System.out.println("Error: unable to load driver class!");
            System.exit(1);
        }


    }

    public static Connection getConnection() throws SQLException {
        String URL = "jdbc:oracle:thin:@localhost:1521:xe";
        Connection con = DriverManager.getConnection(URL, DBProperties.USER, DBProperties.PASS);
        return con;
    }

    public static boolean checkConnection (Connection connection){
        try (Statement stmt = connection.createStatement( )){
           return stmt.execute("SELECT 1 FROM DUAL");
        }
        catch (SQLException e) {
        }
    return false;
    }

}


