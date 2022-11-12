package Objetos;

import javax.imageio.ImageIO;
import java.io.FileInputStream;
import java.io.IOException;

public class Objetivo2 extends Objeto{

    public Objetivo2(){
        nombre = "Edificio";
        try {
            imagen = ImageIO.read(new FileInputStream("src/Imagenes/Objetivo2.png"));

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
