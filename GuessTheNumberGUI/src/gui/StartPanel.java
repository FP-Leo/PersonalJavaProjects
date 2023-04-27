/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;


/**
 *
 * @author User
 */
public class StartPanel extends JPanel implements ActionListener{
    private final JButton optionOne;
    private final JButton optionTwo;
    private final JButton quitOption;
    private final JButton testButton;
    private final JPanel menuPanel;
    
    private getMenuOption menuEmmiter;
    
    public StartPanel(){
        setLayout(new GridBagLayout());
        
        menuPanel = new JPanel();
        menuPanel.setLayout(new GridBagLayout());
        menuPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        
        int x_index = 0;
        int y_index = 0;
        //First button
        optionOne = createMenuEntry("1.Guess the Number game");
        addToMenuPanel(optionOne, x_index,y_index++);
        
        //Second button
        optionTwo = createMenuEntry("2.Tic Tac Toe game");
        addToMenuPanel(optionTwo, x_index, y_index++);
        
        //Test button
        testButton = createMenuEntry("3.Loaded Tic Tac Toe");
        addToMenuPanel(testButton, x_index, y_index++);
        
        //Last Button
        quitOption = createMenuEntry("3.Quit");
        addToMenuPanel(quitOption, x_index,y_index++);
        
        //Add the Menu Panel
        add(menuPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton test = (JButton)e.getSource();
        if(menuEmmiter != null){
            if(test == quitOption){
                menuEmmiter.emmitOption(0);
            }else if(test == optionOne){
                menuEmmiter.emmitOption(1);
            }else if(test == optionTwo){
                menuEmmiter.emmitOption(2);
            }else if(test == testButton){
                menuEmmiter.emmitOption(3);
            }
        }
    }
    
    public void setEmmiter(getMenuOption e){
        menuEmmiter = e;
    }
    
    private void addToMenuPanel(Component tobeadded, int x, int y){
        GridBagConstraints gc = new GridBagConstraints();
        
        gc.fill = GridBagConstraints.NONE;
        
        gc.gridx = x;
        gc.gridy = y;
        
        menuPanel.add(tobeadded, gc);
    }

    private JButton createMenuEntry(String text){
        JButton tobereturned = new JButton(text);
        
        tobereturned.setBorderPainted(false);
        tobereturned.setContentAreaFilled(false);
        tobereturned.addActionListener(this);
        
        return tobereturned;
    }
    
}
