package org.ulco;

import java.util.Vector;

/**
 * Created by cingouf on 20/11/17.
 */
public class Util
{
    public static GraphicsObjects select(Point pt, double distance, Layer layer) {
        GraphicsObjects list = new GraphicsObjects();
        Vector<GraphicsObject> go_list = layer.getObjectList();
        for (GraphicsObject object : go_list) {
            if (object.isClosed(pt, distance)) {
                list.add(object);
            }
        }
        return list;
    }

    public static GraphicsObjects select(Point pt, double distance, Document document) {
        GraphicsObjects list = new GraphicsObjects();
        Vector<Layer> layer_list = document.getLayers();
        for (Layer layer : layer_list) {
            list.addAll(Util.select(pt, distance, layer));
        }
        return list;
    }
}
