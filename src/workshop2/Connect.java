package workshop2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {

    public static void main(String[] args) {
//        try {
//            getConnection();
//        }catch (SQLException e){
//            e.printStackTrace();
//        };
        User user = new User("Mariusz", "marianjin91@gmail.com", "1234");
        try {
            user.saveToDB(getConnection());
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    public static Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/workshop2?useSSL=false",
                "root",
                "coderslab");
        return connection;

        }
}
