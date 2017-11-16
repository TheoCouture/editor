package org.ulco;

public class ID {
    private int ID = 0;

    private static ID INSTANCE = null;

    public static ID getInstance (){
        if (INSTANCE == null){
            INSTANCE = new ID();
        }

        return INSTANCE;
    }

    public int GetNumber(){
        return ++ID;
    }

    public int GetLastNumber(){
        return ID;
    }
}