package org.ulco;

import java.lang.reflect.Constructor;

public class JSON {
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

    static public Group parseGroup(String json) {
        return new Group(json);
    }

    static public Layer parseLayer(String json) {
        return new Layer(json);
    }

    static public Document parseDocument(String json) {
        return new Document(json);
    }
}
