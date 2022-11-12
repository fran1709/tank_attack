package Main;

import Mapa.Mapa;
import Objetos.Objetivo1;
import Objetos.Objetivo2;
import Objetos.Objeto;
import SpriteObjects.Tanque;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel implements Runnable {
    final int originalTileSize = 16;
    final int scale = 3;
    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; //512
    public final int screenHeight = tileSize * maxScreenRow; //448
    public boolean gameOver = false;
    int fps = 60;
    Font arial_40;
    BufferedImage obj1Imagen, obj2Imagen;

    public Thread gameThread;
    KeyHandler keyControl = new KeyHandler();

    Tanque jugador = new Tanque(this, keyControl);
    public Objeto obj[] = new Objeto[10];
    Mapa map = new Mapa(this);
    public ColisionHandler ck = new ColisionHandler(this);

    public GamePanel(){
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

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

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

    public void update(){
        jugador.update();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        map.draw(g2);
        for (int i = 0; i < obj.length; i++){
            if (obj[i] != null){
                obj[i].draw(g2, this);
            }
        }
        jugador.draw(g2);
        drawStats(g2);
        g2.dispose();
    }

    public void initObjetivos(){
        this.obj[0] = new Objetivo1();
        this.obj[0].x = 6 * this.tileSize;
        this.obj[0].y = 8 * this.tileSize;

        this.obj[1] = new Objetivo1();
        this.obj[1].x = 10 * this.tileSize;
        this.obj[1].y = 7 * this.tileSize;

        this.obj[2] = new Objetivo2();
        this.obj[2].x = 13 * this.tileSize;
        this.obj[2].y = 3 * this.tileSize;

        this.obj[3] = new Objetivo2();
        this.obj[3].x = 8 * this.tileSize;
        this.obj[3].y = 5 * this.tileSize;
    }

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

    public void gameSetUp(){
        this.initObjetivos();

    }
}
