package com.assignment1.songlib;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.control.ListView;
import java.util.*;
import javafx.scene.Parent;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import java.io.IOException;

public class SongLib extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SongLib.class.getResource("hello-view.fxml"));
        BorderPane root = (BorderPane)fxmlLoader.load();

        SongController songController = fxmlLoader.getController();
        songController.start();

        Scene scene = new Scene(root, 525, 350);

        stage.setTitle("Song Library");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}