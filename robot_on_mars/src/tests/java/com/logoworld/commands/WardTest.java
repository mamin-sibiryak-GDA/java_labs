package com.logoworld.commands;

import com.logoworld.environment.Field;
import com.logoworld.environment.Robot;
import com.logoworld.exceptions.BadValue;
import com.logoworld.exceptions.NotInitSurface;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WardTest
{
    Ward obj;
    String value;

    @BeforeEach
    void setUp()
    {
        obj = new Ward();
    }

    @Test
    @DisplayName("Testing Ward with some arguments")
    public void notNullParam()
    {
        value = "Some test text";
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
    @DisplayName("Testing Ward with null Robot and Field")
    public void nullActionArgs()
    {
        try
        {
            obj.action(null, null);
            fail();
        }
        catch (NotInitSurface e)
        {
            assertNotNull(e.getMessage());
        }
    }

    @Test
    @DisplayName("Testing Ward with correct param")
    public void correctParam()
    {
        value = null;
        try
        {
            obj.getValue(value);
            Field field = new Field();
            Robot robot = new Robot();

            field.setDisplayedSurface(5, 5, robot);

            obj.action(field, robot, value);
            assertNotNull("Action made succefully");
        }
        catch (BadValue | NotInitSurface | InterruptedException e)
        {
            fail(e.getMessage());
        }
    }
}
