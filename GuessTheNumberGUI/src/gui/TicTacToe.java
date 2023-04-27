/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author User
 */
public class TicTacToe extends JPanel {
    
    private GridBagConstraints gc;
    //Label to display who's to move / who won
    private final JLabel gameStateLabel;
    //Labels to display scores of the two players
    private final JLabel scoreLabelOne;
    private final JLabel scoreLabelTwo;
    //3x3 Tic Tac Toe Board
    private JLabel[] labels_Array;
    //Resets Board
    private final JButton playAgainButton;
    
    //To get players' names from the user if it's a new game
    private JLabel nameLabel;
    private JTextField nameField;
    private JButton getNameButton;
    //1 means you got the first name, 2 means you got the second name and can proceed
    private int getNameButtonClicked;
    
    //To store the two names
    private String firstName;
    private String secondName;
    
    //To easily calculate the game after move 4
    private int[][] clicked;
    
    //To know who's turn is to play 1-x_player, 2-y_player
    private int turn;
    //To know which player is the X player which one is the O player
    private String x_player;
    private String y_player;
    
    //To keep track of the scores
    private int playerOneWins;
    private int playerTwoWins;
    
    //Game's state, 0-continues, 1-is over
    private int state;
    //To know if the board is filled or not
    private int numOfMoves;

    
    private final ImageIcon x;
    private final ImageIcon o;
    //Idea to improve code
    //private MouseAdapter boardGridMA;

//Constructors
    public TicTacToe(){
        //Setting layout
        setLayout(new GridBagLayout());
        gc = new GridBagConstraints();
        
        //Creating the board
        labels_Array = new JLabel[9];
        clicked = new int[3][3];
        
        //Scaling X image
        Image dimg;
        BufferedImage img= null;
        try {
            img = ImageIO.read(new File("G:\\Other computers\\My Laptop\\Java Swing\\GuessTheNumberGUI\\images\\X.jpg"));
        } catch (IOException e) {
            System.out.println("Error, Couldn't find image");
            //System.exit(1);
        }
        dimg = img.getScaledInstance(150, 150,Image.SCALE_SMOOTH);
        x = new ImageIcon(dimg);
        //Scaling O image
        try {
        img = ImageIO.read(new File("G:\\Other computers\\My Laptop\\Java Swing\\GuessTheNumberGUI\\images\\O.jpg"));
        } catch (IOException e) {
        
        }
        dimg = img.getScaledInstance(150,150,Image.SCALE_SMOOTH);
        o = new ImageIcon(dimg);
        
        //Initializing and adding gameStateLabel and Scores to the Panel;     
        scoreLabelOne = new JLabel();
        scoreLabelTwo = new JLabel();
        gameStateLabel = new JLabel();
        gameStateLabel.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, Color.CYAN));
        refactScoreLabels();
        gc.insets = new Insets(0,0, 5,0);
        addComponent(gameStateLabel, 0,0);
        gc.insets = new Insets(0,0, 0,0);
        addComponent(scoreLabelOne, 0, 4);
        addComponent(scoreLabelTwo, 2, 4);
        
        //Setting x_player to play
        turn = 1;
        //Initializes the Tic Tac Toe board
        initializeTTT();
        //Hide board until you get both players' names
        displayBoard(false);
        //Get the names
        getNames();
        
        //Initialize playAgainButton, add it to the panel and hide it
        playAgainButton = new JButton("Play Again");
        playAgainButton.addActionListener((ActionEvent e) -> {
            //If yes reset board, keep names and scores and change X/O players
                resetGame(0);
        });
        addComponent(playAgainButton, 1, 5);
        playAgainButton.setVisible(false);    
    } 
