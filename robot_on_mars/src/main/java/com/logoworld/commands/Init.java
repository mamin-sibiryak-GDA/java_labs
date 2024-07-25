package com.logoworld.commands;

import com.logoworld.environment.Robot;
import com.logoworld.environment.Field;
import com.logoworld.exceptions.BadCoordinates;
import com.logoworld.exceptions.BadValue;
import com.logoworld.exceptions.NotInitSurface;

import java.util.logging.Logger;

/**
 * Initialization of environment
 */
public class Init implements DraftCommand
{
    private int height;
    private int width;
    private int x;
    private int y;
    public String value = null;
    private static final Logger log = Logger.getLogger(Init.class.getName());

    /**
     * Checking of inputted  args: return {@code true} if values is correct, else {@code false}
     */
    private boolean checkArgs()
    {
        if (height <= 0)
        {
            log.info("Bad height arg");
            return false;
        }
        else if (width <= 0)
        {
            log.info("Bad width arg");
            return false;
        }
        else if (x > width || x < 0)
        {
            log.info("Bad x arg");
            return false;
        }
        else if (y > height || y < 0)
        {
            log.info("Bad y arg");
            return false;
        }

        return true;
    }

    /**
     * String to int conversation.
     * @param str argument of values
     * @return integer get from String str
     */
    private int convert(String str)
    {
        int tmp = 0;
        log.info("String = " + str);

        // Convert the String
        try
        {
            tmp = Integer.parseInt(str);
        }
        catch (NumberFormatException e)
        {
            // This is thrown when the String
            // contains characters other than digits
            log.info("Invalid String");
        }
        return tmp;
    }

    /**
     * Getting values for the field initialization: height and width of surface
     * and robot start position
     * @param value options, that specific for every command
     * @throws BadValue if value not contains 4 not negative integers
     */
    @Override
    public void getValue(String value) throws BadValue
    {
        if (value == null || !value.matches("^([1-9]\\d*|0)(\\s+\\d+|\\s+0){3}$"))
        {
            log.info("Bad value: " + value);
            throw new BadValue("INIT");
        }

        this.value = value;
        String[] arr = value.split("\\s+");

        width = convert(arr[0]);
        log.info("width = " + width);
        height = convert(arr[1]);
        log.info("height = " + height);
        x = convert(arr[2]);
        log.info("x = " + x);
        y = convert(arr[3]);
        log.info("y = " + y);

        if (!checkArgs())
        {
            log.info("Bad value: " + value);
            throw new BadValue("INIT");
        }

        log.info("Args successfully got");
    }

    /**
     * Surface initialization: setting of field size from got {@code height},
     * {@code width} and robot coordinates {@code x}, {@code y}
     * @param field where
     * @param robot who
     * @throws BadCoordinates if values string had uncorrected arguments
     * @throws NotInitSurface some exception in way of environment initializations
     */
    @Override
    public void action(Field field, Robot robot) throws BadCoordinates, NotInitSurface
    {
        if (field == null)
        {
            log.info("No Init: null field");
            throw new NotInitSurface("null field", "INIT");
        }
        else if (robot == null)
        {
            log.info("No Init: null robot");
            throw new NotInitSurface("null robot", "INIT");
        }

        log.info("Field setting ...");
        try
        {
            field.setDisplayedSurface(width, height, robot);
        }
        catch (InterruptedException e)
        {
            log.info("Field setting failed");
            e.printStackTrace();
        }

        log.info("Robot coordinates set: (" + x + ", " + y + ")");
        robot.setCoordinates(x, y);

        if (!field.displayRobot(robot))
        {
            log.info("Null surface or bad coordinates of surface");
            throw new NotInitSurface("null surface of Filed", "INIT");
        }

        log.info("Successful INIT");
    }

    /**
     * {@code getValue() + action()}
     */
    @Override
    public void action(Field field, Robot robot, String value) throws BadValue, NotInitSurface, BadCoordinates
    {
        getValue(value);
        action(field, robot);
    }

    /**
     * Function for coping Init classes
     * @param draftCommand what to copy
     * @throws CloneNotSupportedException what if copy impossible
     * @throws BadValue if comparable value types
     */
    @Override
    public void clone(DraftCommand draftCommand) throws CloneNotSupportedException, BadValue
    {
        if (draftCommand.getClass() == this.getClass())
        {
            this.value = ((Init) draftCommand).value;
            getValue(this.value);
        }
        else
            throw new CloneNotSupportedException();
    }
}
