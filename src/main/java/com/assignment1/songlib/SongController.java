package com.assignment1.songlib;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

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
    private ListView<String> SongListView = new ListView<>(); //List view in song list fxml

    @FXML
    private TextField SongNameField;

    @FXML
    private TextField YearField;

    private List<Song> Songs = new ArrayList<>(); //List of song objects with all details of the song
    private ObservableList<String> obsSongList = FXCollections.observableArrayList(); //Observable list to use for listview to display song names

    public void start(){
        // Load the data from the CSV file
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/java/com/assignment1/songlib/data.csv"))) {
            String line;
            Song song;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                String songName = values[0];
                String artist = values[1];
                String album = " ";
                String year = " ";


                if(values.length > 2){ //if the line > 2, there is an album to be displayed
                    album = values[2];
                }
                if(values.length > 3) { //if the line > 3, there is a year to be displayed
                    year = values[3];
                }

                song = new Song(songName, artist, album, year);
                Songs.add(song); //adding to Song object list
            }
        } catch (IOException e) {
            // Handle the exception
        }
        Collections.sort(Songs, Song.TITLE_COMPARATOR); //sorts the Songs list based on title

        for(Song song: Songs){ //adding to observable song list to be used in ListView
            obsSongList.add(song.getName());
        }

        SongListView.setItems(obsSongList);
    }

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
