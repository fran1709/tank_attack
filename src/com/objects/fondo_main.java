package com.objects;

import com.objects.game.Tanque;

import javax.swing.*;
import java.awt.*;

public class fondo_main extends JPanel {
    private Image fondo;
    public fondo_main(){
        fondo = new ImageIcon("C:\\Users\\vecti\\OneDrive - Estudiantes ITCR\\TEC\\2022\\Semestre II\\Lenguajes de Programacion\\Segundo Proyecto Programado\\tank_attack\\src\\media\\fondo2.jpg").
                getImage();
    }
    @Override
    public void paint(Graphics g) {
        g.drawImage(fondo, 0, 0, getWidth(), getHeight(),
                this);
        setOpaque(false);
        super.paint(g);
    }
}
