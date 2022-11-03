package com.objects;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class main_Window extends JFrame {
    private JButton play_button;
    private JButton exit_button;
    private JPanel main_JPanel;

    public main_Window(){
        this.setContentPane(main_JPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400,300);
        this.setVisible(true);
        this.setTitle("Tank Attack");
        this.setLocationRelativeTo(null);
        this.setIconImage(new ImageIcon("C:\\Users\\vecti\\eclipse-workspace\\Tank_Attack\\src\\media\\icon.png").getImage());

        play_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                game_Window playing = new game_Window();
                playing.setVisible(true);
            }
        });

        exit_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}
