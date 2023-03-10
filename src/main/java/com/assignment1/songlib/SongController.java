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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.StageStyle;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Optional;

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
    private Text SongDetails;

    @FXML
    private ListView<String> SongListView = new ListView<>(); //List view in song list fxml

    @FXML
    private TextField SongNameField;

    @FXML
    private TextField YearField;

    private List<Song> Songs = new ArrayList<>(); //List of song objects with all details of the song
    private ObservableList<String> obsSongList = FXCollections.observableArrayList(); //Observable list to use for listview to display song names

    //Only one song can be selected at a time
    // will be initalized once a song is selected from songListView
    private Song selectedSong;
    //selected song index from listView
    //same order(index) as List<Song> Songs because they are sorted
    private int selectedIndex;
    public void start(){
        // Load the data from the CSV file
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/java/com/assignment1/songlib/data.csv"))) {
            String line;
            Song song;
            while ((line = br.readLine()) != null) {
                String[] values = line.split("\\|");
                String songName = values[0];
                String artist = values[1];
                String album = "";
                String year = "";


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

        if(Songs.size() != 0){
            SongListView.getSelectionModel().select(0);
            SongDetails.setText("Song Name: " + Songs.get(0).getName() + "\n"
                    + "Artist: " + Songs.get(0).getArtist() + "\n"
                    + "Album: " + Songs.get(0).getAlbum() + "\n"
                    + "Year: " + Songs.get(0).getYear());
        }

    }

    public static boolean isNumeric(String str) {
        try {
            int d = Integer.parseInt(str);
            return d > 0;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }
    @FXML
    void addSong(ActionEvent event) {

        String name = SongNameField.getText().trim();
        String artist = ArtistField.getText().trim();
        String album = AlbumField.getText().trim();
        String year = YearField.getText().trim();

        if(name.trim().isEmpty() || artist.trim().isEmpty()){
            ErrorMsg.setText("Error: Name and Artist Required");
            return;
        }

        if(name.contains("|") || artist.contains("|") || album.contains("|") || year.contains("|")){
            ErrorMsg.setText("| is an illegal character");
            return;
        }

        if(!year.isEmpty()) {
            if (!isNumeric(year)) {
                ErrorMsg.setText("Year must be positive number");
                return;
            }

            if(Integer.parseInt(year) > 2023 || Integer.parseInt(year) < 0){
                ErrorMsg.setText("Year must be between 0 & 2023");
                return;
            }
        }

        for(Song song: Songs){
            if(name.trim().equalsIgnoreCase(song.getName()) && artist.trim().equalsIgnoreCase(song.getArtist())){
                ErrorMsg.setText("Error: No duplicate songs");
                return;
            }
        }
        // Create a new alert dialog
        Alert alert = new Alert(AlertType.CONFIRMATION);

        // Set the dialog title and message
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Are you sure you want to add this song?");
        alert.setContentText("Click OK to confirm or Cancel to abort.");

        // Show the dialog and wait for the user's response
        Optional<ButtonType> result = alert.showAndWait();

        // Check the user's response
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // User clicked OK, add the item
            obsSongList.add(name.trim());
            Song newSong = new Song(name.trim(), artist.trim(), album, year);
            Songs.add(newSong);

            Collections.sort(obsSongList, String.CASE_INSENSITIVE_ORDER);
            Collections.sort(Songs, Song.TITLE_COMPARATOR);

            SongListView.setItems(obsSongList);

            SongDetails.setText("Song Name: " + name.trim() + "\n"
                    + "Artist: " + artist.trim() + "\n"
                    + "Album: " + album.trim() + "\n"
                    + "Year: " + year.trim());

            int addedSongIndex = Songs.indexOf(newSong);
            SongListView.getSelectionModel().select(addedSongIndex);

            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/java/com/assignment1/songlib/data.csv", true));
                bw.write(newSong.toString());
                bw.newLine();
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // User clicked Cancel or closed the dialog, do nothing
            ErrorMsg.setText(" ");
            return;
        }
        ErrorMsg.setText(" ");
    }

    public void deleteSongFromCSV(Song songToDelete, String fileName) throws IOException {
        List<Song> songs = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split("\\|");
                String songName = values[0];
                String artist = values[1];
                String album = "";
                String year = "";


                if(values.length > 2){ //if the line > 2, there is an album to be displayed
                    album = values[2];
                }
                if(values.length > 3) { //if the line > 3, there is a year to be displayed
                    year = values[3];
                }

                Song song = new Song(songName, artist, album, year);
                if (!song.toString().equals(songToDelete.toString())) {
                    songs.add(song);
                }
            }
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (Song song : songs) {
                bw.write(song.getName() + "|" + song.getArtist() + "|" + song.getAlbum() + "|" + song.getYear() + "\n");
            }
        }
    }
    @FXML
    void deleteSong(ActionEvent event) throws IOException {
        String name = selectedSong.getName();
        String artist = selectedSong.getArtist();
        String album = selectedSong.getAlbum();
        String year = selectedSong.getYear();
        String fullSong;

        Song songToDelete = Songs.get(selectedIndex);
        fullSong = songToDelete.toString();


        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Are you sure you want to delete this song?");
        alert.setContentText("Click OK to confirm or Cancel to abort.");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            // User clicked Ok so delete the song
            obsSongList.remove(name);
            Songs.remove(selectedIndex);

            Collections.sort(obsSongList, String.CASE_INSENSITIVE_ORDER);
            Collections.sort(Songs, Song.TITLE_COMPARATOR);

            SongListView.setItems(obsSongList);

            if(Songs.size() == 0){
                SongDetails.setText("");
            }

            else if(selectedIndex > Songs.size()-1){
                selectedIndex-=1;
                SongDetails.setText("Song Name: " + Songs.get(selectedIndex).getName().trim() + "\n"
                        + "Artist: " + Songs.get(selectedIndex).getArtist().trim() + "\n"
                        + "Album: " + Songs.get(selectedIndex).getAlbum().trim() + "\n"
                        + "Year: " + Songs.get(selectedIndex).getYear().trim());
                SongListView.getSelectionModel().select(selectedIndex);
            }
            else{
                SongDetails.setText("Song Name: " + Songs.get(selectedIndex).getName().trim() + "\n"
                        + "Artist: " + Songs.get(selectedIndex).getArtist().trim() + "\n"
                        + "Album: " + Songs.get(selectedIndex).getAlbum().trim() + "\n"
                        + "Year: " + Songs.get(selectedIndex).getYear().trim());
                SongListView.getSelectionModel().select(selectedIndex);
            }

            deleteSongFromCSV(songToDelete, "src/main/java/com/assignment1/songlib/data.csv");

        }
        else{
            return;
        }

    }

    @FXML
    void editSongDetails(ActionEvent event) throws IOException{
        if(selectedSong==null){
            ErrorMsg.setText("Click on a song from the list.");
            return;
        }
        String name = selectedSong.getName();
        String artist = selectedSong.getArtist();
        String album = selectedSong.getAlbum();
        String year = selectedSong.getYear();

        Song oldSong = selectedSong;
        deleteSongFromCSV(oldSong, "src/main/java/com/assignment1/songlib/data.csv");

        String newName = SongNameField.getText().trim();
        String newArtist = ArtistField.getText().trim();
        String newAlbum = AlbumField.getText().trim();
        String newYear = YearField.getText().trim();

        //Checking that there is a song name or artist
        if((newName.trim().isEmpty())||(newArtist.trim().isEmpty())){
            ErrorMsg.setText("Error: Song is blank.");
            return;
        }
        //Checking that year is valid
        if(!newYear.isEmpty()){
            if (!isNumeric(newYear)) {
                ErrorMsg.setText("Year must be positive number");
                return; }
            if(Integer.parseInt(newYear) > 2023 || Integer.parseInt(newYear) < 0){
                ErrorMsg.setText("Year must be between 0 & 2023");
                return; }
        }
        //Sends confirmation popup
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Are you sure you want to edit this song?");
        alert.setContentText("Click OK to confirm or Cancel to abort.");
        Optional<ButtonType> result = alert.showAndWait();

        //User pressed Ok, continue with editing
        if (result.isPresent() && result.get() == ButtonType.OK) {
            //Removes song to edit to make sure there are no duplicates in rest of list
            int index = selectedIndex;  //Doesn't forget index
            //Song oldSong = Songs.get(selectedIndex);
            obsSongList.remove(name);
            Song songToEdit = oldSong;
            Songs.remove(index);
            //Checks entire list to make sure there is no duplicate
            for(Song song: Songs){
                //Searches for duplicate song without current song interfering
                if(newName.equalsIgnoreCase(song.getName().trim()) && newArtist.equalsIgnoreCase(song.getArtist().trim())){
                    ErrorMsg.setText("Error: Song already exists.");
                    return;
                }   }
            //Edits all fields
            songToEdit.setName(newName);
            obsSongList.add(newName.trim());
            songToEdit.setArtist(newArtist);
            if(AlbumField.getText().isEmpty()){
                songToEdit.setAlbum(" "); }
            else{
                songToEdit.setAlbum(newAlbum);  }
            if(YearField.getText().isEmpty()){
                songToEdit.setYear(" "); }
            else{
                songToEdit.setYear(newYear);    }

            //Adds back the edited song
            Songs.add(songToEdit);

            //Sorts new lists
            Collections.sort(obsSongList, String.CASE_INSENSITIVE_ORDER);
            Collections.sort(Songs, Song.TITLE_COMPARATOR);

            SongListView.setItems(obsSongList);

            //Deletes old song and appends new one
            Song newSong = Songs.get(selectedIndex);
            try{
                BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/java/com/assignment1/songlib/data.csv", true));
                bw.write(newSong.toString());
                bw.newLine();
                bw.close();
            } catch (IOException e){
                e.printStackTrace();
            }   }
        else{
            ErrorMsg.setText(" ");
            return; }
        ErrorMsg.setText(" ");
    }
    @FXML
    void onListViewSongClicked(MouseEvent event) {
        /*if(event.getTarget()==null){
            ErrorMsg.setText("Click on a song from the list.");
            return;
        }*/
        selectedIndex = SongListView.getSelectionModel().getSelectedIndex();
        selectedSong = Songs.get(selectedIndex);

        String Name = selectedSong.getName();
        String Artist = selectedSong.getArtist();
        String Album = selectedSong.getAlbum();
        String Year = selectedSong.getYear();

        SongDetails.setText("Song Name: " + Name + "\n"
                            + "Artist: " + Artist + "\n"
                            + "Album: " + Album + "\n"
                            + "Year: " + Year);
    }
}
