package SpriteObjects;

import Main.GamePanel;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;

/**
 * Clase del tanque enemigo.
 */
public class Enemy extends Sprite{
    // ATRIBUTOS
    public GamePanel panel;
    public int accion_contador = 0;

    /**
     * Constructor de la clase.
     * @param pGp GamePanel de la clase.
     */
    public Enemy(GamePanel pGp){
        super(pGp);
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

    /**
     * Método encargado de inicializar el tanque con una velocidad y dirección.
     */
    public void initTanque(){
        velocidad = 1;
        direccion = "UP";
    }

    /**
     * Método encargado de realizar las acciones del tanque enemigo.
     */
    public void set_acciones(){
        String[] road = this.posicionar_player("x"+String.valueOf(this.x/48)+"y"+String.valueOf(this.y/48),
                    "x"+String.valueOf(gpSuper.enemy[9].x/48)+"y"+String.valueOf(gpSuper.enemy[9].y/48));
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
    /**
     * Método encargado de posicionar al jugador.
     *
     * @param pX String -> coordenada.
     * @param pY String -> coordenada.
     * @return []String -> camino hacia el jugador.
     */
    @Override
    public String[] posicionar_player(String pX, String pY) {
        //System.out.println(pX+"-"+pY);
        return panel.caminos.path(pX,pY);
    }

    /**
     * Método encargado de actualizar la entidad Enemy.
     */
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

    /**
     * Método encargado de dibujar la entidad.
     * @param g2 Graphics2D
     * @param panel GamePanel
     */
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

    /**
     * Método encargado de obtener las imágenes del directorio.
     */
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
