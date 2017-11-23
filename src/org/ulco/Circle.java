package org.ulco;

public class Circle extends GraphicsObject {
    public Circle(Point center, double radius) {
        super(center,radius,radius);
        this.m_radius = radius;
    }

    public Circle copy() {
        return new Circle(m_origin.copy(), m_height);
    }

    public boolean isClosed(Point pt, double distance) {
        return Math.sqrt((m_origin.getX() - pt.getX()) * (m_origin.getX() - pt.getX()) +
                ((m_origin.getY() - pt.getY()) * (m_origin.getY() - pt.getY()))) <= distance;
    }

    void move(Point delta) { m_origin.move(delta); }

    public double getRadius()
    {
        return m_radius;
    }

    public String toString() {
        return "circle[" + m_origin.toString() + "," + m_height + "]";
    }

    private double m_radius;
}
