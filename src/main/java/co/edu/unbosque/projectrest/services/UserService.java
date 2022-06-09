package co.edu.unbosque.projectrest.services;
import co.edu.unbosque.projectrest.model.UserApp;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("unused")
public class UserService {

    static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String URL = "jdbc:postgresql://localhost/Laschiquistriquis";
    static final String USER = "postgres";
    static final String PASSWORD = "admin";

    public UserService(){}

    public UserApp addElement(String name, String email, String password, String role){
        System.out.println("Name = " + name + ", email = " + email + ", password = " + password + ", role = " + role);
        UserApp response = new UserApp(name, email, password, role);
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD); Statement statement = connection.createStatement()){
            Class.forName(JDBC_DRIVER);
            System.out.println("name = " + name + ", email = " + email + ", password = " + password + ", role = " + role);
            String query = "INSERT INTO userApp(name, email, password, role) VALUES ('" + name +"', '" + email +"', '" + password +"', '" + role +"')";
            statement.execute(query);
        } catch(SQLException sqlException) {
            sqlException.printStackTrace();
        } catch (ClassNotFoundException classNotFoundException){
            classNotFoundException.printStackTrace();
        }
        return response;
    }



    public UserApp login(String username, String password){
        List <UserApp> list = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD); Statement statement = connection.createStatement()){
            Class.forName(JDBC_DRIVER);
            String query = "SELECT  * FROM userApp";
            ResultSet result = statement.executeQuery(query);
            while(result.next()) {
                list.add(new UserApp(result.getString("name"),
                        result.getString("email"),
                        result.getString("password"),
                        result.getString("role")));
            }
            return list.stream()
                       .filter(x -> x.getName().equals(username) && x.getPassword().equals(password))
                       .findFirst()
                       .orElse(new UserApp());

        } catch(SQLException sqlException) {
            sqlException.printStackTrace();
        } catch (ClassNotFoundException classNotFoundException){
            classNotFoundException.printStackTrace();
        }
        return new UserApp();
    }
}