//Private Methods
    private void initializeTTT(){
        int ind = 0;
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                //Board is made of labels, each label is firsly set to blank
                labels_Array[ind] = new JLabel();
                labels_Array[ind].setBackground(Color.white);
                labels_Array[ind].setOpaque(true);
                labels_Array[ind].setBorder(BorderFactory.createLineBorder(Color.black));
                
                //To pass info to the MouseListener
                final JLabel jL = labels_Array[ind];
                final int x_index = i;
                final int y_index = j;
                
                //Set Dimensions of the current Board Grid
                labels_Array[ind].setSize(new Dimension(150, 150));
                labels_Array[ind].setPreferredSize(new Dimension(150, 150));
                labels_Array[ind].setMinimumSize(new Dimension(150, 150));
                labels_Array[ind].setMaximumSize(new Dimension(200, 200));
                //Add it to the panel
                addComponent(labels_Array[ind], j, i+1);
                
                labels_Array[ind].addMouseListener(new MouseAdapter(){
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        //Accepts only mouse right click
                        if(e.getButton() != MouseEvent.BUTTON1)
                            return;
                        //Checks to see if the game is already over or if the current Board Grid is already clicked/set, if so do nothing
                        if(state != 0 || clicked[x_index][y_index] != 0){
                            return;
                        }
                        //Sets the current Board Grid to clicked 
                        clicked[x_index][y_index] = turn;
                        //Valid move
                        numOfMoves++;
                        
                        if(turn == 1){    
                            //X_player to play, sets current Board Grid icon to X
                            jL.setIcon(x);
                            gameStateLabel.setText(y_player + " to play");
                            turn = 2;
                        }else{
                            //Y_player to play, sets current Board Grid icon to O
                            jL.setIcon(o);
                            gameStateLabel.setText(x_player + " to play");
                            turn = 1;
                        }
                        //Game can only be finished after move 4
                        if(numOfMoves > 4){
                            //Checks if the game is finished
                            checkGame();
                        }
                    }
                });
                //Design the next Board Grid
                ind++;
            }
        }
    }
    //Adds a Component to the xth col and yth row of a GridBagLayout
    private void addComponent(Component tobeAdded, int x, int y){
        gc.gridx = x;
        gc.gridy = y;
        add(tobeAdded, gc);
    }
    //Function to get the Players' name
    private void getNames(){
        //To fill the entire row
        gc.weightx = 1;
        gc.fill = GridBagConstraints.HORIZONTAL;
        //Creating and adding label and getNameButton
        nameLabel = new JLabel("<html><u>First player's</u> name: </html>", SwingConstants.CENTER);
        addComponent(nameLabel, 0, 0);
        
        getNameButton = new JButton("Submit");

        getNameButton.addActionListener((ActionEvent e) -> {
            String text = nameField.getText();
            //If something is typed in the field it counts it as a name,it gets 2 names
            if(!text.equals("")){
                if(getNameButtonClicked == 0){
                    getNameButtonClicked++;
                    //Store the first name and set text for the second one
                    firstName = text;
                    nameLabel.setText("<html><u>Second player's</u> name: </html>");
                    nameField.setText("");
                }else{
                    //Store the second name, decide who goes first, and start the game
                    secondName = text;
                    displayNameMenu(false);
                    displayBoard(true);
                    x_player = firstName;
                    y_player = secondName;
                    refactScoreLabels();
                    gameStateLabel.setText(x_player + " to play");
                }
            }
        });
        addComponent(getNameButton, 1,1);
        
        //Resize the TextField to match the button's dimension and add it
        Dimension dim = getNameButton.getSize();
        
        nameField = new JTextField();
        nameField.setSize(dim);
        
        dim = getNameButton.getPreferredSize();
        nameField.setPreferredSize(dim);
        addComponent(nameField, 1, 0);
        
        //Reset the gc properties
        gc.weightx = 0;
        gc.fill = GridBagConstraints.NONE;
    }
    //Sets the display of Name Menu to true/false
    private void displayNameMenu(boolean type){
        nameLabel.setVisible(type);
        nameField.setVisible(type);
        getNameButton.setVisible(type);
    }
    //Checks the game current state, used by mouseAdapters of each Board Grid
    private void checkGame(){
        //Calculate the current state, 0 - unfinished/tie, 1 - x player wins, 2 - o player wins
        int gameState = calculateGame();
        switch(gameState){
            case 0:
                //Board not filled yet and no conclusive result
                if(numOfMoves != 9)
                    return;
                //Board filled and no conclusive result
                gameStateLabel.setText("Tie!");
                break;
            case 1:
                //X Player won
                if(x_player.equals(firstName)){
                    playerOneWins++;
                }else{
                    playerTwoWins++;
                }                                        
                gameStateLabel.setText("Player " + x_player + " wins!");
                break;
            case 2:
                //O Player won
                if(y_player.equals(firstName)){
                    playerOneWins++;
                }else{
                    playerTwoWins++;
                }
                gameStateLabel.setText("Player " + y_player + " wins!");
                break;
        }
        //Game finished
        state = 1;
        //Reposition gameStateLabel
        remove(gameStateLabel);
        gc.insets = new Insets(5,0, 5,0);
        addComponent(gameStateLabel, 1, 4);
        gc.insets = new Insets(0,0, 0,0);
        refactScoreLabels();
        //Play again?
        playAgainButton.setVisible(true);
    }
    //Calculate the current state, used by checkGame function
    private int calculateGame(){
        for(int i = 0; i < 3; i++){
            //Checking Rows, if a row has all three elements the same game finishes,1 represents X, 2 represents O
            if(clicked[i][0] != 0 && clicked[i][0] == clicked[i][1] && clicked[i][0] == clicked[i][2]){
                return clicked[i][0];
            }
            //Checking Cols, if a col has all three elements the same game finishes, 1 represents X, 2 represents O
            if(clicked[0][i] != 0 && clicked[0][i] == clicked[1][i] && clicked[0][i] == clicked[2][i]){
                return clicked[0][i];
            }
        }
        
        if(clicked[1][1] != 0){
            //Checking first diag, if all elements are the same game finishes, 1 represents X, 2 represents O
            if(clicked[0][0] == clicked[1][1] && clicked[0][0] == clicked[2][2])
                return clicked[0][0];
            //Checking second diag, if all elements are the same game finishes, 1 represents X, 2 represents O
            if(clicked[0][2] == clicked[1][1] && clicked[0][2] == clicked[2][0])
                return clicked[0][2];
        }
        //If numOfMoves == 9 it's a tie else game continues
        return 0;
    }
    //Refresh the score labels to current names and scores
    private void refactScoreLabels(){
        scoreLabelOne.setText("<html>\"" + firstName+ "\"'s Score: " + playerOneWins + "</html>");
        scoreLabelTwo.setText("<html>\"" + secondName+ "\"'s Score: " + playerTwoWins + "</html>");
    }
    //Sets the display of the game to true/false
    private void displayBoard(boolean type){
        for(int i = 0; i < 9; i++){
            labels_Array[i].setVisible(type);
        }
        scoreLabelOne.setVisible(type);
        scoreLabelTwo.setVisible(type);
        gameStateLabel.setVisible(type);
    }
