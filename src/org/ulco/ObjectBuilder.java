package org.ulco;

import java.util.GregorianCalendar;
import java.util.Vector;

public class ObjectBuilder {
    public static Circle createCircle(String json)
    {
        String str = json.replaceAll("\\s+", "");
        int centerIndex = str.indexOf("center");
        int radiusIndex = str.indexOf("radius");
        int endIndex = str.lastIndexOf("}");

        Point m_origin = new Point(str.substring(centerIndex + 7, radiusIndex - 1));
        double m_radius = Double.parseDouble(str.substring(radiusIndex + 7, endIndex));

        return new Circle(m_origin,m_radius);
    }

    public static Rectangle createRectangle(String json)
    {
        String str = json.replaceAll("\\s+","");
        int centerIndex = str.indexOf("center");
        int heightIndex = str.indexOf("height");
        int widthIndex = str.indexOf("width");
        int endIndex = str.lastIndexOf("}");

        Point m_origin = new Point(str.substring(centerIndex + 7, heightIndex - 1));
        double m_height = Double.parseDouble(str.substring(heightIndex + 7, widthIndex - 1));
        double m_width = Double.parseDouble(str.substring(widthIndex + 6, endIndex));

        return new Rectangle(m_origin,m_height,m_width);
    }

    public static Square createSquare(String json)
    {
        String str = json.replaceAll("\\s+","");
        int centerIndex = str.indexOf("center");
        int lengthIndex = str.indexOf("length");
        int endIndex = str.lastIndexOf("}");

        Point  m_origin = new Point(str.substring(centerIndex + 7, lengthIndex - 1));
        double m_height = Double.parseDouble(str.substring(lengthIndex + 7, endIndex));

        return new Square(m_origin,m_height);
    }

    public static Group createGroup(String json)
    {
        Vector<GraphicsObject> m_objectList = new Vector<>();
        String str = json.replaceAll("\\s+","");
        int objectsIndex = str.indexOf("objects");
        int groupsIndex = str.indexOf("groups");
        int endIndex = str.lastIndexOf("}");

        m_objectList.addAll(Util.parseObjects(str.substring(objectsIndex + 9, groupsIndex - 2)));
        m_objectList.addAll(Util.parseGroups(str.substring(groupsIndex + 8, endIndex - 1)));

        Group g = new Group();
        for(GraphicsObject go : m_objectList)
            g.add(go);
        return g;
    }

    public static Layer createLayer(String json)
    {
        Vector<GraphicsObject> m_list= new Vector<>();
        String str = json.replaceAll("\\s+","");
        int objectsIndex = str.indexOf("objects");
        int groupsIndex = str.indexOf("groups");
        int endIndex = str.lastIndexOf("}");

        m_list.addAll(Util.parseObjects(str.substring(objectsIndex + 9, groupsIndex - 2)));
        m_list.addAll(Util.parseGroups(str.substring(groupsIndex + 8, endIndex - 1)));

        Layer l = new Layer();
        for (GraphicsObject go : m_list)
            l.add(go);
        return l;
    }

    public static Document createDocument(String json)
    {

        String str = json.replaceAll("\\s+", "");
        int layersIndex = str.indexOf("layers");
        int endIndex = str.lastIndexOf("}");

        Document document = new Document();
        document.parseLayers(str.substring(layersIndex + 8, endIndex));
        return document;
    }

}
