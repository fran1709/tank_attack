package com.objects;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameWindow extends JFrame implements KeyListener {
    CampoBatalla campo;
    public GameWindow() {
        campo = new CampoBatalla();
        add(campo);
        addKeyListener(this);
        setTitle("Tank Attack");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(1080, 720);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    //Metodos de la interface KeyListener
    @Override
    public void keyTyped(KeyEvent e) { /*Nada que hacer aqui*/ }

    @Override
    public void keyPressed(KeyEvent e) {
        int id = e.getKeyCode();
        if (id == KeyEvent.VK_RIGHT) {
            campo.moverDer();
        }

        if (id == KeyEvent.VK_LEFT) {
            campo.moverIzq();
        }
    }
    @Override
    public void keyReleased(KeyEvent e) { /*Nada que hacer aqui*/ }
}
