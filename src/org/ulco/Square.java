package org.ulco;

public class Square extends GraphicsObject {
    public Square(Point center, double length) {
        super(center,length,length);
    }

    public Square copy() {
        return new Square(m_origin.copy(), m_height);
    }

    void move(Point delta) { m_origin.move(delta); }

    public String toString() {
        return "square[" + m_origin.toString() + "," + m_height + "]";
    }
}
