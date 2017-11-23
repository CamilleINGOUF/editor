package org.ulco;

public class Rectangle extends GraphicsObject {
    public Rectangle(Point center, double height, double width) {
        super(center,height,width);
    }

    public Rectangle copy() {
        return new Rectangle(m_origin.copy(), m_height, m_width);
    }

    void move(Point delta) { m_origin.move(delta); }

    public String toString() {
        return "rectangle[" + m_origin.toString() + "," + m_height + "," + m_width + "]";
    }
}
