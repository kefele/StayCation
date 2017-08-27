package com.company.model;

import com.company.controller.User;
import org.jasypt.util.password.StrongPasswordEncryptor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class DAO {

    StrongPasswordEncryptor enc = new StrongPasswordEncryptor();

    public static ArrayList<User> getUserList() {
        // define the data for the connection
        //connection info was pulled out into DBCredentials
        //so that file can be hidden

        try {
            // Load driver
            Class.forName("com.mysql.jdbc.Driver");
            // DriverManager.registerDriver(new com.mysql.jdbc.Driver());

            // create the db connection object
            Connection mysqlConnection;
            mysqlConnection = DriverManager.getConnection(
                    DBCredentials.DB_ADDRESS,
                    DBCredentials.USERNAME,
                    DBCredentials.PASSWORD);

            // create the db statement

            String readUserCommand = "select userId,fname, lname, email, cellphone, password from users";

            Statement readUsers = mysqlConnection.createStatement();// creates the statement

            ResultSet results = readUsers.executeQuery(readUserCommand);// executes the statement
            // array list of users
            ArrayList<User> userList = new ArrayList<User>();

            // map from the ResultSet to the ArrayList
            while (results.next()) {
                User temp = new User(results.getString(1),
                        results.getString(2), results.getString(3),
                        results.getString(4), results.getString(5), results.getString(6));
                userList.add(temp);

            }
            for (User i:userList
                 ) {
                System.out.println(i);
            }

            return userList;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null; //null result indicates an issue
        }
    }


    public static ArrayList<Building> getBuildingList() {
        // define the data for the connection
        //connection info was pulled out into DBCredentials
        //so that file can be hidden

        try {
            // Load driver
            Class.forName("com.mysql.jdbc.Driver");
            // DriverManager.registerDriver(new com.mysql.jdbc.Driver());

            // create the db connection object
            Connection mysqlConnection;
            mysqlConnection = DriverManager.getConnection(
                    DBCredentials.DB_ADDRESS,
                    DBCredentials.USERNAME,
                    DBCredentials.PASSWORD);

            // create the db statement

            String readBuildingCommand = "select buildingId,  buildingName,  buildingAddress,  buildingDescription,  buildingImage,  qLineStops from architecture";

            Statement readBuilding = mysqlConnection.createStatement();// creates the statement

            ResultSet results = readBuilding.executeQuery(readBuildingCommand);// executes the statement
            // array list of Buildings
            ArrayList<Building> buildingList = new ArrayList<Building>();

            // map from the ResultSet to the ArrayList
            while (results.next()) {
                Building temp = new Building(results.getString(1),
                        results.getString(2), results.getString(3),
                        results.getString(4), results.getString(5), results.getString(6));
                buildingList.add(temp);

            }
            for (Building i:buildingList
                    ) {
                System.out.println(i);
            }

            return buildingList;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null; //null result indicates an issue
        }
    }


    public static boolean addUser(
            String userId,
            String fName,
            String lName,
            String email,
            String Cphone,
            String password
    ) {

        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection mysqlConnection;
            mysqlConnection = DriverManager.getConnection(
                    DBCredentials.DB_ADDRESS,
                    DBCredentials.USERNAME,
                    DBCredentials.PASSWORD);

            String addUserCommand = "INSERT INTO users " +
                    "(userId, fName, lName, email, cellPhone, password) " +
                    "VALUES ('" +
                    userId + "', '" +
                    fName + "', '" +
                    lName + "', '" +
                    email + "', '" +
                    Cphone + "', '" +

                    password + "')";
            System.out.println("SQL Query " + addUserCommand);

            Statement st = mysqlConnection.createStatement();// creates the statement

            int result = st.executeUpdate(addUserCommand);// executes the statement
            // array list of users

            if (result == 1)
                return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            //null result indicates an issue
        }
        return false;

    }


    public static boolean login(
            String userId,
            String password

    ) {


        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection mysqlConnection;
            mysqlConnection = DriverManager.getConnection(
                    DBCredentials.DB_ADDRESS,
                    DBCredentials.USERNAME,
                    DBCredentials.PASSWORD);

            String readUserCommand = "select userId, fname, lname, email, cellphone, password from users where userId='"
            + userId + "'";

            Statement readUsers = mysqlConnection.createStatement();// creates the statement

            ResultSet results = readUsers.executeQuery(readUserCommand);// executes the statement
            // array list of users
            ArrayList<User> userList = new ArrayList<User>();

            // map from the ResultSet to the ArrayList
            while (results.next()) {
//                User temp = new User(results.getString(1),
//                        results.getString(2), results.getString(3),
//                        results.getString(4), results.getString(5), results.getString(6));
                String dbResPassword = results.getString(6);
                StrongPasswordEncryptor enc = new StrongPasswordEncryptor();
                boolean match = enc.checkPassword(password, dbResPassword);

                if (match) {
                    return true;
                }
            }

            return false;

        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return true;
    }

    public static boolean deleteUser(String userId) {

        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection mysqlConnection;
            mysqlConnection = DriverManager.getConnection(
                    DBCredentials.DB_ADDRESS,
                    DBCredentials.USERNAME,
                    DBCredentials.PASSWORD);

            String deleteUserCommand = "DELETE FROM users " +
                    "WHERE userId = '" +
                    userId + "'";
            System.out.println("SQL Deletion Query " + deleteUserCommand);

            Statement st = mysqlConnection.createStatement();// creates the statement

            int result = st.executeUpdate(deleteUserCommand);// executes the statement
            // array list of users

            //if (result == 1)
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false; //null result indicates an issue
        }
    }
}
