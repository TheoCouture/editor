package org.ulco;

import java.util.Vector;

public class DataManagement {

    public GraphicsObjects select(Point pt, double distance, Document document) {
        GraphicsObjects list = new GraphicsObjects();
        Vector<Layer> LayerList = document.getM_layers();

        for (Layer layer : LayerList) {
            list.addAll(select(pt, distance, layer));
        }
        return list;
    }

    public GraphicsObjects select(Point pt, double distance, Layer layer) {
        GraphicsObjects list = new GraphicsObjects();
        Vector<GraphicsObject> ListObject = layer.getM_list();

        for (GraphicsObject object : ListObject) {
            if (object.isClosed(pt, distance)) {
                list.add(object);
            }
        }
        return list;
    }



}