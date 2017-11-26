package org.ulco;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Vector;

public class JSON {

    /*static public GraphicsObject parseGraphicObject(String json){

        GraphicsObject o = null;
        String str = json.replaceAll("\\s+", "");
        String type = str.substring(str.indexOf("type") + 5, str.indexOf(","));
        type = type.substring(0, 1).toUpperCase() + type.substring(1);

        try
        {

            Class<?> classe = Class.forName ("org.ulco." + type);
            Constructor<?> constructeur = classe.getConstructor(String.class);

            constructeur.

            if (classe.getClass(). instanceof Shape)
            {

            }
            o = (GraphicsObject) constructeur.newInstance (str);
        }
        catch (ClassNotFoundException e)
        {
            // La classe n'existe pas
        }
        catch (NoSuchMethodException e)
        {
            // La classe n'a pas le constructeur recherché
        }
        catch (InstantiationException e)
        {
            // La classe est abstract ou est une interface
        }
        catch (IllegalAccessException e)
        {
            // La classe n'est pas accessible
        }
        catch (java.lang.reflect.InvocationTargetException e)
        {
            // Exception déclenchée si le constructeur invoqué
            // a lui-même déclenché une exception
        }
        catch (IllegalArgumentException e)
        {
            // Mauvais type de paramètre
            // (Pas obligatoire d'intercepter IllegalArgumentException)
        }

        return o;

    }*/
    static public GraphicsObject parse(String json) {
        GraphicsObject o = null;
        String str = json.replaceAll("\\s+", "");
        String type = str.substring(str.indexOf("type") + 5, str.indexOf(","));
        type = type.substring(0, 1).toUpperCase() + type.substring(1);

        try
        {

            Class<?> classe = Class.forName ("org.ulco." + type);
            Constructor<?> constructeur = classe.getConstructor(String.class);
             o = (GraphicsObject) constructeur.newInstance (str);
        }
        catch (ClassNotFoundException e)
        {
            // La classe n'existe pas
        }
        catch (NoSuchMethodException e)
        {
            // La classe n'a pas le constructeur recherché
        }
        catch (InstantiationException e)
        {
            // La classe est abstract ou est une interface
        }
        catch (IllegalAccessException e)
        {
            // La classe n'est pas accessible
        }
        catch (java.lang.reflect.InvocationTargetException e)
        {
            // Exception déclenchée si le constructeur invoqué
            // a lui-même déclenché une exception
        }
        catch (IllegalArgumentException e)
        {
            // Mauvais type de paramètre
            // (Pas obligatoire d'intercepter IllegalArgumentException)
        }

        return o;
    }

    static public String PointToJSON( Point pt){
        return "{ type: point, x: " + pt.getX() + ", y: " + pt.getY() + " }";
    }

    static public String GraphicToJSON(GraphicsObject o) {


        String str = "{ type: "+  o.getClass().getSimpleName().toLowerCase() + ",";

        if (o instanceof Contener){
            Vector<Vector<GraphicsObject>> list = ((Contener) o).getList();
            Vector<GraphicsObject> objects =  list.get(0);
            Vector<GraphicsObject> conteners =  list.get(1);

            str+= " objects : { ";

            for (int i=0; i< objects.size(); i++){
                str += JSON.GraphicToJSON(objects.get(i));
                if (i < objects.size()-1)
                    str+= ", ";
            }

            str+= " }, groups : { ";

            for (int i=0; i< conteners.size(); i++){
                str += JSON.GraphicToJSON(conteners.get(i));
                if (i < conteners.size()-1)
                    str+= ", ";
            }

            str += " }";
        }
        else
        {
            if (o instanceof Shape){
                str += " center: ";
                str += PointToJSON((Point) ((Shape) o).getOrigin());
                str += ", ";
                String attribut;
                String s_method;
                Method method= null;

                Field f[] = o.getClass().getDeclaredFields();
                for (int i = 0; i < f.length; i++) {

                    attribut=f[i].toGenericString();
                    s_method = attribut.substring(attribut.indexOf("_")+1, attribut.length());
                    str += s_method+ ": ";
                    s_method = s_method.substring(0, 1).toUpperCase() + s_method.substring(1);

                    try {
                        method = o.getClass().getMethod("get" + s_method);
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    }

                    try {
                        str += method.invoke(o);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }

                    if (i < f.length -1){
                        str+= ", ";
                    }

                }

            }
        }

        str+= " }";

        return str;
    }

    static public Document parseDocument(String json) {
        return new Document(json);
    }

    static public String DocumentToJson(Document doc) {
        String str = "{ type: document, layers: { ";

        Vector<GraphicsObject> layers = doc.getM_layers();

        for (int i=0; i< layers.size(); i++){
            str += JSON.GraphicToJSON(layers.get(i));
            if (i < layers.size()-1)
                str += ", ";
        }

        str += " } }";

        return str;

    }
}
