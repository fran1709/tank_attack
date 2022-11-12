package Objetos;

import javax.imageio.ImageIO;
import java.io.FileInputStream;
import java.io.IOException;

//Clase que representa el primer tipo de objetivo extiende de la clase objeto
public class Objetivo1 extends Objeto{

    public Objetivo1(){ //Constructor de la clase
        nombre = "Dinero";
        try {
            imagen = ImageIO.read(new FileInputStream("src/Imagenes/Objetivo1.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
