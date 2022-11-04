package com.objects.game;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Tanque extends Polygon {
    private static int[] coordX = {0, 66, 66, 36, 36, 38, 38, 28, 28, 30, 30, 0};
    private static int[] coordY = {65, 65, 25, 25, 10, 10, 0, 0, 10, 10, 25, 25};


    public Tanque() {
        super(coordX, coordY, 12);

    }


    public void rotar() {
        // Crea una lista de pares Point2D
        ArrayList<Point2D> list = new ArrayList<Point2D>();

        for(int j = 0; j < ypoints.length; j++){
            list.add(new Point2D.Double(xpoints[j], ypoints[j]));
        }

        // Nuevo array donde se guardaran los puntos rotados
        Point2D[] rotatedPoints = new Point2D[list.size()];

        // Rotamos los puntos 180 grados
        AffineTransform transform = AffineTransform.getRotateInstance(Math.toRadians(180),
                getBounds2D().getCenterX(), getBounds2D().getCenterY());
        transform.transform(list.toArray(new Point2D[0]), 0, rotatedPoints, 0, rotatedPoints.length);

        // actualizamos los puntos x e y, a partir de los pares Point2D rotados
        for(int j = 0; j < rotatedPoints.length; j++){
            xpoints[j] = (int)rotatedPoints[j].getX();
            ypoints[j] = (int)rotatedPoints[j].getY();
        }
    }

}
