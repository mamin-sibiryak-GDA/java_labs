package com.logoworld.exceptions;

public class BadCommand extends Exception
{
    public BadCommand(String commandName)
    {
        super("Manager got bad command :: " + commandName);
    }
}
