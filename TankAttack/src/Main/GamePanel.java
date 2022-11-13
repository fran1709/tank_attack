package Main;

import Mapa.Mapa;
import Objetos.Objetivo1;
import Objetos.Objetivo2;
import Objetos.Objeto;
import SpriteObjects.Enemy;
import SpriteObjects.Sprite;
import SpriteObjects.Tanque;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel implements Runnable {
    //Configuración del tamaño del GamePanel
    final int originalTileSize = 16;
    final int scale = 3;
    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    //Bandera de Game Over
    public boolean gameOver = false;

    //Frames por segundo (cantidad de veces que se actualiza el mapa)
    int fps = 60;
    Font arial_40; //Declaración de fuente (tipo de letra)
    BufferedImage obj1Imagen, obj2Imagen, enemyImg; //Imágenes de los objetivos

    public Thread gameThread; //Thread de juego
    KeyHandler keyControl = new KeyHandler(); //Controlador de la entrada por teclado

    // ------ENTIDADES & OBJETOS-----
    Tanque jugador = new Tanque(this, keyControl); //Instancia del tanque jugador
    public Objeto obj[] = new Objeto[10]; //Lista de objetivos
    public Sprite enemy[] = new Sprite[10];

    Mapa map = new Mapa(this); //Mapa del juego
    public ColisionHandler ck = new ColisionHandler(this); //Controlador de colisiones entre objetos

    public GamePanel(){ //Constructor de la clase GamePanel
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyControl);
        this.setFocusable(true);
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        Objetivo1 o1 = new Objetivo1();
        obj1Imagen = o1.imagen;
        Objetivo2 o2 = new Objetivo2();
        obj2Imagen = o2.imagen;
    }

    //Inicialización del Thread del juego
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    //Runnable del Thread del juego
    @Override
    public void run() {
        double drawInterval = 1000000000/fps;
        double nextDrawTime = System.nanoTime() + drawInterval;
        while (gameThread!=null){
            update();
            repaint();
            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;
                if(remainingTime < 0){
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterval;

            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }

    }

    //Método que actualiza los elementos del panel
    public void update(){
        jugador.update();
        for (Sprite sprite : enemy) {
            if (sprite != null) {
                sprite.update();
                sprite.posicionar_player(String.valueOf(sprite.x), String.valueOf(sprite.y),
                        String.valueOf(jugador.x), String.valueOf(jugador.y));
            }
        }
    }

    //Método que dibuja los elementos en el panel
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        map.draw(g2);
        for (Objeto objeto : obj) {
            if (objeto != null) {
                objeto.draw(g2, this);
            }
        }
        for (Sprite sprite : enemy) {
            if (sprite != null) {
                sprite.draw(g2, this);
                //sprite.cargar_mapa(map);
            }
        }
        jugador.draw(g2);
        drawStats(g2);
        g2.dispose();
    }

    //Método que crea los objetos que se muestran el en mapa (panel)
    public void initObjetivos(){
        this.obj[0] = new Objetivo1();
        this.obj[0].x = 6 * this.tileSize;
        this.obj[0].y = 8 * this.tileSize;
        jugador.totalObjetivos++;

        this.obj[1] = new Objetivo1();
        this.obj[1].x = 10 * this.tileSize;
        this.obj[1].y = 7 * this.tileSize;
        jugador.totalObjetivos++;

        this.obj[2] = new Objetivo2();
        this.obj[2].x = 13 * this.tileSize;
        this.obj[2].y = 3 * this.tileSize;
        jugador.totalObjetivos++;

        this.obj[3] = new Objetivo2();
        this.obj[3].x = 8 * this.tileSize;
        this.obj[3].y = 5 * this.tileSize;
        jugador.totalObjetivos++;
    }

    //Método que dibuja las estadísticas del juego, (objetivos recolectados, vida del jugador y mensaje de Game Over)
    public void drawStats(Graphics2D g2){
        if(gameOver){
            g2.setFont(arial_40);
            g2.setColor(Color.white);
            int x, y;
            String mensaje = "Game Over";
            int txtLength = (int)g2.getFontMetrics().getStringBounds(mensaje, g2).getWidth();
            x = this.screenWidth/2 - txtLength/2;
            y = this.screenHeight/2;
            g2.drawString(mensaje, x, y);
            gameThread.stop();

        }
        else {
            g2.setFont(arial_40);
            g2.setColor(Color.black);
            g2.drawImage(obj1Imagen, this.tileSize/2, 0, this.tileSize, this.tileSize, null);
            g2.drawString("x "+ this.jugador.objetivo1,74,35);
            g2.drawImage(obj2Imagen, 150, 0, this.tileSize, 45, null);
            g2.drawString("x "+ this.jugador.objetivo2,200,35);
            g2.drawImage(jugador.vida, 276, 0, this.tileSize, this.tileSize, null);
            g2.drawString("x "+ this.jugador.vidas,326,35);
        }
    }

    public void initEnemys(){
        this.enemy[0] = new Enemy(this);
        this.enemy[0].x = this.tileSize * 7;
        this.enemy[0].y = this.tileSize * 8;
        this.enemy[0].cargar_mapa(map);

        this.enemy[1] = new Enemy(this);
        this.enemy[1].x = this.tileSize * 7;
        this.enemy[1].y = this.tileSize * 4;
        this.enemy[1].cargar_mapa(map);

        this.enemy[2] = new Enemy(this);
        this.enemy[2].x = this.tileSize * 11;
        this.enemy[2].y = this.tileSize * 2;
        this.enemy[2].cargar_mapa(map);
    }

    //Método que configura juego
    public void gameSetUp(){
        this.initEnemys();
        this.initObjetivos();
    }
}
