package Main;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame principal = new JFrame();
        principal.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        principal.setResizable(false);
        principal.setTitle("Tank Attack");
        principal.setIconImage(new ImageIcon("src/Imagenes/icon.png  ").getImage());

        GamePanel juego = new GamePanel();
        principal.add(juego);
        principal.pack();

        principal.setLocationRelativeTo(null);
        principal.setVisible(true);

        juego.gameSetUp();
        juego.startGameThread();

    }

}
