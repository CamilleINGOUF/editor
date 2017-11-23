package org.ulco.test;

import junit.framework.TestCase;
import org.ulco.GraphicsObject;
import org.ulco.JSONBuilder;
import org.ulco.Point;
import org.ulco.Square;

public class SquareTest extends TestCase {

    public void testType() throws Exception {
        Square s = new Square(new Point(0,0),10);

        assertTrue(s instanceof Square);
        assertTrue(s instanceof GraphicsObject);
    }

    public void testJson() throws Exception {
        Square s = new Square(new Point(0,0), 10);

        assertEquals(JSONBuilder.toJSON(s), "{ type: square, center: { type: point, x: 0.0, y: 0.0 }, length: 10.0 }");
    }

    public void testCopy() throws Exception {
        Square s = new Square(new Point(0,0), 10);

        assertEquals(JSONBuilder.toJSON(s), JSONBuilder.toJSON(s.copy()));
    }
}