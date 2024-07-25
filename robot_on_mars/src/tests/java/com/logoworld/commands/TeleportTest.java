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


class TeleportTest
{
    Teleport obj;
    String value;

    @BeforeEach
    void setUp()
    {
        obj = new Teleport();
    }

    @Test
    @DisplayName("Testing getParam method with different")
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

        value = "90 9 9";
        try
        {
            obj.getValue(value);
            fail();
        }
        catch (BadValue e)
        {
            assertNotNull(e.getMessage());
        }

        value = "90 titatu";
        try
        {
            obj.getValue(value);
            fail();
        }
        catch (BadValue e)
        {
            assertNotNull(e.getMessage());
        }

        value = "Some text there.";
        try
        {
            obj.getValue(value);
            fail();
        }
        catch (BadValue e)
        {
            assertNotNull(e.getMessage());
        }

        value = "09 09";
        try
        {
            obj.getValue(value);
            fail();
        }
        catch (BadValue e)
        {
            assertNotNull(e.getMessage());
        }

        value = "90 90";
        try
        {
            obj.getValue(value);
            assertNotNull("Parameters got successfully");
        }
        catch (BadValue e)
        {
            fail(e.getMessage());
        }

        value = "90    90";
        try
        {
            obj.getValue(value);
            assertNotNull("Parameters got successfully");
        }
        catch (BadValue e)
        {
            fail(e.getMessage());
        }
    }

    @Test
    void action() throws NotInitSurface
    {
        Field field = new Field();
        Robot robot = new Robot();

        value = "90 90";
        try
        {
            field.setDisplayedSurface(10, 10, robot);
            obj.action(field, robot, value);
            fail();
        }
        catch (InterruptedException | BadValue | NotInitSurface e)
        {
            fail(e.getMessage());
        }
        catch (BadCoordinates e)
        {
            assertNotNull(e.getMessage());
        }

        value = "0 0";
        try
        {
            field.setDisplayedSurface(10, 10, robot);
            obj.action(field, robot, value);
            assertNotNull("Correct param");
        }
        catch (InterruptedException | BadCoordinates | BadValue | NotInitSurface e)
        {
            fail(e.getMessage());
        }

        value = "9 9";
        try
        {
            field.setDisplayedSurface(10, 10, robot);
            obj.action(null, null, value);
            fail();
        }
        catch (InterruptedException | BadCoordinates | BadValue e)
        {
            fail(e.getMessage());
        }
        catch (NotInitSurface e)
        {
            assertNotNull("Null field and robot");
        }

        value = "09 0 9";
        try
        {
            obj.action(null, null, value);
        }
        catch (BadCoordinates | NotInitSurface e)
        {
            fail(e.getMessage());
        }
        catch (BadValue e)
        {
            assertNotNull("Bad param test");
        }
    }
}
