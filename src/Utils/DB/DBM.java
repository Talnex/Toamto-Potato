package Utils.DB;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBM {

    public static Connection connection;

    public static Connection getConnection() {
        if (connection != null) {
            return connection;
        } else {
            String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
            String dbURL = "jdbc:sqlserver://139.199.112.250:1433;DatabaseName=P&T";
            String Name = "user1";
            String Pwd = "1234567890";
            Connection connection = null;
            try {
                Class.forName(driverName);
                connection = DriverManager.getConnection(dbURL, Name, Pwd);
                System.out.println("连接数据库成功");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("连接失败");
            }
            return connection;
        }
    }

}
