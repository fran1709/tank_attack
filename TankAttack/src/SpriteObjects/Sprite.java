package SpriteObjects;
import Main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

//Clase abstracta con las características de un objeto en movimiento
public abstract class Sprite {
    public Bala bala;
    public boolean alive;
    public BufferedImage imagen;
    public GamePanel gpSuper;
    public int x, y, velocidad;
    public BufferedImage up, down, right, left, vida;
    public String direccion;
    public Rectangle solidArea = new Rectangle(0,0,48,48);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean colisionOn = false;

    public Sprite(GamePanel pg){
        this.gpSuper = pg;
    }

    public void draw(Graphics2D g2, GamePanel panel){
        g2.drawImage(imagen, x, y, panel.tileSize, panel.tileSize, null);
    }
    //Método actualizar
    public void update() {
    }
    public String[] posicionar_player(String pX, String pY){
        return new String[0];
    }

    public void draw(Graphics2D g2) {
        BufferedImage imagen = null;
        switch (direccion) {
            case "UP":
                imagen = up;
                break;
            case "DOWN":
                imagen = down;
                break;
            case "RIGHT":
                imagen = right;
                break;
            case "LEFT":
                imagen = left;
                break;
        }
        g2.drawImage(imagen, x, y, null);
    }
}
