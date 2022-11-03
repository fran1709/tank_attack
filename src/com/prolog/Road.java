package com.prolog;

import org.jpl7.*;

public class Road {
    public Road(){}

    public void Start(){
        // Connection with data base.
		Query conn = new Query("consult",new Term[] {new Atom("C:\\Users\\vecti\\eclipse-workspace\\Tank_Attack\\src\\com\\prolog\\dataBase.pl")});
		System.out.println("Connection " + (conn.hasSolution() ? "succeeded" : "failed") + " to the data base.\n");
    }

    public void Hijos(){
        Query q2 = new Query( "child_of", new Term[] {new Atom("joe"), new Atom("ralf")});
		System.out.println( "child_of(joe,ralf). Is " + ( q2.hasSolution() ? "true" : "false" )+ "\n");
		
		Variable Y = new Variable("Y");
		Query q3 = new Query("child_of", new Term[] {new Atom("joe"),Y});
		System.out.println( "first solution of child_of(joe, Y)"); 
		System.out.println(q3.oneSolution()+"\n");
		
		Variable X = new Variable("X");
		Query q4 = new Query( "descendent_of", new Term[] {X, new Atom("ralf")});
		java.util.Map<String,Term> solution;
		solution = q4.oneSolution();
		System.out.println( "first solution of descendent_of(X, ralf)"); 
		System.out.println( "X = " + solution.get("X"));
    }
}
