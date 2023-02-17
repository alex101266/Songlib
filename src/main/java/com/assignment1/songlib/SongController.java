package com.assignment1.songlib;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SongController {

    @FXML
    private Button AddBtn;

    @FXML
    private TextField AlbumField;

    @FXML
    private TextField ArtistField;

    @FXML
    private Button DeleteBtn;

    @FXML
    private Button EditBtn;

    @FXML
    private Label ErrorMsg;

    @FXML
    private TextArea SongDetails;

    @FXML
    private ListView<?> SongList;

    @FXML
    private TextField SongNameField;

    @FXML
    private TextField YearField;

    @FXML
    void addSong(ActionEvent event) {

    }

    @FXML
    void deleteSong(ActionEvent event) {

    }

    @FXML
    void editSongDetails(ActionEvent event) {

    }

}
