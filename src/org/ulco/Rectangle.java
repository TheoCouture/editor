package org.ulco;

public class Rectangle extends Shape {
    public Rectangle(Point center, double height, double width) {
        super(center);
        this.m_height = height;
        this.m_width = width;
    }

    public Rectangle(String json) {
        super(json);


        if (this.getClass().getSimpleName().contains("Square")){
            m_height = Utils.parseLength(json);
            m_width = m_height;
        }else {
            m_height = Utils.parseHeight(json);
            m_width = Utils.parseWidth(json);
        }


    }

    public GraphicsObject copy() {
        return new Rectangle(m_origin.copy(), m_height, m_width);
    }


    public String toJson() {
        return "{ type: rectangle, center: " + m_origin.toJson() + ", height: " + this.m_height + ", width: " + this.m_width + " }";
    }

    public String toString() {
        return "rectangle[" + m_origin.toString() + "," + m_height + "," + m_width + "]";
    }

    protected final double m_height;
    private final double m_width;
}
