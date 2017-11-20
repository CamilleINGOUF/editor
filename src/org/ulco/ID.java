package org.ulco;

public class ID {
    private int id;

    private static ID instance;

    private ID()
    {
        id = 0;
    }

    public static ID getInstance()
    {
        if(instance == null)
            instance = new ID();
        return instance;
    }

    public int getID()
    {
        return id++;
    }
}