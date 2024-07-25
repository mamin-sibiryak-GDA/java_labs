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

class MoveTest
{
    Move obj;
    String value;

    @BeforeEach
    void setUp()
    {
        obj = new Move();
    }

    @Test
    @DisplayName("Testing of getParam method of MOVE cmd")
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

        value = "Some text there";
        try
        {
            obj.getValue(value);
            fail();
        }
        catch (BadValue e)
        {
            assertNotNull(e.getMessage());
        }

        value = "L 90";
        try
        {
            obj.getValue(value);
            assertNotNull("Good param");
        }
        catch (BadValue e)
        {
            fail(e.getMessage());
        }

        value = "R                100";
        try
        {
            obj.getValue(value);
            assertNotNull("Good param");
        }
        catch (BadValue e)
        {
            fail(e.getMessage());
        }

        value = "U 09";
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
    @DisplayName("Testing of action(Field, Robot, String)")
    void action()
    {
        Field field = new Field();
        Robot robot = new Robot();

        value = "L 90";
        try
        {
            field.setDisplayedSurface(10, 10, robot);
            obj.action(field, robot, value);
            assertNotNull("Good param");
        }
        catch (InterruptedException | NotInitSurface | BadCoordinates | BadValue e)
        {
            fail(e.getMessage());
        }

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
            field.setDisplayedSurface(10, 6, robot);
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

        value = "U 0";
        try
        {
            field.setDisplayedSurface(10, 10, robot);
            obj.action(field, robot, value);
            assertNotNull("Good param");
        }
        catch (InterruptedException | NotInitSurface | BadCoordinates | BadValue e)
        {
            fail(e.getMessage());
        }
    }
}
