package SpriteObjects;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;

public class Enemy extends Sprite{
    public GamePanel panel;
    public int accion_contador = 0;
    public Enemy(GamePanel pGp){
        this.panel = pGp;
        solidArea = new Rectangle();
        solidArea.x = 0;
        solidArea.y = 0;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 48;
        solidArea.height = 48;
        getImagenes();
        initTanque();
    }
    public void initTanque(){
        velocidad = 1;
        direccion = "UP";
    }

    public void set_acciones(){
        accion_contador++;
        if (accion_contador == 160) {
            Random rand = new Random();
            int i = rand.nextInt(50)+1;
            if (i <= 15){
                direccion = "UP";
            }
            if (i > 15){
                direccion = "DOWN";
            }
            if (i > 25 && i < 40){
                direccion = "LEFT";
            }
            if (i >40 && i < 50){
                direccion = "RIGHT";
            }
            accion_contador = 0;
        }
    }

    @Override
    public void update() {
        set_acciones();

        colisionOn = false;
        panel.ck.checkTile(this);
        panel.ck.CheckObject(this,false);
        int index = panel.ck.check_enemy(this);
        panel.ck.check_player(this);

        //Si no hay colisión se mueve el tanque enemy
        if (!colisionOn) {
            switch (direccion) {
                case "UP" -> y -= velocidad;
                case "DOWN" -> y += velocidad;
                case "LEFT" -> x -= velocidad;
                case "RIGHT" -> x += velocidad;
            }
        }
    }
    @Override
    public void draw(Graphics2D g2, GamePanel panel) {
        BufferedImage imagen = switch (direccion) {
            case "UP" -> up;
            case "DOWN" -> down;
            case "RIGHT" -> right;
            case "LEFT" -> left;
            default -> null;
        };
        g2.drawImage(imagen, x, y, panel.tileSize, panel.tileSize, null);
    }

    public void getImagenes(){
        try {
            up = ImageIO.read(new FileInputStream("src/Imagenes/Enemigo1_UP.png"));
            down = ImageIO.read(new FileInputStream("src/Imagenes/Enemigo1_DOWN.png"));
            right = ImageIO.read(new FileInputStream("src/Imagenes/Enemigo1_RIGHT.png"));
            left = ImageIO.read(new FileInputStream("src/Imagenes/Enemigo1_LEFT.png"));
            //vida = ImageIO.read(new FileInputStream("src/Imagenes/Vida.png"));
        } catch(IOException e){
            e.printStackTrace();

        }
    }
}
