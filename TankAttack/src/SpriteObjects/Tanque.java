package SpriteObjects;

import Main.GamePanel;
import Main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

public class Tanque extends Sprite {
    GamePanel panel;
    KeyHandler keyH;
    public int objetivo1 = 0;
    public int objetivo2 = 0;
    public int vidas;
    public Tanque(GamePanel p, KeyHandler kH){
        this.panel = p;
        this.keyH = kH;
        this.vidas = 3;
        solidArea = new Rectangle();
        solidArea.x = 0;
        solidArea.y = 0;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 48;
        solidArea.height = 48;
        initTanque();
        getImagenes();
    }

    public void initTanque(){
        x = 350;
        y = 350;
        velocidad = 4;
        direccion = "UP";
    }

    public void getImagenes(){
        try {
            up = ImageIO.read(new FileInputStream("src/Imagenes/Jugador_UP.png"));
            down = ImageIO.read(new FileInputStream("src/Imagenes/Jugador_DOWN.png"));
            right = ImageIO.read(new FileInputStream("src/Imagenes/Jugador_RIGHT.png"));
            left = ImageIO.read(new FileInputStream("src/Imagenes/Jugador_LEFT.png"));
            vida = ImageIO.read(new FileInputStream("src/Imagenes/Vida.png"));
        } catch(IOException e){
            e.printStackTrace();

        }
    }

    @Override
    public void update() {
        if (keyH.upPressed == true || keyH.downPressed == true
                || keyH.leftPressed == true || keyH.rightPressed == true) {
            if (keyH.upPressed == true) {
                direccion = "UP";
            } else if (keyH.downPressed == true) {
                direccion = "DOWN";
            } else if (keyH.leftPressed == true) {
                direccion = "LEFT";
            } else if (keyH.rightPressed == true) {
                direccion = "RIGHT";
            }

            // CHECK TILE COLLISION
            colisionOn = false;
            panel.ck.checkTile(this);

            //CHECK OBJECT COLLISION
            int objIndex = panel.ck.CheckObject(this, true);
            recolectarObjetivo(objIndex);

            if (colisionOn == false ) {
                switch (direccion) {
                    case "UP":
                        y -= velocidad;
                        break;
                    case "DOWN":
                        y += velocidad;
                        break;
                    case "LEFT":
                        x -= velocidad;
                        break;
                    case "RIGHT":
                        x += velocidad;
                        break;

                }
            }
        }
    }

    public void recolectarObjetivo(int i){
        if(i != 999){
            switch (panel.obj[i].nombre){
                case "Dinero":
                    objetivo1++;
                    break;
                case "Edificio":
                    objetivo2++;
                    break;
            }
            panel.obj[i] = null;
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage imagen = null;
        switch (direccion){
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
        g2.drawImage(imagen, x, y, panel.tileSize, panel.tileSize, null);
    }
}
