package com.assignment1.songlib;
import java.io.*;
import java.util.*;
//adding random stuff that'll have to be integrated with gui elements

ArrayList<Integer> array = new ArrayList<Integer>(1);




//addSong code pieces; will place where it needs to be when gui is ready for it.

/*if((album==null)&&(year==null)){
    addSongNoAlbumNoYear(name,artist); }
else if(album==null){
    addSongNoAlbum(name,artist,year);   }
else if(year==null){
    addSongNoYear(name,artist,album);   }
else{
    addSong(name,artist,album,year);    }*/

//May be able to combine both parts together, depending on how the buttons, list, and storage work
public Song addSong(String name, String artist, String album, String year){
    newSong = new Song(name,artist,album,year);
    return newSong; }
public Song addSongNoAlbum(String name, String artist, String year){
    newSong = new Song(name,artist,null,year);
    return newSong; }
public Song addSongNoYear(String name, String artist, String album){
    newSong = new Song(name,artist,album,null);
    return newSong; }
public void addSongNoAlbumNoYear(String name, String artist){

    newSong = new Song(name,artist,null,null);
    return newSong; }

//editSong code pieces; have edit button next two each parameter for a song
public Song editName(String newName, Song song){
    newName.toLowerCase();
    newName.trim();
    if((song.getName())==(newName)){
        return"A song already exists with this name. Try another."; //Rather than return, it sends an error popup that says this.
    }
    else{
        song.setName(newName);
        return song;
    }
}
public editArtist(String newArtist, Song song){
    yada;
}

//deleteSong code pieces
