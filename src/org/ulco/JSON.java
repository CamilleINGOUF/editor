package org.ulco;

import java.lang.reflect.Constructor;

public class JSON {
    static public GraphicsObject parse(String json) {
        GraphicsObject o = null;
        String str = json.replaceAll("\\s+", "");
        String type = str.substring(str.indexOf("type") + 5, str.indexOf(","));
        /*if (type.compareTo("square") == 0) {
            o = new Square(str);
        } else if (type.compareTo("rectangle") == 0) {
            o = new Rectangle(str);
        } else if (type.compareTo("circle") == 0) {
            o = new Circle(str);
        }*/

        try {
            type = type.substring(0, 1).toUpperCase() + type.substring(1);
            type = "org.ulco."+type;


            Class resultClass =  Class.forName(type);
            Constructor constructor = resultClass.getDeclaredConstructor(String.class);
            o = (GraphicsObject) constructor.newInstance(str);
        } catch (Exception e) {

        }

        return o;
    }

    static public Group parseGroup(String json) {
        return new Group(json);
    }

    static public Layer parseLayer(String json) {
        return new Layer(json);
    }

    static public Document parseDocument(String json) {
        return new Document(json);
    }
}
