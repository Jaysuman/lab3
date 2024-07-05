package com.example.lab3;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import java.sql.*;

public class HelloController {
    public TextField username;
    public TextField password;
    public Button login;
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {


        String name = username.getText();
        String Password=password.getText();


        String jdbcUrl = "jdbc:mysql://localhost:3306/lab3";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser,
                dbPassword)) {
            // Execute a SQL query to retrieve data from the database
            String query = "SELECT * FROM user where username='"+name+"' AND password='"+Password+"'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            // Populate the table with data from the database
            if (resultSet.next()) {

                try {

                    Parent secondScene = FXMLLoader.load(getClass().getResource("dashboard.fxml"));

                    Stage secondStage = new Stage();
                    secondStage.setTitle("Login Page");
                    secondStage.setScene(new Scene(secondScene));

                    Stage firstSceneStage = (Stage) login.getScene().getWindow();
                    firstSceneStage.close();



                    secondStage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else{
                welcomeText.setText("invalid username or password");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }






}