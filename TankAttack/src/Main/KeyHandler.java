package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    //Variables que indican si se presiona las teclas
    public boolean upPressed, downPressed, rightPressed, leftPressed;
    @Override
    public void keyTyped(KeyEvent e) {}

    //Función que se ejecuta cuando se presiona una tecla
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_UP) {
            upPressed = true;
        }
        if (code == KeyEvent.VK_DOWN) {
            downPressed = true;
        }
        if (code == KeyEvent.VK_LEFT) {
            leftPressed = true;
        }
        if (code == KeyEvent.VK_RIGHT) {
            rightPressed = true;
        }
    }

    //Función que se ejecuta cuando se deja de presionar una tecla
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_UP) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_DOWN) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_LEFT) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_RIGHT) {
            rightPressed = false;
        }
    }
}
