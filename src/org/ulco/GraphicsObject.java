package org.ulco;

abstract public class GraphicsObject {
    public GraphicsObject() {
        m_ID = ID.getInstance().getID();
    }

    public GraphicsObject(Point center, double height, double width) {
        m_ID = ID.getInstance().getID();
        this.m_origin = center;
        this.m_height = height;
        this.m_width = width;
    }

    abstract public GraphicsObject copy();

    public int getID() {
        return m_ID;
    }

    public double getHeight()
    {
        return m_height;
    }

    public double getWidth()
    {
        return m_width;
    }

    abstract void move(Point delta);

    abstract public String toString();

    protected int m_ID;

    public Point getOrigin() { return m_origin; }

    public boolean isSimple()
    {
        return true;
    }

    public boolean isClosed(Point pt, double distance) {
        Point center = new Point(m_origin.getX() + m_width / 2, m_origin.getY() + m_height / 2);

        return Math.sqrt((center.getX() - pt.getX()) * (center.getX() - pt.getX()) +
                ((center.getY() - pt.getY()) * (center.getY() - pt.getY()))) <= distance;
    }

    public int size()
    {
        return 1;
    }

    protected Point m_origin;
    protected double m_height;
    protected double m_width;
}
