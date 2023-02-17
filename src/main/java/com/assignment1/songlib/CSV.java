package com.assignment1.songlib;

import java.io.FileReader;
//import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;


public class CSV {
    public static void main(String[] args) throws IOException {
        String input;
        try{
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\alexm\\OneDrive\\Desktop\\CS213\\Songlib\\src\\main\\java\\com\\assignment1\\songlib\\data.csv"));
            while((input = reader.readLine()) != null){
                String[] data = input.split(",");
                System.out.println("Name: "+data[0]+",Artist: "+data[1]+",Album: "+data[2]+",Year: "+data[3]+" ");
            }
            reader.close();
        }
        catch(IOException a){
            a.printStackTrace();
        }
        /*FileWriter writer = new FileWriter("data.csv");
        String[] addItem = new String[4];
        addItem[0]="newSong";
        addItem[1]="newArtist";
        addItem[2]="newAlbum";
        addItem[3]="newYear";
        for(int i=0;i<addItem.length;i++){
            writer.append(String.join(",",addItem[i]));
            writer.append("\r\n");
        }
        writer.flush();
        writer.close();*/
    }
        
}
