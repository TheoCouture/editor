package org.ulco;

import java.util.Vector;

import static org.ulco.Utils.parse;

abstract public class Contener extends GraphicsObject {

    public Contener() {
        super();
        m_list = new Vector<GraphicsObject>();

    }

    public Contener(String json) {
        m_list = Utils.parseContener(json);
    }

    public void move(Point delta) {
        for (Object o : m_list) {
            GraphicsObject element = (GraphicsObject) (o);

            element.move(delta);
        }
    }


    public boolean isClosed(Point pt, double distance) {

        int i = 0;
        boolean close;

        do {
            GraphicsObject element = m_list.elementAt(i);

            close = element.isClosed(pt,distance);

            i++;
        }while(close != true && i!= m_list.size());

        return close;
    }


    protected boolean isObject(){
        return false;
    }

    public void add(GraphicsObject o) {
        m_list.add(o);
    }

    public GraphicsObject get(int index) {
        return m_list.elementAt(index);
    }

    public int getNumberOfObject(){
        int number= 0;
        for (int i = 0; i < m_list.size(); ++i)
        {
            GraphicsObject element = m_list.elementAt(i);
            if (element.isObject()) {
                number++;
            }
        }
        return number;
    }

    public Group copy() {
        Group g = new Group();

        for (Object o : m_list) {
            GraphicsObject element = (GraphicsObject) (o);

            g.add(element.copy());
        }
        return g;
    }

    public Vector<GraphicsObject> getM_list() {
        return m_list;
    }


    public int size() {

        int size = 0;

        for (int i = 0; i < m_list.size(); ++i) {

            GraphicsObject element = m_list.elementAt(i);
            size += element.size();
        }
        return size;
    }

    public String toJson() {
        String str = "{ type: "+this.getClass().getSimpleName().toLowerCase()+", objects : { ";

        for (int i = 0; i < m_list.size(); ++i) {
            GraphicsObject element = m_list.elementAt(i);

            if (element.isObject()) {
                str += element.toJson();
                if (i < m_list.size() - 1) {
                    str += ", ";
                }
            }
        }
        str += " }, groups : { ";

        for (int i = 0; i < m_list.size(); ++i) {


            GraphicsObject element = m_list.elementAt(i);

            if (!element.isObject())
                str += element.toJson();
        }

        return str + " } }";
    }

    public Vector<Vector<GraphicsObject>> getList (){
        Vector<Vector<GraphicsObject>> list = new Vector<>();
        Vector<GraphicsObject> objects = new Vector<>();
        Vector<GraphicsObject> contener = new Vector<>();
        for( GraphicsObject o : m_list){
            if (o.isObject())
                objects.add(o);
            else
                contener.add(o);
        }

        list.add(objects);
        list.add(contener);
        return list;
    }

    public String toString() {


        String str = this.getClass().getSimpleName().toLowerCase()+"[[";
        int nbmax = getNumberOfObject();
        int nbobject = 0;

        for (int i = 0; i < m_list.size(); ++i)
        {
            GraphicsObject element = m_list.elementAt(i);
            if (element.isObject()) {
                nbobject++;
                str += element.toString();
                if (nbobject < nbmax) {
                    str += ", ";
                }
            }
        }

        str += "],[";

        for (int i = 0; i < m_list.size(); ++i)
        {
            GraphicsObject element = m_list.elementAt(i);

            if (!element.isObject())
                str += element.toString();
        }

        return str + "]]";
    }

    protected Vector<GraphicsObject> m_list;
}
