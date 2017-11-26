package org.ulco;

public class Rectangle extends Shape {
    public Rectangle(Point center, double height, double width) {
        super(center);
        this.m_height = height;
        this.m_width = width;
    }

    public Rectangle(String json) {
        super(json);
        String str = json.replaceAll("\\s+","");
        int centerIndex = str.indexOf("center");
        int heightIndex = str.indexOf("height");
        int widthIndex = str.indexOf("width");
        int endIndex = str.lastIndexOf("}");


        if (this.getClass().getSimpleName().contains("Square")){
            heightIndex = str.indexOf("length");
            m_height = Double.parseDouble(str.substring(heightIndex + 7, endIndex));
            m_width = m_height;
        }else {
            m_height = Double.parseDouble(str.substring(heightIndex + 7, widthIndex - 1));
            m_width = Double.parseDouble(str.substring(widthIndex + 6, endIndex));

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
