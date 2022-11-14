package SpriteObjects;

import Main.GamePanel;
import Main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

public class Bala extends Sprite{

    public boolean colision;
    int maxLife, life;
    public Sprite user;

    public Bala(GamePanel p) {
        super(p);
        velocidad = 5;
        this.maxLife = 80;
        this.life = maxLife;
        this.alive = false;
        cargarImagenes();
    }

    private void cargarImagenes() {
        up = setup("BalaU");
        down = setup("BalaD");
        right = setup("BalaR");
        left = setup("BalaL");
    }

    public void set(int x, int y, String direccion, boolean alive, Sprite user){
        this.x = x;
        this.y = y;
        this.direccion = direccion;
        this.alive = alive;
        this.user = user;
        this.life = this.maxLife;
    }

    public BufferedImage setup(String nombreImagen){
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;
        try {
            image = ImageIO.read(new FileInputStream("src/Imagenes/" + nombreImagen + ".png"));
            image = uTool.scaleImage(image, 16, 16);

        } catch (IOException e){
            e.printStackTrace();
        }
        return image;
    }

    public void update(){
        if (user == gpSuper.enemy[9]){

        }
        if (user != gpSuper.enemy[9]){

        }
        switch (direccion){
            case "UP":
                y -= velocidad;
                break;
            case "DOWN":
                y += velocidad;
                break;
            case "RIGHT":
                x += velocidad;
                break;
            case "LEFT":
                x -= velocidad;
                break;
        }

        life--;
        if (life <= 0){
            alive = false;
        }

    }
}
