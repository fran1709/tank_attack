package Objetos;

import javax.imageio.ImageIO;
import java.io.FileInputStream;
import java.io.IOException;

//Clase que representa el segundo tipo de objetivo extiende de la clase objeto
public class Objetivo2 extends Objeto{

    public Objetivo2(){ //Constructor de la clase
        nombre = "Edificio";
        try {
            imagen = ImageIO.read(new FileInputStream("src/Imagenes/Objetivo2.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
