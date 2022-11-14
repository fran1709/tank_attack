package Prolog;

import org.jpl7.*;

public class Road {
    public Road(){}

    public void Start(){
        // Connection with database.
		Query conn = new Query("consult",new Term[] {new Atom("src/Prolog/dijkstra.pl")});
		System.out.println("Connection " + (conn.hasSolution() ? "succeeded" : "failed") + " to the data base.\n");
    }

    public String[] path(String x, String y){
		Start();
		Variable path = new Variable("Camino");
		Variable weight = new Variable("Peso");
		Query best_path = new Query("path", new Term[] {new Atom(x), new Atom(y),path,weight});
		System.out.println( "Best solution of path("+x+", "+y+")");
		if (!best_path.hasSolution()){
			String[] lista = {"1","1"};
			return lista;
		}
		System.out.println(best_path.oneSolution().get("Camino")+"\n");
		best_path.close();

		try {
			String[] direction = {String.valueOf(best_path.oneSolution().get("Camino").arg(1)),
					String.valueOf(best_path.oneSolution().get("Camino").arg(2))};
			return direction;
		} catch (JPLException e){}

		return new String[2];
    }

	public void add(String pAtomA, String pAtomB){
		Start();
		Query azert = new Query("assert(dist("+pAtomA+", "+pAtomB+", 1)).");
		//Query azert = new Query("dist",new Term[]{new Atom(pX), new Atom(pY), new Integer(pWeight)});
		System.out.println(azert.oneSolution()+"\n");
		azert.close();

	}
}
