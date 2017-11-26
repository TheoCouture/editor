package org.ulco;

public class Circle extends Shape {
    public Circle(Point center, double radius) {
        super(center);
        this.m_radius = radius;
    }

    public Circle(String json) {
        super(json);
        m_radius = Utils.parseRadius(json);
    }

    public GraphicsObject copy() {
        return new Circle(m_center.copy(), m_radius);
    }

    public Point getCenter() { return m_center; }


    public String toJson() {
        return "{ type: circle, center: " + m_center.toJson() + ", radius: " + this.m_radius + " }";
    }

    public double getRadius(){
        return m_radius;
    }

    public String toString() {
        return "circle[" + m_center.toString() + "," + m_radius + "]";
    }

    private final double m_radius;
}
