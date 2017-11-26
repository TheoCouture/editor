package org.ulco;

abstract public class Shape extends GraphicsObject{

    public Shape(Point center) {
        this.m_center = center;
    }

    public Shape(String json) {
        m_center = Utils.parseCenter(json);
    }

    public boolean isClosed(Point pt, double distance) {
        return Math.sqrt((m_center.getX() -  pt.getX()) * (m_center.getX() -  pt.getX()) + ((m_center.getY() -  pt.getY()) * (m_center.getY() -  pt.getY()))) <= distance;
    }

    public boolean isObject(){
        return true;
    }

    public Point getOrigin() { return m_center; }

    protected void move(Point delta) { m_center.move(delta); }

    protected final Point m_center;
}
