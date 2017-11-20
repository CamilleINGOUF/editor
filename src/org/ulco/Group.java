package org.ulco;

import java.util.Vector;

public class Group extends GraphicsObject{

    public Group() {
        super();
        m_objectList = new Vector<GraphicsObject>();
    }

    public Group(String json) {
        super();
        m_objectList = new Vector<GraphicsObject>();
        String str = json.replaceAll("\\s+","");
        int objectsIndex = str.indexOf("objects");
        int groupsIndex = str.indexOf("groups");
        int endIndex = str.lastIndexOf("}");

        parseObjects(str.substring(objectsIndex + 9, groupsIndex - 2));
        parseGroups(str.substring(groupsIndex + 8, endIndex - 1));
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

    private int searchSeparator(String str) {
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

    private void parseGroups(String groupsStr) {
        while (!groupsStr.isEmpty()) {
            int separatorIndex = searchSeparator(groupsStr);
            String groupStr;

            if (separatorIndex == -1) {
                groupStr = groupsStr;
            } else {
                groupStr = groupsStr.substring(0, separatorIndex);
            }
            m_objectList.add(JSON.parseGroup(groupStr));
            if (separatorIndex == -1) {
                groupsStr = "";
            } else {
                groupsStr = groupsStr.substring(separatorIndex + 1);
            }
        }
    }

    private void parseObjects(String objectsStr) {
        while (!objectsStr.isEmpty()) {
            int separatorIndex = searchSeparator(objectsStr);
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
        Vector<GraphicsObject> objects = new Vector<GraphicsObject>();

        for(Object o : m_objectList)
        {
            if(!(o instanceof Group))
                objects.add((GraphicsObject)o);
        }

        return objects;
    }

    public String toJson() {
        String str = "{ type: group, objects : { ";

        for (int i = 0; i < getObjects().size(); ++i) {
            GraphicsObject element = getObjects().elementAt(i);
            if(element.isSimple()) {
                str += element.toJson();
                if (i < getObjects().size() - 1) {
                    str += ", ";
                }
            }
        }
        str += " }, groups : { ";

        for (int i = 0; i < m_objectList.size(); ++i) {
            GraphicsObject element = m_objectList.elementAt(i);
            if(!element.isSimple()) {
                str += element.toJson();
            }
        }
        return str + " } }";
    }

    public String toString() {
        String str = "group[[";

        for (int i = 0; i < m_objectList.size(); ++i) {
            GraphicsObject object = m_objectList.elementAt(i);
            if(object.isSimple()) {
                str += object.toString();
                if (i < getObjects().size() - 1) {
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
