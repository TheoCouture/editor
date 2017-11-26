package org.ulco;

public class Triangle extends Shape {

    public Triangle(Point center, double height, double base){
        super(center);
        this.m_height = height;
        this.m_base = base;
    }

    public Triangle(String json){
        super(json);
        this.m_height = Utils.parseHeight(json);
        this.m_base = Utils.parseBase(json);

    }

    public double getHeight() {
        return m_height;
    }

    public double getBase() {
        return m_base;
    }

    public GraphicsObject copy() {
        return new Triangle(m_center.copy(), m_height, m_base);
    }

    public String toString() {
        return "triangle[" + m_center.toString() + "," + m_height + "," + m_base + "]";
    }


    private final double m_height;
    private final double m_base;


}
