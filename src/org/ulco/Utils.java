package org.ulco;

import java.util.Vector;

public class Utils {

    static public int searchSeparator(String str) {
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

    static public Vector<GraphicsObject> parse(String objectsStr) {
        Vector<GraphicsObject> m_list = new Vector<GraphicsObject>();
        while (!objectsStr.isEmpty()) {
            int separatorIndex = Utils.searchSeparator(objectsStr);
            String objectStr;

            if (separatorIndex == -1) {
                objectStr = objectsStr;
            } else {
                objectStr = objectsStr.substring(0, separatorIndex);
            }
            m_list.add(JSON.parse(objectStr));
            if (separatorIndex == -1) {
                objectsStr = "";
            } else {
                objectsStr = objectsStr.substring(separatorIndex + 1);
            }
        }

        return m_list;
    }

    static public String setJson(String json){
        return json.replaceAll("\\s+","");
    }


    static public Point parseCenter(String json){
        String str = setJson(json);
        int centerIndex = str.indexOf("center");
        int radiusIndexEnd = str.indexOf("},");

        return new Point(str.substring(centerIndex + 7, radiusIndexEnd + 1));
    }

    static public double parseRadius(String json){
        String str = setJson(json);
        int radiusIndex = str.indexOf("radius");
        int endIndex = str.lastIndexOf("}");

        return Double.parseDouble(str.substring(radiusIndex + 7, endIndex));
    }

    static public double parsePointX(String json){
        String str = setJson(json);
        int xIndex = str.indexOf("x");
        int separatorIndex = str.indexOf(",", xIndex + 2);

        return Double.parseDouble(str.substring(xIndex + 2, separatorIndex));
    }

    static public double parsePointY(String json){
        String str = setJson(json);
        int yIndex = str.lastIndexOf("y");
        int endIndex = str.lastIndexOf("}");

        return Double.parseDouble(str.substring(yIndex + 2, endIndex));
    }

    static public Vector<GraphicsObject> parseLayers(String json){
        String str = setJson(json);
        int layersIndex = str.indexOf("layers");
        int endIndex = str.lastIndexOf("}");

        return Utils.parse(str.substring(layersIndex + 8, endIndex));
    }

    static public double parseHeight(String json){
        String str = setJson(json);
        int heightIndex = str.indexOf("height");
        int heightIndexEnd = str.lastIndexOf(",");


        return  Double.parseDouble(str.substring( heightIndex + 7 , heightIndexEnd ));
    }

    static public double parseWidth(String json){

        String str = setJson(json);
        int widthIndex = str.indexOf("width");
        int endIndex = str.lastIndexOf("}");


        return Double.parseDouble(str.substring(widthIndex + 6, endIndex));
    }

    static public double parseLength(String json){
        String str = setJson(json);

        int lengthIndex = str.indexOf("length");
        int endIndex = str.lastIndexOf("}");

        return Double.parseDouble(str.substring(lengthIndex + 7, endIndex));
    }

    static public Vector<GraphicsObject> parseContener(String json){
        Vector<GraphicsObject> GraphicList = new Vector<>();
        String str = json.replaceAll("\\s+","");
        int objectsIndex = str.indexOf("objects");
        int groupsIndex = str.indexOf("groups");
        int endIndex = str.lastIndexOf("}");

        GraphicList.addAll(Utils.parse(str.substring(objectsIndex + 9, groupsIndex - 2)));
        GraphicList.addAll(Utils.parse(str.substring(groupsIndex + 8, endIndex - 1)));

        return  GraphicList;
    }



}
