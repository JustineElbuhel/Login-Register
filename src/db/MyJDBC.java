package db;

import com.mysql.cj.jdbc.exceptions.MySQLTimeoutException;
import constants.CommonConstants;

import java.sql.*;

// JDBC - Java Database Connectivity
// this class will be out gateway in accessing our MySQL database
public class MyJDBC {
    // register new user to the database
    // true - register success
    // false - register failure
    public static boolean register(String username, String password){
        try{
            // first check if the username already exists in the db
            if(!checkUsername(username)){
                // connect to the db
                Connection connection = DriverManager.getConnection(CommonConstants.DB_URL,
                        CommonConstants.DB_USERNAME, CommonConstants.DB_PASSWORD);

                // create insert query
                PreparedStatement insertUser = connection.prepareStatement(
                        "INSERT INTO " + CommonConstants.DB_USERS_TABLE_NAME +
                                " (username, password)" + "Values(?, ?)"
                );

                // insert parameters in the insert query
                insertUser.setString(1, username);
                insertUser.setString(2, password);

                // update db with the new user
                insertUser.executeUpdate();
                return true;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    // check if the username exists in the db
    // false - username does not exist
    // true - username exists in the db
    public static boolean checkUsername(String username){
        try{
            Connection connection = DriverManager.getConnection(CommonConstants.DB_URL,
                    CommonConstants.DB_USERNAME, CommonConstants.DB_PASSWORD);

            // create Query using PreparedStatement
            PreparedStatement checkUserExists = connection.prepareStatement(
                    "SELECT * FROM " + CommonConstants.DB_USERS_TABLE_NAME +
                            " WHERE USERNAME = ?"
            );
            // replace the "?" with values using the setString()
            checkUserExists.setString(1, username);

            // store result in a ResultSet that we will be able to access
            ResultSet resultSet = checkUserExists.executeQuery();

            // check to see if the result set is empty
            // if it is empty it means that there was no data row that contains the username
            if(!resultSet.isBeforeFirst()){
                return false;
            }
        } catch(SQLException e){
            e.printStackTrace();
        }

        return true;
    }

    // validate login credentials by checking to see if username/password pair exists in the db
    public static boolean validateLogin(String username, String password){
        try{
            Connection connection = DriverManager.getConnection(CommonConstants.DB_URL,
                    CommonConstants.DB_USERNAME, CommonConstants.DB_PASSWORD);

            // create select query
            PreparedStatement validateUser = connection.prepareStatement(
                    "SELECT * FROM " + CommonConstants.DB_USERS_TABLE_NAME +
                            " WHERE USERNAME = ? AND PASSWORD = ?"
            );
            validateUser.setString(1, username);
            validateUser.setString(2, password);

            ResultSet resultSet = validateUser.executeQuery();

            if(!resultSet.isBeforeFirst()){
                return false;
            }
        } catch(SQLException e){
            e.printStackTrace();
        }

        return true;
    }
}
















