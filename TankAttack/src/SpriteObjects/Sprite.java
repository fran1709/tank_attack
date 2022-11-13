package SpriteObjects;
import Main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

//Clase abstracta con las características de un objeto en movimiento
public abstract class Sprite {
    public BufferedImage imagen;
    public GamePanel gp;
    public int x, y, velocidad;
    public BufferedImage up, down, right, left, vida;
    public String direccion;
    public Rectangle solidArea = new Rectangle(0,0,48,48);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean colisionOn = false;

    public void draw(Graphics2D g2, GamePanel panel){
        g2.drawImage(imagen, x, y, panel.tileSize, panel.tileSize, null);
    }
    //Método actualizar
    public void update() {
    }
}
