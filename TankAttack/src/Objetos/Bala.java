package Objetos;

import Main.GamePanel;

public class Bala extends SpriteObjects.Bala {
    GamePanel panel;
    public Bala(GamePanel panel) {
        super(panel);
        this.panel = panel;
        velocidad = 5;
    }
}
