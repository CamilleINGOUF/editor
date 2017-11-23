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

    public static int searchSeparator(String str) {
        int index = 0;
        int level = 0;
        boolean found = false;

        while (!found && index < str.length()) {
            if (str.charAt(index) == '{') {
                ++level;
                ++index;
            } else if (str.charAt(index) == '}') {
                --level;
                ++index;
            } else if (str.charAt(index) == ',' && level == 0) {
                found = true;
            } else {
                ++index;
            }
        }
        if (found) {
            return index;
        } else {
            return -1;
        }
    }

    public static GraphicsObjects parseGroups(String groupsStr) {
        GraphicsObjects gos = new GraphicsObjects();
        while (!groupsStr.isEmpty()) {
            int separatorIndex = Util.searchSeparator(groupsStr);
            String groupStr;

            if (separatorIndex == -1) {
                groupStr = groupsStr;
            } else {
                groupStr = groupsStr.substring(0, separatorIndex);
            }
            gos.add(JSON.parse(groupStr));
            if (separatorIndex == -1) {
                groupsStr = "";
            } else {
                groupsStr = groupsStr.substring(separatorIndex + 1);
            }
        }
        return gos;
    }

    public static GraphicsObjects  parseObjects(String objectsStr) {
        GraphicsObjects gos = new GraphicsObjects();
        while (!objectsStr.isEmpty()) {
            int separatorIndex = Util.searchSeparator(objectsStr);
            String objectStr;

            if (separatorIndex == -1) {
                objectStr = objectsStr;
            } else {
                objectStr = objectsStr.substring(0, separatorIndex);
            }
            gos.add(JSON.parse(objectStr));
            if (separatorIndex == -1) {
                objectsStr = "";
            } else {
                objectsStr = objectsStr.substring(separatorIndex + 1);
            }
        }
        return gos;
    }
}
