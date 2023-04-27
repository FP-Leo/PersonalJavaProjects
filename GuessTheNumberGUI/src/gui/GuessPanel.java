/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author User
 */
public class GuessPanel extends JPanel{
    
    private JLabel explanation;
    private JLabel numberLabel;
    private JLabel guessedLabel;
    private JLabel remainingGuesses;
    private JTextField numberField;
    private JButton guessButton;
    private JButton startButton;
    
    private int guesses = 7;
    private int currentRanNum;
    Random ran;
    String guessedNumberStr;
    
    private int state;
    
    public GuessPanel(){
        setLayout(new GridBagLayout());
        ran = new Random();
        
        String expText = "<html><p style=\"text-align: center;\">Welcome to \"Guess the Number\" game!"
                        +" Rules are pretty simple.<br/>Everyday a random number from 0-100(both included) will be generated.<br/>"
                        +"Your goal here is to find which number this is in just 7 tries.</p></html>";
        explanation = new JLabel(expText,SwingConstants.CENTER);
        guessedLabel = new JLabel("", SwingConstants.CENTER);
        guessedLabel.setFont(new Font("Serif", Font.PLAIN, 12));
        
        remainingGuesses = new JLabel("Remaining Guesses: " + guesses, SwingConstants.CENTER);
        
        startButton = new JButton("Start");
        startButton.addActionListener((ActionEvent e) -> {
            state = 1;
            
            currentRanNum = ran.nextInt(101);
            
            startButton.setVisible(false);
            explanation.setVisible(false);
            
            numberLabel.setVisible(true);
            
            numberField.setVisible(true);
            numberField.setEditable(true);
            numberField.setText("");
            
            guessButton.setVisible(true);
            
            guessedLabel.setVisible(true);
            guessedLabel.setBorder(null);
            guessedLabel.setText("");
            
            remainingGuesses.setVisible(true);
        });
        
        //Number Label/Field
        numberLabel = new JLabel("Guess the number", SwingConstants.CENTER);
        numberLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        numberLabel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.CYAN));
        numberField = new JTextField(10);
        numberField.setHorizontalAlignment(JTextField.CENTER);
        
        //Guess Button
        guessButton = new JButton("Guess");
        Dimension dim = numberField.getPreferredSize();
        guessButton.setPreferredSize(dim);
        guessButton.addActionListener((ActionEvent e) -> {
            guessedNumberStr = numberField.getText();
            if(guessedNumberStr.equals(""))
                return;
            int guessedNumber = 0;
            try{
                guessedNumber = Integer.parseInt(guessedNumberStr);
            }catch(final NumberFormatException badInput){
                JOptionPane.showConfirmDialog(null, "Please enter only integers!", "Error", JOptionPane.DEFAULT_OPTION);
                return;
            }
            
            try {
                Thread.sleep(200);
            } catch (InterruptedException ex) {
                Logger.getLogger(GuessPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if(guessedNumber < 0 || guessedNumber > 100){
                JOptionPane.showConfirmDialog(null, "Number can only be between 0-100(borders included).", "Error", JOptionPane.DEFAULT_OPTION);
                return;
            }
            
            if(currentRanNum == guessedNumber){
                //JOptionPane.showConfirmDialog(null, "You guessed it correctly!", "Your Guess", JOptionPane.DEFAULT_OPTION|JOptionPane.INFORMATION_MESSAGE);
                guessedLabel.setText("<html><p style=\"text-align: center;\"><strong>Correct!!</strong></p></html>");
                guessedLabel.setForeground(Color.green);
                guessedLabel.setBorder(BorderFactory.createMatteBorder(1, 3, 1, 1, Color.green));
                remainingGuesses.setVisible(false);
                guessButton.setVisible(false);
                numberField.setEditable(false);
                
                state = 0;
            }
            else{
                guesses--;
                remainingGuesses.setText("Remaining Guesses: " + guesses);
                guessedLabel.setForeground(Color.red);
                guessedLabel.setBorder(BorderFactory.createMatteBorder(1, 3, 1, 1, Color.red));
                if(guesses == 0){
                    numberField.setEditable(false);
                    remainingGuesses.setVisible(false);
                    guessButton.setVisible(false);
                    guessedLabel.setText("<html><p style=\"text-align: center;\"><strong>Game Over</strong></p></html>");
                    state = 0;
                }else if(currentRanNum > guessedNumber)
                    guessedLabel.setText("<html><p style=\"text-align: center;\">Incorrect! Higher!</p></html>");
                else
                    guessedLabel.setText("<html><p style=\"text-align: center;\">Incorrect! Lower!</p></html>");
            }
        });
        
        numberLabel.setVisible(false);
        numberField.setVisible(false);
        guessButton.setVisible(false);
        guessedLabel.setVisible(false);
        remainingGuesses.setVisible(false);
        
        addToLayout(explanation, 0,0,0,0,0,0);
        addToLayout(startButton, 0,1,6,0,0,0);
        addToLayout(remainingGuesses, 1,0,0,0,0,0);
        addToLayout(numberLabel, 0,1,0,0,0,5);
        addToLayout(numberField, 1,1,0,0,0,0);
        addToLayout(guessedLabel, 1,2,2,0,0,1);
        addToLayout(guessButton, 1,3,5,0,0,1);
    }
    
    /**
     *
     */
    
    private void addToLayout(Component comp, int x, int y, int ins_top, int ins_left, int ins_bottom, int ins_right){
        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.weightx = 1;
        
        gc.gridx = x;
        gc.gridy = y;
        if(ins_top != 0 || ins_left != 0 || ins_bottom != 0 || ins_right != 0)
            gc.insets = new Insets(ins_top, ins_left, ins_bottom, ins_right);
        
        add(comp, gc);        
    }
    
    public void resetPanel(){
        guesses = 7;
        state = 0;
        
        numberLabel.setVisible(false);
        numberField.setVisible(false);
        guessButton.setVisible(false);
        guessedLabel.setVisible(false);
        remainingGuesses.setVisible(false);
        
        remainingGuesses.setText("Remaining Guesses: " + guesses);
        
        explanation.setVisible(true);
        startButton.setVisible(true);
    }

    public int getState() {
        return state;
    }
    
}
