/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import java.util.EventObject;

/**
 *
 * @author User
 */
public class GameForm {
    
    private String player_1;
    private String player_2;
    
    private int playerOne_score;
    private int playerTwo_score;
    private int[][] gameState;
    
    private String x_player;
    private String y_player;
    private int to_move;
    private int state;
    
    private String saveDate;

    public GameForm() {
    }

    public GameForm(String player_1, String player_2, int playerOne_score, 
                  int playerTwo_score, int[][] gameState, int state, 
                  String x_player, String y_player, int to_move, String saveDate){
        this.saveDate = saveDate;
        this.player_1 = player_1;
        this.player_2 = player_2;
        this.x_player = x_player;
        this.y_player = y_player;
        
        this.playerOne_score = playerOne_score;
        this.playerTwo_score = playerTwo_score;
        
        this.state = state;
        this.gameState = gameState;
        
        this.to_move = to_move;
    }
    
    

    public String getPlayer_1() {
        return player_1;
    }

    public void setPlayer_1(String player_1) {
        this.player_1 = player_1;
    }

    public String getPlayer_2() {
        return player_2;
    }

    public void setPlayer_2(String player_2) {
        this.player_2 = player_2;
    }

    public int getPlayerOne_score() {
        return playerOne_score;
    }

    public void setPlayerOne_score(int playerOne_score) {
        this.playerOne_score = playerOne_score;
    }

    public int getPlayerTwo_score() {
        return playerTwo_score;
    }

    public void setPlayerTwo_score(int playerTwo_score) {
        this.playerTwo_score = playerTwo_score;
    }

    public int[][] getGameState() {
        return gameState;
    }

    public void setGameState(int[][] gameState) {
        this.gameState = gameState;
    }

    public int getTo_move() {
        return to_move;
    }

    public void setTo_move(int to_move) {
        this.to_move = to_move;
    }

    public String getSaveDate() {
        return saveDate;
    }

    public void setSaveDate(String saveDate) {
        this.saveDate = saveDate;
    }

    public String getX_player() {
        return x_player;
    }

    public void setX_player(String x_player) {
        this.x_player = x_player;
    }

    public String getY_player() {
        return y_player;
    }

    public void setY_player(String y_player) {
        this.y_player = y_player;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
    
}
