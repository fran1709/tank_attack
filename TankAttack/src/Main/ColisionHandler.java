package Main;

import SpriteObjects.Bala;
import SpriteObjects.Sprite;
import SpriteObjects.Tanque;

import java.awt.*;
import java.util.ArrayList;

public class ColisionHandler {
    GamePanel panel; //GamePanel del juego

    public ColisionHandler(GamePanel panel) { //Constructor de la clase ColisionHandler
        this.panel = panel;
    }

    //Método que verifica si el jugador colisiona con los muros del mapa
    public void checkTile(Sprite entity) {

        int entityLeftX = entity.x + entity.solidArea.x;
        int entityRightX = entity.x + entity.solidArea.x + entity.solidArea.width;

        int entityTopY = entity.y + entity.solidArea.y;
        int entityBottomY = entity.y + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftX / panel.tileSize;
        int entityRightCol = entityRightX / panel.tileSize;

        int entityTopRow = entityTopY / panel.tileSize;
        int entityBottomRow = entityBottomY / panel.tileSize;

        int tileNum1, tileNum2;

        switch (entity.direccion) {
            case "UP" -> {
                entityTopRow = (entityTopY - entity.velocidad) / panel.tileSize;
                tileNum1 = panel.map.mapa[entityLeftCol][entityTopRow];
                tileNum2 = panel.map.mapa[entityRightCol][entityTopRow];
                if (panel.map.muros[tileNum1].colision || panel.map.muros[tileNum2].colision) {
                    entity.colisionOn = true;
                }
            }
            case "DOWN" -> {
                entityBottomRow = (entityBottomY + entity.velocidad) / panel.tileSize;
                tileNum1 = panel.map.mapa[entityLeftCol][entityBottomRow];
                tileNum2 = panel.map.mapa[entityRightCol][entityBottomRow];
                if (panel.map.muros[tileNum1].colision || panel.map.muros[tileNum2].colision) {
                    entity.colisionOn = true;
                }
            }
            case "LEFT" -> {
                entityLeftCol = (entityLeftX - entity.velocidad) / panel.tileSize;
                tileNum1 = panel.map.mapa[entityLeftCol][entityTopRow];
                tileNum2 = panel.map.mapa[entityLeftCol][entityBottomRow];
                if (panel.map.muros[tileNum1].colision || panel.map.muros[tileNum2].colision) {
                    entity.colisionOn = true;
                }
            }
            case "RIGHT" -> {
                entityRightCol = (entityRightX + entity.velocidad) / panel.tileSize;
                tileNum1 = panel.map.mapa[entityRightCol][entityTopRow];
                tileNum2 = panel.map.mapa[entityRightCol][entityBottomRow];
                if (panel.map.muros[tileNum1].colision || panel.map.muros[tileNum2].colision) {
                    entity.colisionOn = true;
                }
            }
        }
    }

