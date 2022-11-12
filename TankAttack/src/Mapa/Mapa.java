package Mapa;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;

public class Mapa {
    GamePanel panel;
    public Muro[] muros;
    public int[][] mapa;

    public Mapa(GamePanel p){
        this.panel = p;
        muros = new Muro[3];
        mapa = new int[panel.maxScreenCol][panel.maxScreenRow];
        getImagenMuro();
        cargarMapa("src/Mapa/Mapa1.0");
    }

    public void getImagenMuro(){
        try {
            muros[0] = new Muro();
            muros[0].imagen = ImageIO.read(new File("src/Imagenes/Grass.png"));

            muros[1] = new Muro();
            muros[1].imagen = ImageIO.read(new File("src/Imagenes/Borde.png"));
            muros[1].colision = true;

            muros[2] = new Muro();
            muros[2].imagen = ImageIO.read(new File("src/Imagenes/Pared.png"));
            muros[2].colision = true;

        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void cargarMapa(String map){
        try {
            FileReader fr = new FileReader(map);
            BufferedReader br = new BufferedReader(fr);
            int col = 0, fil = 0;
            while (col < panel.maxScreenCol && fil < panel.maxScreenRow){
                String line = br.readLine();
                while (col < panel.maxScreenCol){
                    String numeros[] = line.split(" ");
                    int numero = Integer.parseInt(numeros[col]);
                    mapa[col][fil] = numero;
                    col++;
                }
                if(col == panel.maxScreenCol){
                    col = 0;
                    fil++;
                }
            }
            br.close();
        } catch(Exception e){
            e.printStackTrace();

        }

    }

    public void draw(Graphics2D g2){
        int col = 0, fil = 0, x = 0, y = 0;
        while (col < panel.maxScreenCol && fil < panel.maxScreenRow){
            int nuMuro = mapa[col][fil];
            g2.drawImage(muros[nuMuro].imagen, x, y, panel.tileSize, panel.tileSize, null);
            col++;
            x += panel.tileSize;

            if (col == panel.maxScreenCol){
                col = 0;
                x = 0;
                fil++;
                y += panel.tileSize;
            }
        }
    }
}
