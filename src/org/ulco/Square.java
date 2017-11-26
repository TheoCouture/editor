package org.ulco;

import sun.plugin.dom.css.Rect;

public class Square extends Rectangle {
    public Square(Point center, double length) {
        super(center,length,length);
        m_length = length;
    }

    public Square(String json) {
        super(json);
        m_length = m_height;
    }

    public GraphicsObject copy() {
        return new Square(m_center.copy(), m_height);
    }

    public double getLength() {
        return  m_length;
    }

    public String toJson() {
        return "{ type: square, center: " + m_center.toJson() + ", length: " + this.m_height + " }";
    }

    public String toString() {
        return "square[" + m_center.toString() + "," + m_height + "]";
    }

    private final double m_length;
}
