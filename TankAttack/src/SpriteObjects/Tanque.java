package SpriteObjects;
import Main.GamePanel;
import Main.KeyHandler;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

//Clase Tanque extiende de la clase Sprite
public class Tanque extends Sprite {
    GamePanel panel;
    KeyHandler keyH;
    public int objetivo1 = 0;
    public int objetivo2 = 0;
    public int totalObjetivos;
    public int vidas;


    //Constructor de la clase
    public Tanque(GamePanel gp, KeyHandler kH){
        super(gp);
        this.panel = gp;
        this.keyH = kH;
        this.vidas = 3;
        bala = new Bala(gp);
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

    //Inicialización de una instancia de tanque con valores por defecto
    public void initTanque(){
        x = 50;
        y = 50;
        velocidad = 2;
        direccion = "RIGHT";
    }

    //Carga de las imágenes del objeto Tanque
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

    //Método que actualiza la posición del tanque y verifica si el juego ya se completó
    @Override
    public void update() {
        if (this.objetivo1+objetivo2 == this.totalObjetivos || this.vidas == 0){
            panel.gameOver = true;
        }
        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed){
            if (keyH.upPressed) {
                direccion = "UP";
            } else if (keyH.downPressed) {
                direccion = "DOWN";
            } else if (keyH.leftPressed) {
                direccion = "LEFT";
            } else if (keyH.rightPressed) {
                direccion = "RIGHT";
            }

            //comprobar colision con tanques
            int tanIndx = panel.ck.check_enemy(this);
            if (tanIndx != 999){
                this.vidas--;
            }

            //Comprobar colisión con los muros
            colisionOn = false;
            panel.ck.checkTile(this);

            //Comprobar colisión con los objetivos
            int objIndex = panel.ck.CheckObject(this, true);
            recolectarObjetivo(objIndex);

            //Comprobar disparo acertado
            /*for (int i = 0; i < panel.enemy.length; i++){
                if (panel.enemy[i] != null){
                    int eneIndex = panel.ck.CheckBulletJ(panel.enemy[i], true);
                    if (dispararEnemigo(eneIndex)){
                        break;
                    };
                }

            }*/
            panel.ck.checkEnemyBala(panel.balas,panel.enemy);

            //Comprobar que se recibe un disparo
            int dIndex = panel.ck.CheckBulletE(this, true);
            disparoRecibido(dIndex);

            //Si no hay colisión se mueve el tanque jugador
            if (!colisionOn) {
                switch (direccion) {
                    case "UP" -> y -= velocidad;
                    case "DOWN" -> y += velocidad;
                    case "LEFT" -> x -= velocidad;
                    case "RIGHT" -> x += velocidad;
                }
            }
        }
        if (keyH.shotPressed && !bala.alive){
            bala.set(x+15, y, direccion, true, this);
            panel.balas.add(bala);
        }
    }

    //Método que dispara al tanque jugador
    public void disparoRecibido(int i){
        if(i != 999){
            this.vidas--;
        }
    }

    //Método que dispara a los tanques enemigos
    public boolean dispararEnemigo(int i){
        if(i != 999){
            panel.enemy[i] = null;
            bala.alive = false;
            return true;
        }
        return false;
    }

    //Método de recolección de objetivos
    public void recolectarObjetivo(int i){
        if(i != 999){
            switch (panel.obj[i].nombre) {
                case "Dinero" -> objetivo1++;
                case "Edificio" -> objetivo2++;
            }
            panel.obj[i] = null;
        }
    }

    //Método dibujar
    public void draw(Graphics2D g2) {
        BufferedImage imagen = switch (direccion) {
            case "UP" -> up;
            case "DOWN" -> down;
            case "RIGHT" -> right;
            case "LEFT" -> left;
            default -> null;
        };
        g2.drawImage(imagen, x, y, panel.tileSize, panel.tileSize, null);
    }
}
