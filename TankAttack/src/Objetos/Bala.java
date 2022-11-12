package Objetos;

import Main.GamePanel;
import SpriteObjects.Proyectil;

public class Bala extends Proyectil {
    GamePanel panel;
    public Bala(GamePanel panel) {
        super(panel);
        this.panel = panel;
        velocidad = 5;
    }
}
