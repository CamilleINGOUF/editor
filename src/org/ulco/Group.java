package org.ulco;

import java.util.Vector;

public class Group extends GraphicsObject implements IObjectsContainer {

    public Group() {
        super();
        m_objectList = new Vector<>();
    }

    public void add(Object object) {
        addObject((GraphicsObject)object);
    }

    private void addObject(GraphicsObject object) {
        m_objectList.add(object);
    }

    public Group copy() {
        Group g = new Group();

        for (Object o : m_objectList) {
            GraphicsObject element = (GraphicsObject) (o);
            g.addObject(element.copy());
        }

        return g;
    }

    public int getID() {
        return m_ID;
    }

    @Override
    public boolean isClosed(Point pt, double distance) {
        for(GraphicsObject go : m_objectList) {
            if(go.isClosed(pt,distance)) {
                return true;
            }
        }
        return false;
    }

    public void move(Point delta) {
        for (Object o : m_objectList) {
            GraphicsObject element = (GraphicsObject) (o);

            element.move(delta);
        }
    }

    private void parseObjects(String objectsStr) {
        while (!objectsStr.isEmpty()) {
            int separatorIndex = Util.searchSeparator(objectsStr);
            String objectStr;

            if (separatorIndex == -1) {
                objectStr = objectsStr;
            } else {
                objectStr = objectsStr.substring(0, separatorIndex);
            }
            m_objectList.add(JSON.parse(objectStr));
            if (separatorIndex == -1) {
                objectsStr = "";
            } else {
                objectsStr = objectsStr.substring(separatorIndex + 1);
            }
        }
    }

    public int size() {
        int size = 0;

        for (int i = 0; i < m_objectList.size(); ++i) {
            size += m_objectList.elementAt(i).size();
        }

        return size;
    }

    public Vector<GraphicsObject> getObjects()
    {
        return m_objectList;
    }

    @Override
    public int sizeSimpleObjects() {
        int size = 0;

        for(GraphicsObject go : m_objectList) {
            if(go.isSimple()) {
                size++;
            }
        }

        return size;
    }

    public String toString() {
        String str = "group[[";

        for (int i = 0; i < m_objectList.size(); ++i) {
            GraphicsObject object = m_objectList.elementAt(i);
            if(object.isSimple()) {
                str += object.toString();
                if (i < sizeSimpleObjects() - 1) {
                    str += ", ";
                }
            }
        }
        str += "],[";

        for (int i = 0; i < m_objectList.size(); ++i) {
            GraphicsObject object = m_objectList.elementAt(i);
            if(!object.isSimple()) {
                Group element = (Group) object;

                str += element.toString();
            }
        }
        return str + "]]";
    }

    @Override
    public boolean isSimple()
    {
        return false;
    }

    private Vector<GraphicsObject> m_objectList;
}
