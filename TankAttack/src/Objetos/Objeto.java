package Objetos;

import Main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

//Clase abstracta con las características generales de un objeto
public abstract class Objeto { //Constructor de la clase
    public BufferedImage imagen;
    public String nombre;
    public boolean colision = false;
    public int x, y;
    public Rectangle solidArea = new Rectangle(0,0,48,48);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;

    //Método dibujar
    public void draw(Graphics2D g2, GamePanel panel){
        g2.drawImage(imagen, x, y, panel.tileSize, panel.tileSize, null);
    }
}
