package org.ulco;

import sun.plugin.dom.css.Rect;

public class Square extends Rectangle {
    public Square(Point center, double length) {
        super(center,length,length);
    }

    public Square(String json) {
        super(json);
    }

    public GraphicsObject copy() {
        return new Square(m_origin.copy(), m_height);
    }

    public String toJson() {
        return "{ type: square, center: " + m_origin.toJson() + ", length: " + this.m_height + " }";
    }

    public String toString() {
        return "square[" + m_origin.toString() + "," + m_height + "]";
    }
}
