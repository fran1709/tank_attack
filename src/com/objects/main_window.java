package com.objects;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class main_window extends JFrame {
    private JLabel tittle = new JLabel();
    private JButton play_button = new JButton();
    private JButton exit_button = new JButton();
    private fondo_main fondo = new fondo_main();

    public main_window(){
        play_button();
        exit_button();
        tittle();
        instance();

        play_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                GameWindow start_game = new GameWindow();
                start_game.setVisible(true);
            }
        });

        exit_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
    private void instance(){
        this.add(fondo);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(720,480);
        this.setVisible(true);
        this.setTitle("Tank Attack");
        this.setLocationRelativeTo(null);
        this.setIconImage(new ImageIcon("C:\\Users\\vecti\\eclipse-workspace\\Tank_Attack\\src\\media\\icon.png").getImage());

    }
    private void play_button(){
        play_button.setSize(80,35);
        play_button.setLocation(310,150);
        play_button.setText("PLAY");
        add(play_button);
    }
    private void exit_button(){
        exit_button.setBounds(310,220,80,35);
        exit_button.setText("EXIT");
        //exit_button.setVerticalAlignment(SwingConstants.CENTER);
        add(exit_button);
    }
    private void tittle(){
        tittle.setBounds(280,20,200,70);
        tittle.setText("--- TANK ATTACK GAME ---");
        tittle.setForeground(Color.WHITE);
        tittle.setOpaque(false);
        add(tittle);
    }
}

