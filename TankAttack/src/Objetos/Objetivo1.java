package Objetos;

import javax.imageio.ImageIO;
import java.io.FileInputStream;
import java.io.IOException;

public class Objetivo1 extends Objeto{

    public Objetivo1(){
        nombre = "Dinero";
        try {
            imagen = ImageIO.read(new FileInputStream("src/Imagenes/Objetivo1.png"));

        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
