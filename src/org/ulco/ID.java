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
        else
            instance.id++;
        return instance;
    }

    public int getID()
    {
        return id;
    }
}