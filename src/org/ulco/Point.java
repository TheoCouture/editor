package org.ulco;

public class Point {
    public Point(double x, double y) {
        m_x = x;
        m_y = y;
    }

    public Point(String json) {
        m_x = Utils.parsePointX(json);
        m_y = Utils.parsePointY(json);
    }

    public Point copy() {
        return new Point(m_x, m_y);
    }

    public double getX() {
        return m_x;
    }

    public double getY() {
        return m_y;
    }

    void move(Point delta)
    {
        m_x += delta.getX();
        m_y += delta.getY();
    }

    public String toString() {
        return "point[" + m_x + "," + m_y + "]";
    }

    private double m_x;
    private double m_y;
}
