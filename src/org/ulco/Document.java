package org.ulco;

import java.util.Iterator;
import java.util.Vector;

public class Document {
    public Document() {
        m_layers = new Vector<>();
    }

    public Document(String json) {
        m_layers = new Vector<>();
        m_layers.addAll(0,Utils.parseLayers(json));

    }


    public Layer createLayer() {
        Layer layer = new Layer();

        m_layers.add(layer);
        return layer;
    }

    public int getLayerNumber() {
        return m_layers.size();
    }

    public int getObjectNumber() {
        int size = 0;

        for (int i = 0; i < m_layers.size(); ++i) {
            size += m_layers.elementAt(i).size();
        }
        return size;
    }

    public Vector<GraphicsObject> getM_layers() {
        return m_layers;
    }


    public String toJson() {
        String str = "{ type: document, layers: { ";

        for (int i = 0; i < m_layers.size(); ++i) {
            GraphicsObject element = m_layers.elementAt(i);

            str += element.toJson();
            if (i < m_layers.size() - 1) {
                str += ", ";
            }
        }
        return str + " } }";
    }

    private Vector<GraphicsObject> m_layers;
}
