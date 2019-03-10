package workshop2;

import org.mindrot.jbcrypt.BCrypt;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
    int id;
    String username;
    String email;
    String password;
    String hashPass;

    public int getId() {
        return id;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public String getUsername() {
        return username;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getEmail(){
        return email;
    }
    public void setPassword(String password){
        this.password= BCrypt.hashpw(password, BCrypt.gensalt());
    }
    public String getPassword(){
        return password;
    }



    public User (){
    }
    public User (String username, String email, String password){
        this.username = username;
        this.email = email;
        this.setPassword(password);
    }



    public void saveToDB(Connect connect) throws SQLException {
        if (this.id == 0) {
            String sql = "INSERT INTO users(username, email, password) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement
                    = connect.getConnection().prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, this.username);
            preparedStatement.setString(2, this.email);
            preparedStatement.setString(3, this.password);
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                this.id = rs.getInt(1);
            }
        }
    }

}
