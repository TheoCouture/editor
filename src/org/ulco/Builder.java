package org.ulco;

import java.util.Vector;

public class Builder {


    public Document SpecificDocument(Point origin, int line, int column, double length) {

        Document specialdocument = new Document();

        Layer layer = specialdocument.createLayer();

        for (int indexX = 0; indexX < column; ++indexX) {
            for (int indexY = 0; indexY < line; ++indexY) {
                layer.add(new Square(new Point(origin.getX() + indexX * length, origin.getY() + indexY * length), length));
            }
        }

        return specialdocument;
    }

    public Document SpecificDocument(Point center, int number, double radius, double delta) {

        Document specialdocument = new Document();

        Layer layer = specialdocument.createLayer();

        for (int index = 0; index < number; ++index) {
            layer.add(new Circle(center, radius + index * delta));
        }

        return specialdocument;
    }
}
