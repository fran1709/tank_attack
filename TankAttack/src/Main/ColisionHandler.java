package Main;

import SpriteObjects.Sprite;

public class ColisionHandler {
    GamePanel panel;

    public ColisionHandler(GamePanel panel) {
        this.panel = panel;
    }

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
            case "UP":
                entityTopRow = (entityTopY - entity.velocidad) / panel.tileSize;
                tileNum1 = panel.map.mapa[entityLeftCol][entityTopRow];
                tileNum2 = panel.map.mapa[entityRightCol][entityTopRow];
                if (panel.map.muros[tileNum1].colision == true || panel.map.muros[tileNum2].colision == true) {
                    entity.colisionOn = true;
                }
                break;
            case "DOWN":
                entityBottomRow = (entityBottomY + entity.velocidad) / panel.tileSize;
                tileNum1 = panel.map.mapa[entityLeftCol][entityBottomRow];
                tileNum2 = panel.map.mapa[entityRightCol][entityBottomRow];
                if (panel.map.muros[tileNum1].colision == true || panel.map.muros[tileNum2].colision == true) {
                    entity.colisionOn = true;
                }
                break;
            case "LEFT":
                entityLeftCol = (entityLeftX - entity.velocidad) / panel.tileSize;
                tileNum1 = panel.map.mapa[entityLeftCol][entityTopRow];
                tileNum2 = panel.map.mapa[entityLeftCol][entityBottomRow];
                if (panel.map.muros[tileNum1].colision == true || panel.map.muros[tileNum2].colision == true) {
                    entity.colisionOn = true;
                }
                break;
            case "RIGHT":
                entityRightCol = (entityRightX + entity.velocidad) / panel.tileSize;
                tileNum1 = panel.map.mapa[entityRightCol][entityTopRow];
                tileNum2 = panel.map.mapa[entityRightCol][entityBottomRow];
                if (panel.map.muros[tileNum1].colision == true || panel.map.muros[tileNum2].colision == true) {
                    entity.colisionOn = true;
                }
                break;
        }
    }

    public int CheckObject(Sprite entity, boolean player){
        int index = 999;
        for(int i = 0; i < panel.obj.length; i++){
            if(panel.obj[i] != null){
                entity.solidArea.x = entity.x + entity.solidArea.x;
                entity.solidArea.y = entity.y + entity.solidArea.y;
                panel.obj[i].solidArea.x = panel.obj[i].x + panel.obj[i].solidArea.x;
                panel.obj[i].solidArea.y = panel.obj[i].y + panel.obj[i].solidArea.y;
                switch (entity.direccion){
                    case "UP":
                        entity.solidArea.y -= entity.velocidad;
                        if(entity.solidArea.intersects(panel.obj[i].solidArea)){
                            if(panel.obj[i].colision == true){
                                entity.colisionOn = true;
                            }
                            if(player){
                                index = i;
                            }
                        }
                        break;
                    case "DOWN":
                        entity.solidArea.y += entity.velocidad;
                        if(entity.solidArea.intersects(panel.obj[i].solidArea)){
                            if(panel.obj[i].colision == true){
                                entity.colisionOn = true;
                            }
                            if(player){
                                index = i;
                            }
                        }
                        break;
                    case "LEFT":
                        entity.solidArea.x -= entity.velocidad;
                        if(entity.solidArea.intersects(panel.obj[i].solidArea)){
                            if(panel.obj[i].colision == true){
                                entity.colisionOn = true;
                            }
                            if(player){
                                index = i;
                            }
                        }
                        break;
                    case "RIGHT":
                        entity.solidArea.x += entity.velocidad;
                        if(entity.solidArea.intersects(panel.obj[i].solidArea)){
                            if(panel.obj[i].colision == true){
                                entity.colisionOn = true;
                            }
                            if(player){
                                index = i;
                            }
                        }
                        break;
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                panel.obj[i].solidArea.x = panel.obj[i].solidAreaDefaultX;
                panel.obj[i].solidArea.y = panel.obj[i].solidAreaDefaultY;
            }
        }
        return index;
    }
}
