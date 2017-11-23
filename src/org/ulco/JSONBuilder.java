package org.ulco;

public class JSONBuilder {
    public static String toJSON(GraphicsObject go) {
        if(go instanceof Square)
            return toJSON((Square)go);
        else if(go instanceof Circle)
            return toJSON((Circle)go);
        else if(go instanceof Rectangle)
            return toJSON((Rectangle) go);
        return JSON.toJSON((Group)go);
    }

    public static String toJSON(Circle circle)
    {
        return "{ type: circle, center: " + toJSON(circle.getOrigin()) + ", radius: " + circle.getRadius() + " }";
    }

    public static String toJSON(Rectangle rectangle) {
        return "{ type: rectangle, center: " + toJSON(rectangle.getOrigin()) + ", height: " + rectangle.getHeight() + ", width: " + rectangle.getWidth() + " }";
    }

    public static String toJSON(Square square) {
        return "{ type: square, center: " + toJSON(square.getOrigin()) + ", length: " + square.getHeight() + " }";
    }

    public static String toJSON(Point point) {
        return "{ type: point, x: " + point.getX() + ", y: " + point.getY() + " }";
    }

    public static String toJson(Document document) {
        String str = "{ type: document, layers: { ";

        for (int i = 0; i < document.getLayers().size(); ++i) {
            Layer element = document.getLayers().elementAt(i);

            str += JSON.toJSON(element);
            if (i < document.getLayers().size() - 1) {
                str += ", ";
            }
        }
        return str + " } }";
    }
}
