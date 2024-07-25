package com.logoworld.commands;

import com.logoworld.environment.Field;
import com.logoworld.environment.Robot;
import com.logoworld.exceptions.BadCoordinates;
import com.logoworld.exceptions.BadValue;
import com.logoworld.exceptions.NotInitSurface;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InitTest
{
    Init obj;
    String value;

    @BeforeEach
    void setUp()
    {
        obj = new Init();
    }

    @Test
    @DisplayName("Testing of getParam method of INIT")
    void getParam()
    {
        value = null;
        try
        {
            obj.getValue(value);
            fail();
        }
        catch (BadValue e)
        {
            assertNotNull(e.getMessage());
        }

        value = "58 60 35 20";
        try
        {
            obj.getValue(value);
            assertNotNull("Good param");
        }
        catch (BadValue e)
        {
            fail(e.getMessage());
        }

        value = "0 0 0 0";
        try
        {
            obj.getValue(value);
            fail();
        }
        catch (BadValue e)
        {
            assertNotNull(e.getMessage());
        }

        value = "8  9 -1 6";
        try
        {
            obj.getValue(value);
            fail();
        }
        catch (BadValue e)
        {
            assertNotNull(e.getMessage());
        }

        value = "08 6 5 1";
        try
        {
            obj.getValue(value);
            fail();
        }
        catch (BadValue e)
        {
            assertNotNull(value);
        }

        value = "8 6 10 3";
        try
        {
            obj.getValue(value);
            fail();
        }
        catch (BadValue e)
        {
            assertNotNull(e.getMessage());
        }

        value = "        ";
        try
        {
            obj.getValue(value);
            fail();
        }
        catch (BadValue e)
        {
            assertNotNull(e.getMessage());
        }

    }

    @Test
    @DisplayName("Testing of action(Filed, Robot, String)")
    void action()
    {
        Field field = new Field();
        Robot robot = new Robot();

        value = "10 10 9 9";
        try
        {
            field.setDisplayedSurface(10, 10, robot);
            obj.action(field, robot, value);
            assertNotNull("Good param");
        }
        catch (BadCoordinates | BadValue | InterruptedException | NotInitSurface e)
        {
            fail(e.getMessage());
        }

        value = "85 85 0 0";
        try
        {
            obj.action(null, null, value);
            fail();
        }
        catch (BadCoordinates | BadValue e)
        {
            fail(e.getMessage());
        }
        catch (NotInitSurface e)
        {
            assertNotNull(e.getMessage());
        }

        try
        {
            field.setDisplayedSurface(10, 10, robot);
            obj.action(field, null, value);
            fail();
        }
        catch (BadCoordinates | BadValue | InterruptedException e)
        {
            fail(e.getMessage());
        }
        catch (NotInitSurface e)
        {
            assertNotNull(e.getMessage());
        }
    }
}
