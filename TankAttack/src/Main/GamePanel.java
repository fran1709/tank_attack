package Main;

import Mapa.Mapa;
import Objetos.Objetivo1;
import Objetos.Objetivo2;
import Objetos.Objeto;
import Prolog.Road;
import SpriteObjects.Bala;
import SpriteObjects.Enemy;
import SpriteObjects.Sprite;
import SpriteObjects.Tanque;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable {
    //Configuración del tamaño del GamePanel
    final int originalTileSize = 16;
    final int scale = 3;
    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;
    //Estado del juego
    public int gameState;
    public int titleState = 0;
    public int playState = 1;

    //Bandera de Game Over
    public boolean gameOver = false;

    //Frames por segundo (cantidad de veces que se actualiza el mapa)
    int fps = 60;
    Font arial_40; //Declaración de fuente (tipo de letra)
    BufferedImage obj1Imagen, obj2Imagen, enemyImg; //Imágenes de los objetivos

    public Thread gameThread; //Thread de juego
    KeyHandler keyControl = new KeyHandler(this); //Controlador de la entrada por teclado

    // ------ENTIDADES & OBJETOS-----
    Tanque jugador = new Tanque(this, keyControl); //Instancia del tanque jugador
    public Objeto[] obj = new Objeto[10]; //Lista de objetivos
    public Sprite[] enemy = new Sprite[10];
    public ArrayList<Bala> balas = new ArrayList<>(); //Array de balas
    Mapa map = new Mapa(this); //Mapa del juego
    public Road caminos = new Road();
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
        cargar_mapa();
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
        //this.caminos.Start();
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
                //System.out.println("asd");
                //this.encontrar_camino();

            }catch (InterruptedException e){
                e.printStackTrace();
            }

        }

    }

    //Método que actualiza los elementos del panel
    public void update(){
        if (gameState == playState){
            jugador.update();
            for (Sprite sprite : enemy) {
                if (sprite != null) {
                    sprite.update();
                }
            }
            for (int i = 0; i < balas.size(); i++){
                if (balas.get(i) != null){
                    if (balas.get(i).alive){
                        balas.get(i).update();
                    }
                    if (balas.get(i).alive == false){
                        balas.remove(i);
                    }
                }
            }
        }
    }

    //Método que dibuja los elementos en el panel
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        if(gameState == titleState){
            drawTitlePanel(g2);
        } else {
            map.draw(g2);
            for (Objeto objeto : obj) {
                if (objeto != null) {
                    objeto.draw(g2, this);
                }
            }
            for (Sprite sprite : enemy) {
                if (sprite != null) {
                    sprite.draw(g2, this);
                    //sprite.posicionar_player(String.valueOf(sprite.x/48), String.valueOf(sprite.y/48), String.valueOf(jugador.x/48), String.valueOf(jugador.y/48), caminos);
                }
            }
            for (int i = 0; i < balas.size(); i++) {
                if (balas.get(i) != null) {
                    balas.get(i).draw(g2);
                }
            }
            jugador.draw(g2);
            drawStats(g2);
            g2.dispose();
        }
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

        this.enemy[9] = jugador;
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

        this.enemy[1] = new Enemy(this);
        this.enemy[1].x = this.tileSize * 7;
        this.enemy[1].y = this.tileSize * 4;

        this.enemy[2] = new Enemy(this);
        this.enemy[2].x = this.tileSize * 11;
        this.enemy[2].y = this.tileSize * 2;
    }

    /**
     * Método encargado de proporcionarle el conocimiento a prolog.
     */
    public void cargar_mapa() {
        int[][] matriz = this.map.mapa;
        for (int i = 1; i < matriz.length-1; i++) {
            for (int j = 1; j < matriz[i].length-1; j++) {
                if (matriz[i][j] == 0){
                    if (matriz[i+1][j]==0){
                        System.out.println("Fila:" + i);
                        System.out.println("Columna:" + j);
                        System.out.println("x"+String.valueOf(i) +"x"+String.valueOf(j) +"->"+
                                "x"+String.valueOf(i+1) +"y"+String.valueOf(j));
                        caminos.add("x" + String.valueOf(i) + "y"+String.valueOf(j),
                                "x"+ (String.valueOf(i+1)) + "y"+String.valueOf(j));
                    }
                    else if (matriz[i][j+1] == 0){
                        System.out.println("Fila:" + i);
                        System.out.println("Columna:" + j);
                        System.out.println("x"+String.valueOf(i) +"y"+String.valueOf(j) +"->"+
                                "x"+String.valueOf(i) +"y"+String.valueOf(j+1));
                        caminos.add("x" + String.valueOf(i) + "y"+String.valueOf(j),
                                "x"+ (String.valueOf(i)) + "y"+String.valueOf(j+1));
                    } else if (matriz[i-1][j]==0){
                        System.out.println("Fila:" + i);
                        System.out.println("Columna:" + j);
                        System.out.println("x"+String.valueOf(i) +"y"+String.valueOf(j) +"->"+
                                "x"+String.valueOf(i-1) +"y"+String.valueOf(j));
                        caminos.add("x" + String.valueOf(i) + "y"+String.valueOf(j),
                                "x"+ (String.valueOf(i-1)) + "y"+String.valueOf(j));
                    } else if(matriz[i][j-1] == 0){
                        System.out.println("Fila:" + i);
                        System.out.println("Columna:" + j);
                        System.out.println("x"+String.valueOf(i) +"y"+String.valueOf(j) +"->"+
                                "x"+String.valueOf(i) +"y"+String.valueOf(j-1));
                        caminos.add("x" + String.valueOf(i) + "y"+String.valueOf(j),
                                "x"+ (String.valueOf(i)) + "y"+String.valueOf(j-1));
                    }
                }
            }
        }
    }

    //Método que dibuja el panel de inicio
    public void drawTitlePanel(Graphics2D g2){
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,96F));
        String text = "Tank Attack";
        int txtLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = this.screenWidth/2 - txtLength/2;
        int y = this.tileSize*3;

        //Sombra
        g2.setColor(Color.gray);
        g2.drawString(text, x+5, y+5);

        //Titulo
        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        //Imagen
        x = this.screenWidth/2 - (this.tileSize*2)/2;
        y += this.tileSize*2;
        g2.drawImage(jugador.up, x, y, this.tileSize*2, this.tileSize*2, null);

        //Indicación
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,40F));
        text = "Presione ENTER para iniciar";
        txtLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        x = this.screenWidth/2 - txtLength/2;
        y += this.tileSize*4;
        g2.drawString(text, x, y);
    }
    //Método que configura juego
    public void gameSetUp(){
        this.gameState = titleState;
        this.initEnemys();
        this.initObjetivos();
        //this.initMapProlog();
    }
}
