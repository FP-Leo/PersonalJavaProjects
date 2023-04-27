/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import controller.Controller;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;


/**
 *
 * @author User
 */

public class MainFrame extends JFrame implements ActionListener {
    
    private int currentPanel;
    private GuessPanel guessPanel;
    private final StartPanel startPanel;
    private TicTacToe tttPanel;
    private final JButton back;
    private final JLabel welcomeLabel;
    
    private final controller.Controller controller;

    JLabel emptySpace;
            
    public MainFrame(){
        pack();
        //Set up controller
        controller = new Controller();
        
        //Set up the layout
        setLayout(new GridBagLayout());
        GridBagConstraints cons = new GridBagConstraints();
        cons.fill = GridBagConstraints.BOTH; 
        
        //Create Back Button and Add Back Button Top Left
        back = new JButton("Back");
        back.addActionListener(this);
        cons.gridx = 0;
        cons.gridy = 0;
        add(back, cons);
        back.setVisible(false);
        
        //Add empty space Top Right for allignment
        cons.gridx = 2; 
        emptySpace = new JLabel();
        Dimension dim = back.getMinimumSize();
        emptySpace.setMinimumSize(dim);
        dim = back.getPreferredSize();
        emptySpace.setPreferredSize(dim);
        dim = back.getSize();
        emptySpace.setSize(dim);
        
        add(emptySpace, cons);
        emptySpace.setVisible(false);
        
        //Welcome Label
        String text = "<html><p style=\"text-align: center;\">Welcome!<br/>Select an option: </p></html>";
        welcomeLabel = new JLabel(text, SwingConstants.CENTER);

        cons.gridx = 1;
        cons.gridy = 1;
        cons.insets = new Insets(0,0,5,0);
        add(welcomeLabel, cons);
       
        //Start and Add Panels
        cons.gridx = 1;
        cons.gridy = 2;
        
            //Start menu panel
        startPanel = new StartPanel();
        startPanel.setEmmiter((int option) -> {
            switch(option){
                case 0:
                    int val = JOptionPane.showOptionDialog(null, "Are you sure you want to quit?", "Quit", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null,null,null);
                    if(val == JOptionPane.OK_OPTION)
                        System.exit(0);
                    break;
                case 1:
                    setTitle("Guess The Number");
                    currentPanel = 1;
                    guessPanel.setVisible(true);
                    break;
                case 2:
                    setTitle("Tic Tac Toe");
                    currentPanel = 2;
                    tttPanel.setVisible(true);
                    //System.out.println("Width: " + tttPanel.getWidth());
                    break;
                case 3:
                    System.out.println("test");
                    File f = new File("C:\\Users\\User\\Desktop\\Java Swing\\GuessTheNumberGUI\\testDb.xlsx");
                    try {
                        controller.readFromFile(f);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    tttPanel.setGame(controller.readGame());
                    tttPanel.setVisible(true);
                    System.out.println("test2");
                    break;
            }
            disableStartScreen();
        });
        add(startPanel, cons);
        
            //Guess the number Panel
        guessPanel = new GuessPanel();
        cons.weightx = 1;
        cons.weighty = 1;
        add(guessPanel, cons);
        guessPanel.setVisible(false);
        
            //Tic Tac Toe Panel 
        tttPanel = new TicTacToe();
        add(tttPanel, cons);
        tttPanel.setVisible(false);
        
        //Set up the Main Frame
        setTitle("Minigames - By Leo");
        setSize(600, 600);
        setMinimumSize(new Dimension(600, 600));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    private void disableStartScreen(){
        welcomeLabel.setVisible(false);
        startPanel.setVisible(false);
        
        back.setVisible(true);
        emptySpace.setVisible(true);
    }

    /**
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton)e.getSource();
        if(source == back){
            switch(currentPanel){
                case 1:
                    if(guessPanel.getState() == 1){
                        int val = JOptionPane.showOptionDialog(null, "Save current game?", "Save", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null,null,null);
                        if(val == JOptionPane.NO_OPTION){
                            guessPanel.resetPanel();
                        }else if(val == JOptionPane.CANCEL_OPTION)
                            return;
                    }else {
                        guessPanel.resetPanel();
                    }
                    guessPanel.setVisible(false);
                    break;
                case 2:
                    int val = JOptionPane.showOptionDialog(null, "Save current game?", "Save", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null,null,null);
                    if(val == JOptionPane.CANCEL_OPTION)
                        return;
                    if(val == JOptionPane.OK_OPTION){
                        GameForm currentGame = tttPanel.getSaveState();
                        controller.addGame(currentGame);
                        File test = new File("C:\\Users\\User\\Desktop\\Java Swing\\GuessTheNumberGUI\\testDb.xlsx");
                        if(!test.exists()){
                            try {
                                test.createNewFile();
                            } catch (IOException ex) {
                                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        
                        //GameTableModel tableModel = new GameTableModel();
                        //tableModel.setGames(controller.getDb());
                        //JTable testTable = new JTable();
                        //testTable.setModel(tableModel);
                        try {
                            controller.saveToFile(test);
                        } catch (IOException ex) {
                            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    tttPanel.setVisible(false);
                    tttPanel.resetGame(1);
                    break;
            }
            setTitle("Minigames - By Leo");
            welcomeLabel.setVisible(true);
            startPanel.setVisible(true);
            
            back.setVisible(false);
            emptySpace.setVisible(false);
        }
    }

}
