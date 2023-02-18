package com.assignment1.songlib;


import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
//import java.util.*;
import java.io.BufferedReader;


public class CSV {
    public static void main(String[] args) throws IOException {
        String input;
        
        //ArrayList<Song> songList = new ArrayList<>();
        String name = "song1";
        String artist = "artist1";
        String album = "album1";
        String year = "year1";
        Song song1 = new Song(name,artist,album,year);

        FileWriter writer = new FileWriter("data.csv",true);
        writer.append("\r\n");
        String stringSong = song1.toString();
        writer.append(stringSong);
        //writer.append(String.join(",","NextLine"));
        //String[] addItem = new String[8];
        /*addItem[0]="newSong1";
        addItem[1]="newArtist1";
        addItem[2]="newAlbum1";
        addItem[3]="newYear1";
        addItem[4]="newSong2";
        addItem[5]="newArtist2";
        addItem[6]="newAlbum2";
        addItem[7]="newYear2";*/
        //writer.append("\r\n");
        //writer.append(String.join(",",addItem));
        /*for(int i=0;i<2;i++){
            writer.append("\r\n");
            writer.append(String.join(",",addItem));
            //writer.append("\r\n");
        }*/
        
        writer.flush();
        writer.close();
        try{
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\alexm\\OneDrive\\Desktop\\CS213\\Songlib\\src\\main\\java\\com\\assignment1\\songlib\\data.csv"));
            while((input = reader.readLine()) != null){
                String[] data = input.split(",");
                System.out.println(data[0]+","+data[1]+","+data[2]+","+data[3]);
                //System.out.println("Name: "+data[0]+",Artist: "+data[1]+",Album: "+data[2]+",Year: "+data[3]+" ");

            }
            reader.close();
        }
        catch(IOException a){
            a.printStackTrace();
        }
    }
        
}
