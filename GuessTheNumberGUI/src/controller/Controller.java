/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import gui.GameForm;
import java.io.File;
import java.io.IOException;
import java.util.List;
import model.Database;
import model.GNGame;

/**
 *
 * @author User
 */
public class Controller {
    private Database db  = new Database();

    public void addGame(GameForm currentGame){
        String saveDate = currentGame.getSaveDate();
        String firstName = currentGame.getPlayer_1();
        String secondName = currentGame.getPlayer_2();
        String x_player = currentGame.getX_player();
        String y_player = currentGame.getY_player();
        
        int [][]gameState = currentGame.getGameState();
        int fPScore = currentGame.getPlayerOne_score();
        int sPScore = currentGame.getPlayerTwo_score();
        int state = currentGame.getState();
        int to_move = currentGame.getTo_move();
        
        GNGame game = new GNGame(firstName, secondName, fPScore, sPScore, gameState, state, x_player, y_player, to_move, saveDate);
        db.addGame(game);
    }

    public GameForm readGame(){
        GNGame currentGame = db.returnGame();
        
        String saveDate = currentGame.getSaveDate();
        String firstName = currentGame.getPlayer_1();
        String secondName = currentGame.getPlayer_2();
        String x_player = currentGame.getX_player();
        String y_player = currentGame.getY_player();
        
        int [][]gameState = currentGame.getGameState();
        int fPScore = currentGame.getPlayerOne_score();
        int sPScore = currentGame.getPlayerTwo_score();
        int state = currentGame.getState();
        int to_move = currentGame.getTo_move();
        
        GameForm test = new GameForm(firstName, secondName, fPScore, sPScore, gameState, state, x_player, y_player, to_move, saveDate);
        return test;
    }
    
    public List<GNGame> getDb() {
        return db.getGames();
    }

    public void setDb(Database db) {
        this.db = db;
    }
    
    public void saveToFile(File f) throws IOException{
        db.saveToFile(f);
    }
    
    public void readFromFile(File f) throws ClassNotFoundException, IOException{
        db.readFromFile(f);
    }
}