//Public Methods
    public void resetGame(int type){
        remove(gameStateLabel);
        gc.insets = new Insets(0,0, 5,0);
        addComponent(gameStateLabel, 0,0);
        gc.insets = new Insets(0,0, 0,0);
        
        int ind = 0;
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                clicked[i][j] = 0;
                labels_Array[ind++].setIcon(null);                
            }
        }
        
        playAgainButton.setVisible(false);
        state = 0;
        numOfMoves = 0;
        turn = 1;
        if(type == 0){
            if(x_player.equals(firstName)){
                x_player = secondName;
                y_player = firstName;
            }else{
                x_player = firstName;
                y_player = secondName;
            }
        }else{
            playerOneWins = 0;
            playerTwoWins = 0;
            displayBoard(false);
            displayNameMenu(true);
            refactScoreLabels();
        }
        gameStateLabel.setText(x_player +  " to move");
    }
    //Return a new GameForm for saving to the database
    public GameForm getSaveState(){
        //Save current players, their scores, who's to move, and the date when the save is being made
        GameForm currentGame = new GameForm();
        currentGame.setPlayer_1(firstName);
        currentGame.setPlayer_2(secondName);
        
        currentGame.setPlayerOne_score(playerOneWins);
        currentGame.setPlayerTwo_score(playerTwoWins);
        
        currentGame.setGameState(clicked);
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now();
        currentGame.setSaveDate(dtf.format(now));
        
        currentGame.setTo_move(turn);
        currentGame.setX_player(x_player);
        currentGame.setY_player(y_player);
        
        return currentGame;
    }
    //Loads a game from the database
    public void setGame(GameForm currentGame){
        firstName = currentGame.getPlayer_1();
        secondName = currentGame.getPlayer_2();
        
        playerOneWins = currentGame.getPlayerOne_score();
        playerTwoWins = currentGame.getPlayerTwo_score();
        refactScoreLabels();
        
        clicked = currentGame.getGameState();
        int ind = 0;
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                switch (clicked[i][j]) {
                    case 1:
                        labels_Array[ind].setIcon(x);
                        break;
                    case 2:
                        labels_Array[ind].setIcon(o);
                        break;
                    default:
                        labels_Array[ind].setIcon(null);
                        break;
                }
                ind++;
            }
        }
        
        x_player = currentGame.getX_player();
        y_player = currentGame.getY_player();
        
        turn = currentGame.getTo_move();
        state = currentGame.getState();
        gc.insets = new Insets(0,0, 5,0);        
        if(state == 1){
            playAgainButton.setVisible(true);
            addComponent(gameStateLabel, 1,4);
        }else{
            playAgainButton.setVisible(false);
            addComponent(gameStateLabel, 0,0);
        }
        gc.insets = new Insets(0,0, 0,0);
        
        displayNameMenu(false);
        displayBoard(true);
        refactScoreLabels();
        System.out.println("TicTest");
    }

}
