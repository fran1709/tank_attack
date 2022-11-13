package Main;

import SpriteObjects.Sprite;

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
}
