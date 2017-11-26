package org.ulco;

abstract public class Shape extends GraphicsObject{

    public Shape(Point center) {
        this.m_origin = center;
    }

    public Shape(String json) {
        m_origin = Utils.parseCenter(json);
    }

    public boolean isClosed(Point pt, double distance) {
        return Math.sqrt((m_origin.getX() -  pt.getX()) * (m_origin.getX() -  pt.getX()) + ((m_origin.getY() -  pt.getY()) * (m_origin.getY() -  pt.getY()))) <= distance;
    }

    public boolean isObject(){
        return true;
    }

    public Point getOrigin() { return m_origin; }

    protected void move(Point delta) { m_origin.move(delta); }

    protected final Point m_origin;
}
