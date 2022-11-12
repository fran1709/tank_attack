package SpriteObjects;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Sprite {
    public int x, y, velocidad;
    public BufferedImage up, down, right, left, vida;
    public String direccion;
    public Rectangle solidArea;
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean colisionOn = false;

    public void update() {
    }
}
