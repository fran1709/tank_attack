import com.objects.*;
import com.prolog.Road;

public class App {
    private static void prolog(){
        Road camino = new Road();
        camino.Start();        
        camino.Hijos();
    }
    private static void app(){
        main_Window window = new main_Window();
    }
    public static void main(String[] args) {
        prolog();
        app();
    }
}
