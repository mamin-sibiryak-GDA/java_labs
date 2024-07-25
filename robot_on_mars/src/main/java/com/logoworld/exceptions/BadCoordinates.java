package com.logoworld.exceptions;

public class BadCoordinates extends Exception
{
    public BadCoordinates(int x, int y, String commandName)
    {
        super(commandName + " :: bad coordinates: " + x + y);
    }
}
