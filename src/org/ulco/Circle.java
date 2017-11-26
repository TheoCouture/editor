package org.ulco;

public class Circle extends Shape {
    public Circle(Point center, double radius) {
        super(center);
        this.m_radius = radius;
    }

    public Circle(String json) {
        super(json);
        String str = json.replaceAll("\\s+", "");
        int radiusIndex = str.indexOf("radius");
        int endIndex = str.lastIndexOf("}");

        m_radius = Double.parseDouble(str.substring(radiusIndex + 7, endIndex));
    }

    public GraphicsObject copy() {
        return new Circle(m_origin.copy(), m_radius);
    }

    public Point getCenter() { return m_origin; }


    public String toJson() {
        return "{ type: circle, center: " + m_origin.toJson() + ", radius: " + this.m_radius + " }";
    }

    public String toString() {
        return "circle[" + m_origin.toString() + "," + m_radius + "]";
    }

    private final double m_radius;
}