    //Método que verifica si el jugador colisiona con los objetivos
    public int CheckObject(Sprite entity, boolean player){
        int index = 999;
        for(int i = 0; i < panel.obj.length; i++){
            if(panel.obj[i] != null){
                entity.solidArea.x = entity.x + entity.solidArea.x;
                entity.solidArea.y = entity.y + entity.solidArea.y;
                panel.obj[i].solidArea.x = panel.obj[i].x + panel.obj[i].solidArea.x;
                panel.obj[i].solidArea.y = panel.obj[i].y + panel.obj[i].solidArea.y;
                switch (entity.direccion) {
                    case "UP" -> {
                        entity.solidArea.y -= entity.velocidad;
                        if (entity.solidArea.intersects(panel.obj[i].solidArea)) {
                            if (panel.obj[i].colision) {
                                entity.colisionOn = true;
                            }
                            if (player) {
                                index = i;
                            }
                        }
                    }
                    case "DOWN" -> {
                        entity.solidArea.y += entity.velocidad;
                        if (entity.solidArea.intersects(panel.obj[i].solidArea)) {
                            if (panel.obj[i].colision) {
                                entity.colisionOn = true;
                            }
                            if (player) {
                                index = i;
                            }
                        }
                    }
                    case "LEFT" -> {
                        entity.solidArea.x -= entity.velocidad;
                        if (entity.solidArea.intersects(panel.obj[i].solidArea)) {
                            if (panel.obj[i].colision) {
                                entity.colisionOn = true;
                            }
                            if (player) {
                                index = i;
                            }
                        }
                    }
                    case "RIGHT" -> {
                        entity.solidArea.x += entity.velocidad;
                        if (entity.solidArea.intersects(panel.obj[i].solidArea)) {
                            if (panel.obj[i].colision) {
                                entity.colisionOn = true;
                            }
                            if (player) {
                                index = i;
                            }
                        }
                    }
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                panel.obj[i].solidArea.x = panel.obj[i].solidAreaDefaultX;
                panel.obj[i].solidArea.y = panel.obj[i].solidAreaDefaultY;
            }
        }
        return index;
    }

    public int check_enemy(Sprite entity){
        int index = 999;
        for(int i = 0; i < panel.enemy.length; i++){
            if(panel.enemy[i] != null){
                entity.solidArea.x = entity.x + entity.solidArea.x;
                entity.solidArea.y = entity.y + entity.solidArea.y;
                panel.enemy[i].solidArea.x = panel.enemy[i].x + panel.enemy[i].solidArea.x;
                panel.enemy[i].solidArea.y = panel.enemy[i].y + panel.enemy[i].solidArea.y;
                switch (entity.direccion) {
                    case "UP" -> {
                        entity.solidArea.y -= entity.velocidad;
                        if (entity.solidArea.intersects(panel.enemy[i].solidArea)) {
                            if (panel.enemy[i].colisionOn) {
                                entity.colisionOn = true;

                            }

                        }
                    }
                    case "DOWN" -> {
                        entity.solidArea.y += entity.velocidad;
                        if (entity.solidArea.intersects(panel.enemy[i].solidArea)) {
                            if (panel.enemy[i].colisionOn) {
                                entity.colisionOn = true;
                            }

                        }
                    }
                    case "LEFT" -> {
                        entity.solidArea.x -= entity.velocidad;
                        if (entity.solidArea.intersects(panel.enemy[i].solidArea)) {
                            if (panel.enemy[i].colisionOn) {
                                entity.colisionOn = true;
                            }

                        }
                    }
                    case "RIGHT" -> {
                        entity.solidArea.x += entity.velocidad;
                        if (entity.solidArea.intersects(panel.enemy[i].solidArea)) {
                            if (panel.enemy[i].colisionOn) {
                                entity.colisionOn = true;
                            }

                        }
                    }
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                panel.enemy[i].solidArea.x = panel.enemy[i].solidAreaDefaultX;
                panel.enemy[i].solidArea.y = panel.enemy[i].solidAreaDefaultY;
            }
        }
        return index;
    }
    public void check_player(Sprite entity){
        entity.solidArea.x = entity.x + entity.solidArea.x;
        entity.solidArea.y = entity.y + entity.solidArea.y;
        panel.jugador.solidArea.x = panel.jugador.x + panel.jugador.solidArea.x;
        panel.jugador.solidArea.y = panel.jugador.y + panel.jugador.solidArea.y;
        switch (entity.direccion) {
            case "UP" -> {
                entity.solidArea.y -= entity.velocidad;
                if (entity.solidArea.intersects(panel.jugador.solidArea)) {
                    if (panel.jugador.colisionOn) {
                        entity.colisionOn = true;
                    }

                }
            }
            case "DOWN" -> {
                entity.solidArea.y += entity.velocidad;
                if (entity.solidArea.intersects(panel.jugador.solidArea)) {
                    if (panel.jugador.colisionOn) {
                        entity.colisionOn = true;
                    }

                }
            }
            case "LEFT" -> {
                entity.solidArea.x -= entity.velocidad;
                if (entity.solidArea.intersects(panel.jugador.solidArea)) {
                    if (panel.jugador.colisionOn) {
                        entity.colisionOn = true;
                    }

                }
            }
            case "RIGHT" -> {
                entity.solidArea.x += entity.velocidad;
                if (entity.solidArea.intersects(panel.jugador.solidArea)) {
                    if (panel.jugador.colisionOn) {
                        entity.colisionOn = true;
                    }

                }
            }
        }

        entity.solidArea.x = entity.solidAreaDefaultX;
        entity.solidArea.y = entity.solidAreaDefaultY;
        panel.jugador.solidArea.x = panel.jugador.solidAreaDefaultX;
        panel.jugador.solidArea.y = panel.jugador.solidAreaDefaultY;
    }
    //Método que verifica si el jugador dispara exitosamente a un enemigo
    public int CheckBulletJ(Sprite entity, boolean player){
        int index = 999;
        for(int i = 0; i < panel.balas.size(); i++){
            if(panel.balas.get(i) != null && panel.balas.get(i).user == panel.jugador){
                entity.solidArea.x = entity.x + entity.solidArea.x;
                entity.solidArea.y = entity.y + entity.solidArea.y;
                panel.balas.get(i).solidArea.x = panel.balas.get(i).x + panel.balas.get(i).solidArea.x;
                panel.balas.get(i).solidArea.y = panel.balas.get(i).y + panel.balas.get(i).solidArea.y;
                switch (entity.direccion) {
                    case "UP" -> {
                        entity.solidArea.y -= entity.velocidad;
                        if (entity.solidArea.intersects(panel.balas.get(i).solidArea)) {
                            if (panel.balas.get(i).colision) {
                                entity.colisionOn = true;
                            }
                            if (player) {
                                index = i;
                            }
                        }
                    }
                    case "DOWN" -> {
                        entity.solidArea.y += entity.velocidad;
                        if (entity.solidArea.intersects(panel.balas.get(i).solidArea)) {
                            if (panel.balas.get(i).colision) {
                                entity.colisionOn = true;
                            }
                            if (player) {
                                index = i;
                            }
                        }
                    }
                    case "LEFT" -> {
                        entity.solidArea.x -= entity.velocidad;
                        if (entity.solidArea.intersects(panel.balas.get(i).solidArea)) {
                            if (panel.balas.get(i).colision) {
                                entity.colisionOn = true;
                            }
                            if (player) {
                                index = i;
                            }
                        }
                    }
                    case "RIGHT" -> {
                        entity.solidArea.x += entity.velocidad;
                        if (entity.solidArea.intersects(panel.balas.get(i).solidArea)) {
                            if (panel.balas.get(i).colision) {
                                entity.colisionOn = true;
                            }
                            if (player) {
                                index = i;
                            }
                        }
                    }
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                panel.balas.get(i).solidArea.x = panel.balas.get(i).solidAreaDefaultX;
                panel.balas.get(i).solidArea.y = panel.balas.get(i).solidAreaDefaultY;
            }
        }
        return index;
    }

    //Método que verifica si el jugador dispara exitosamente a un enemigo
    public int CheckBulletE(Sprite entity, boolean player){
        int index = 999;
        for(int i = 0; i < panel.balas.size(); i++){
            if(panel.balas.get(i) != null && panel.balas.get(i).user != entity){
                entity.solidArea.x = entity.x + entity.solidArea.x;
                entity.solidArea.y = entity.y + entity.solidArea.y;
                panel.balas.get(i).solidArea.x = panel.balas.get(i).x + panel.balas.get(i).solidArea.x;
                panel.balas.get(i).solidArea.y = panel.balas.get(i).y + panel.balas.get(i).solidArea.y;
                switch (entity.direccion) {
                    case "UP" -> {
                        entity.solidArea.y -= entity.velocidad;
                        if (entity.solidArea.intersects(panel.balas.get(i).solidArea)) {
                            if (panel.balas.get(i).colision) {
                                entity.colisionOn = true;
                            }
                            if (player) {
                                index = i;
                            }
                        }
                    }
                    case "DOWN" -> {
                        entity.solidArea.y += entity.velocidad;
                        if (entity.solidArea.intersects(panel.balas.get(i).solidArea)) {
                            if (panel.balas.get(i).colision) {
                                entity.colisionOn = true;
                            }
                            if (player) {
                                index = i;
                            }
                        }
                    }
                    case "LEFT" -> {
                        entity.solidArea.x -= entity.velocidad;
                        if (entity.solidArea.intersects(panel.balas.get(i).solidArea)) {
                            if (panel.balas.get(i).colision) {
                                entity.colisionOn = true;
                            }
                            if (player) {
                                index = i;
                            }
                        }
                    }
                    case "RIGHT" -> {
                        entity.solidArea.x += entity.velocidad;
                        if (entity.solidArea.intersects(panel.balas.get(i).solidArea)) {
                            if (panel.balas.get(i).colision) {
                                entity.colisionOn = true;
                            }
                            if (player) {
                                index = i;
                            }
                        }
                    }
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                panel.balas.get(i).solidArea.x = panel.balas.get(i).solidAreaDefaultX;
                panel.balas.get(i).solidArea.y = panel.balas.get(i).solidAreaDefaultY;
            }
        }
        return index;
    }
    public void checkEnemyBala(ArrayList<Bala> balas, Sprite[] TankAIs) {
        for (int x = 0; x < balas.size(); x++) {
            Bala b = balas.get(x);
            Rectangle r1 = b.solidArea.getBounds();
            for (int i = 0; i < 10; i++) {
                Sprite tankAI = TankAIs[i];
                if ( tankAI != null){
                    Rectangle r2 = tankAI.solidArea.getBounds();
                    if (r1.intersects(r2) && b.user == panel.jugador) {
                        b.alive = false;
                        TankAIs[i] = null;
                        break;
                    }
                }
            }
        }
    }
    public static void checkJugadorBala(ArrayList<Bala> balas, Tanque tank) {
        Rectangle r2 = tank.solidArea.getBounds();
        for (int x = 0; x < balas.size(); x++) {
            Bala b = balas.get(x);
            Rectangle r1 = b.solidArea.getBounds();
            if (r1.intersects(r2) && b.user != tank) {
                b.alive = false;
                tank.vidas--;
            }
        }
    }
}
