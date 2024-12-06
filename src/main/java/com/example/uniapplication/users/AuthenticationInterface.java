package com.example.uniapplication.autentication;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;

import java.io.IOException;

public interface AuthenticationInterface {
    void login(String username, String password, Button button, FXMLLoader loader) throws IOException;
}
