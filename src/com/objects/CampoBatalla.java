package com.objects;

import com.objects.game.Tanque;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class CampoBatalla extends JPanel {
    private Vector<Tanque> enemigos;
    private Tanque tanque;
    private Image fondo;

    public CampoBatalla() {
        fondo = new ImageIcon("C:\\Users\\vecti\\OneDrive - Estudiantes ITCR\\TEC\\2022\\Semestre II\\Lenguajes de Programacion\\Segundo Proyecto Programado\\tank_attack\\src\\media\\fondo2.png").
                getImage();
        crearEnemigos();
        crearTanque();
    }
    private void crearEnemigos() {
        enemigos = new Vector<Tanque>();
        for (int i = 0; i < 7; i++) {
            Tanque t = new Tanque();
            t.rotar(); //Giramos la figura original 180 grados
            if (i == 0)
                t.translate(5, 5);
            else {
                int xAnterior = (int) enemigos.get(i - 1).getBounds2D().getX();
                int anchoTanque = (int) t.getBounds2D().getWidth();
                t.translate(xAnterior + anchoTanque + 35 , 5);
            }
            enemigos.add(t);
        }
    }
    private void crearTanque() {
        tanque = new Tanque();
        tanque.translate(5, 450 - (int)(tanque.getBounds2D().getHeight()));
    }
    public int getTanqueX() {
        return (int) tanque.getBounds2D().getCenterX() - 15;
    }
    public int getTanqueY() {
        return (int) tanque.getBounds2D().getCenterY() - 65;
    }
    public void moverIzq() {
        int posX = (int) tanque.getBounds2D().getX();
        if (posX > 5) //Limite son 5 px desde el borde izquierdo
            tanque.translate(-10, 0);
        //Solo repintamos el cuadrante por donde se desplaza el tanque
        Rectangle bounds = tanque.getBounds();
        repaint(0, bounds.y, 600, bounds.height);
    }
    public void moverDer() {
        int ancho = (int) tanque.getBounds2D().getWidth();
        int xLimite = 575 - ancho;
        int posX = (int) tanque.getBounds2D().getX();
        if (posX < xLimite)
            tanque.translate(10, 0);
        //Solo repintamos el cuadrante por donde se desplaza el tanque
        Rectangle bounds = tanque.getBounds();
        repaint(0, bounds.y, 600, bounds.height);
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(fondo, 0, 0, getWidth(), getHeight(),
                this);
        setOpaque(false);
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(new Color(175, 200, 39));
        for (Tanque enemigo: enemigos)
            g2.fillPolygon(enemigo);
        g2.setColor(new Color(72, 140, 54));
        g2.fillPolygon(tanque);
    }
}
