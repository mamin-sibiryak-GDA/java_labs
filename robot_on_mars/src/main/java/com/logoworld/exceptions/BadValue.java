package com.logoworld.exceptions;

public class BadValue extends Exception
{
    public BadValue(String commandName)
    {
        super(commandName);
    }
}
