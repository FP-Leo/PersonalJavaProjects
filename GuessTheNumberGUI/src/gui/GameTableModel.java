/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.GNGame;

/**
 *
 * @author User
 */
public class GameTableModel extends AbstractTableModel{

    List<GNGame> games;
    String[] colNames = {"Save Date", "First Player", "Second Player",
        "X Player", "O Player", "First Player's score", "Second Player's score", "Moves Made", "Game state","To Move"};
    
    @Override
    public int getRowCount() {
        return games.size();
    }

    @Override
    public int getColumnCount() {
        return 10;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        GNGame game = games.get(rowIndex);
        switch(columnIndex){
            case 0:
                return game.getSaveDate();
            case 1:
                return game.getPlayer_1();
            case 2:
                return game.getPlayer_2();
            case 3:
                return game.getX_player();
            case 4:
                return game.getY_player();
            case 5:
                return game.getPlayerOne_score();
            case 6:
                return game.getPlayerTwo_score();
            case 7:
                return game.getGameState();
            case 8:
                return game.getState();
            case 9:
                return game.getTo_move();
        }
        return null;
    }
    
    public void setGames(List<GNGame> games){
        this.games = games;
    }
    
    @Override
    public String getColumnName(int column) {
        return colNames[column];
    }
    
}
