package org.ulco;

import java.util.Vector;

public class Layer implements IObjectsContainer {
    public Layer() {
        m_list = new Vector<>();
        m_ID = ID.getInstance().getID();
    }

    public void add(GraphicsObject o) {
        m_list.add(o);
    }

    public GraphicsObject get(int index) {
        return m_list.elementAt(index);
    }

    public int getObjectNumber() {
        return m_list.size();
    }

    public int getID() {
        return m_ID;
    }

    public Vector<GraphicsObject> getObjectList()
    {
        return m_list;
    }

    private Vector<GraphicsObject> m_list;
    private int m_ID;

    @Override
    public Vector<GraphicsObject> getObjects() {
        return m_list;
    }

    @Override
    public int sizeSimpleObjects() {
        int size = 0;

        for(GraphicsObject go : m_list) {
            if(go.isSimple()) {
                size++;
            }
        }

        return size;
    }
}
