package org.ulco;

import java.lang.reflect.Constructor;
import java.util.Vector;

public class JSON {
    static public GraphicsObject parse(String json) {
        GraphicsObject o = null;
        String str = json.replaceAll("\\s+", "");
        String type = str.substring(str.indexOf("type") + 5, str.indexOf(","));

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

    static public Layer parseLayer(String json) {
        return new Layer(json);
    }

    static public Document parseDocument(String json) {
        return new Document(json);
    }

    static public String toJSON(IObjectsContainer ioc)
    {
        String type = ioc.getClass().getSimpleName().toLowerCase();
        String str = "{ type: "+type+", objects : { ";

        Vector<GraphicsObject> objects = ioc.getObjects();

        for (int i = 0; i < objects.size(); ++i) {
            GraphicsObject element = objects.elementAt(i);
            if(element.isSimple()) {
                str += element.toJson();
                if (i < ioc.sizeSimpleObjects() - 1) {
                    str += ", ";
                }
            }
        }
        str += " }, groups : { ";

        for (int i = 0; i < objects.size(); ++i) {
            GraphicsObject element = objects.elementAt(i);
            if(!element.isSimple()) {
                str += JSON.toJSON((Group)element);
            }
        }
        return str + " } }";
    }
}
