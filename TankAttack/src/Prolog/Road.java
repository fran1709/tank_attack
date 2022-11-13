package Prolog;

import org.jpl7.*;

public class Road {
    public Road(){}

    public void Start(){
        // Connection with database.
		Query conn = new Query("consult",new Term[] {new Atom("C:\\Users\\vecti\\OneDrive - Estudiantes ITCR\\TEC\\2022\\Semestre II\\Lenguajes de Programacion\\Segundo Proyecto Programado\\tank_attack\\TankAttack\\src\\Prolog\\dijkstra.pl")});
		System.out.println("Connection " + (conn.hasSolution() ? "succeeded" : "failed") + " to the data base.\n");

    }

    public void path(String x, String y){
		Variable path = new Variable("Camino");
		Variable weight = new Variable("Peso");
		Query best_path = new Query("path", new Term[] {new Atom(x), new Atom(y),path,weight});
		System.out.println( "Best solution of path("+x.toString()+", "+y+")");
		System.out.println(best_path.oneSolution()+"\n");
		best_path.close();
    }

	public void add(String pX, String pY){
		Query azert = new Query("assert(dist("+pX+", "+pY+", 1)).");
		//Query azert = new Query("dist",new Term[]{new Atom(pX), new Atom(pY), new Integer(pWeight)});
		System.out.println(azert.oneSolution()+"\n");
		azert.close();
	}
}
