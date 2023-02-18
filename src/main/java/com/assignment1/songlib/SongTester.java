package com.assignment1.songlib;

public class SongTester {
    public static void main(String[] args){
        Song song1 = new Song("name","artist","album","year");
        String songString = song1.toString();
        System.out.println(songString);
    }
    
}
