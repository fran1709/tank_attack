package com.objects;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class game_Window extends JFrame{
    private JButton backButton;
    private JPanel game_JPanel;

    public game_Window() {
        this.setContentPane(game_JPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400,300);
        this.setVisible(true);
        this.setTitle("Tank Attack");
        this.setLocationRelativeTo(null);
        this.setIconImage(new ImageIcon("C:\\Users\\vecti\\eclipse-workspace\\Tank_Attack\\src\\media\\icon.png").getImage());


        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                main_Window main_Window = new main_Window();
                main_Window.setVisible(true);
            }
        });
    }
}
