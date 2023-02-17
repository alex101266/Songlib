package com.assignment1.songlib;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.io.IOException;

public class SongLibraryApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SongLibraryApp.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 525, 350);

        stage.setTitle("Song Library");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}