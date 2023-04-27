/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author User
 */
public class Database {
    
    private final ArrayList<GNGame> gameList;
    
    public Database(){
        gameList = new ArrayList<>();
    }
    
    public void addGame(GNGame g){
        gameList.add(g);
    }
    
    public List<GNGame> getGames(){
        return gameList;
    }
    
    public GNGame returnGame(){
        return gameList.get(0);
    }
    
    public void saveToFile(File f) throws FileNotFoundException, IOException{
        FileOutputStream fos = new FileOutputStream(f);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        GNGame[] games = gameList.toArray(new GNGame[gameList.size()]); 
        oos.writeObject(games);
        oos.close();
    }
    
    public void readFromFile(File f) throws ClassNotFoundException, FileNotFoundException, IOException{
        FileInputStream fis = new FileInputStream(f);
        ObjectInputStream ois = new ObjectInputStream(fis);
        GNGame[] games = (GNGame[])ois.readObject();
        gameList.addAll(Arrays.asList(games));
        ois.close();   
    } 
}
