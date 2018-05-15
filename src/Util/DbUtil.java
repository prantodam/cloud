package Util;
import java.sql.*;


public class DbUtil {
	private static Connection connection = null;

    public static Connection getConnection() {
        if (connection != null)
            return connection;
        else {
            try {
               
                String driver ="com.mysql.jdbc.Driver"; //prop.getProperty("driver");
               
                Class.forName(driver);
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sds-v3", "root", "root");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            } 
            return connection;
        }

    }

}
