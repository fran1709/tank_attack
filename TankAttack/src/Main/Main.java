package Main;

import javax.swing.*;
import Prolog.Road;

public class Main {
    public static void main(String[] args) {

        //Configuración del frame principal
        JFrame principal = new JFrame();
        principal.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        principal.setResizable(false);
        principal.setTitle("Tank Attack");
        principal.setIconImage(new ImageIcon("src/Imagenes/icon.png").getImage());

        //Creación del GamePanel
        GamePanel juego = new GamePanel();
        principal.add(juego);
        principal.pack();

        //Se hace visible el frame
        principal.setLocationRelativeTo(null);
        principal.setVisible(true);

        //Se configura el panel
        juego.gameSetUp();

        //Se inicia el Thread del juego
        juego.startGameThread();
    }

}
