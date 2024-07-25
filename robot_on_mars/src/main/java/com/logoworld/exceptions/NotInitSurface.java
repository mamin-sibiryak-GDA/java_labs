package com.logoworld.exceptions;

public class NotInitSurface extends Exception
{
    public NotInitSurface(String commandName, String message)
    {
        super(commandName + " :: " + message);
    }
}

