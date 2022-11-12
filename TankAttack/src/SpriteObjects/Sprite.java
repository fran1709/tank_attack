package SpriteObjects;
import Main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

//Clase abstracta con las características de un objeto en movimiento
public abstract class Sprite {
    public int x, y, velocidad;
    public BufferedImage up, down, right, left, vida;
    public String direccion;
    public Rectangle solidArea;
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean colisionOn = false;

    //Método actualizar
    public void update() {
    }
}
