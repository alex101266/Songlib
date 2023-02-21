package com.assignment1.songlib;

import java.util.Comparator;

public class Song {                 //song object
    private String name,artist,album,year;  //song, artist, and album names as well as year
    
    //Year is a String because it becomes easier to work with; inputs will be strings so no need to convert.
    //Plus, if there is no year it is easier to type "..." instead of some random int to denote that there
    //is no year

    //set it up so if they leave album or year boxes blank it somehow returns null

    //sorting for Song objects for the Song object list
    public static final Comparator<Song> TITLE_COMPARATOR = new Comparator<Song>() {
        public int compare(Song s1, Song s2) {
            return s1.getName().toLowerCase().compareTo(s2.getName().toLowerCase());
        }
    };

    public Song(String name, String artist, String album, String year){
        this.name = name;
        this.artist = artist;
        //next two check if there was an album or year input, which are optional
        if(album == null){
            this.album = " ";   }
        else{
            this.album=album;   }
        if(year == null){
            this.year = " ";    }
        else{
                this.year=year; }   }

    //getter methods
    public String getName(){
        return this.name;}
    public String getArtist(){
        return this.artist;}
    public String getAlbum(){
        return this.album;}
    public String getYear(){
        return this.year;}

    //setter methods (for editing)
    public void setName(String name){
        String tempName = name.trim();
        this.name = tempName;   }
    public void setArtist(String artist){
        String tempArtist = artist.trim();
        this.artist = tempArtist;   }
    public void setAlbum(String album){
        String tempAlbum = album.trim();
        if(tempAlbum.length() == 0){
            this.album = " ";   }
        else{
            this.album = tempAlbum; }   }
    public void setYear(String year){
        String tempYear = year.trim();
        if(tempYear.length() == 0){
            this.year = " ";   }
        else{
            this.year = tempYear; }   }

    @Override
    public String toString()
    {
        return name + "|" + artist + "|" + album + "|" + year;
    }
}

