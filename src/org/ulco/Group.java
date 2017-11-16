package org.ulco;

import java.util.Vector;

public class Group extends GraphicsObject {

    public Group() {
        //m_groupList = new  Vector<Group>();
        m_objectList = new Vector<GraphicsObject>();
        m_ID = ID.getInstance().GetNumber();
    }

    public Group(String json) {
        //m_groupList = new  Vector<Group>();
        m_objectList = new Vector<GraphicsObject>();
        String str = json.replaceAll("\\s+","");
        int objectsIndex = str.indexOf("objects");
        int groupsIndex = str.indexOf("groups");
        int endIndex = str.lastIndexOf("}");

        parseObjects(str.substring(objectsIndex + 9, groupsIndex - 2));
        parseGroups(str.substring(groupsIndex + 8, endIndex - 1));
    }

    protected boolean isObject(){
        return false;
    }

    public void add(Object object) {
        /*if (object instanceof Group) {
            //addGroup((Group)object);
        } else {
            addObject((GraphicsObject)object);
        }*/

        m_objectList.add((GraphicsObject)object);
    }

    public boolean isClosed(Point pt, double distance) {

        int i = 0;
        boolean close;

        do {
            GraphicsObject element = m_objectList.elementAt(i);

            close = element.isClosed(pt,distance);

            i++;
        }while(close != true && i!= m_objectList.size());

        return close;
    }


    public Group copy() {
        Group g = new Group();

        for (Object o : m_objectList) {
            GraphicsObject element = (GraphicsObject) (o);

            g.add(element.copy());
        }
        return g;
    }

    public int getID() {
        return m_ID;
    }

    public void move(Point delta) {
        Group g = new Group();

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

            GraphicsObject element = m_objectList.elementAt(i);
            size += element.size();
        }
        return size;
    }

    public String toJson() {
        String str = "{ type: group, objects : { ";

        for (int i = 0; i < m_objectList.size(); ++i) {
            GraphicsObject element = m_objectList.elementAt(i);

            if (element.isObject()) {
                str += element.toJson();
                if (i < m_objectList.size() - 1) {
                    str += ", ";
                }
            }
        }
        str += " }, groups : { ";

        for (int i = 0; i < m_objectList.size(); ++i) {


            GraphicsObject element = m_objectList.elementAt(i);

            if (!element.isObject())
                str += element.toJson();
        }

        return str + " } }";
    }

    public int getNumberOfObject(){
        int number= 0;
        for (int i = 0; i < m_objectList.size(); ++i)
        {
            GraphicsObject element = m_objectList.elementAt(i);
            if (element.isObject()) {
              number++;
            }
        }
        return number;
    }

    public String toString() {
        String str = "group[[";
        int nbmax = getNumberOfObject();
        int nbobject = 0;

        for (int i = 0; i < m_objectList.size(); ++i)
        {
            GraphicsObject element = m_objectList.elementAt(i);
            if (element.isObject()) {
                nbobject++;
                str += element.toString();
                if (nbobject < nbmax) {
                    str += ", ";
                }
            }
        }

        str += "],[";

        for (int i = 0; i < m_objectList.size(); ++i)
        {
            GraphicsObject element = m_objectList.elementAt(i);

            if (!element.isObject())
                str += element.toString();
        }

        return str + "]]";
    }

    //private Vector<Group> m_groupList;
    private Vector<GraphicsObject> m_objectList;
    private int m_ID;
}
