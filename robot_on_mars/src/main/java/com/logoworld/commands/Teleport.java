package com.logoworld.commands;

import com.logoworld.environment.Robot;
import com.logoworld.environment.Field;
import com.logoworld.exceptions.BadCoordinates;
import com.logoworld.exceptions.BadValue;
import com.logoworld.exceptions.NotInitSurface;

import java.util.logging.Logger;

/**
 * This command sets robot (x, y) coordinates on field
 */
public class Teleport implements DraftCommand
{
    private int x;
    private int y;
    public String value = null;
    private static final Logger log = Logger.getLogger(Teleport.class.getName());

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
        if (value == null || !value.matches("^0\\s+0$")
                && !value.matches("^0\\s+[1-9]\\d*$")
                && !value.matches("^[1-9]\\d*\\s+[1-9]\\d*$")
                && !value.matches("^[1-9]\\d*\\s+0$"))
        {
            log.info("Bad value: " + value);
            throw new BadValue("TELEPORT");
        }

        String[] arr = value.split("\\s+");

        x = convert(arr[0]);
        y = convert(arr[1]);

        log.info("Args successfully got: (" + x + ", " + y + ")");
    }

    /**
     * Teleportation of robot in some concrete (x,y) point
     * @param field where
     * @param robot who
     * @throws BadCoordinates if values string had uncorrected arguments
     * @throws NotInitSurface some exception in way of environment initializations
     */
    @Override
    public void action(Field field, Robot robot) throws BadCoordinates, NotInitSurface
    {
        if (field == null || !field.isInit())
        {
            log.info("No Init: null field");
            throw new NotInitSurface("null field", "TELEPORT");
        }
        else if (robot == null)
        {
            log.info("No Init: null robot");
            throw new NotInitSurface("null robot", "TELEPORT");
        }

        log.info("Robot coordinates set: (" + x + ", " + y + ")");
        field.hideRobot(robot);

        if (!robot.setCoordinates(x, y))
        {
            log.info("Null surface or bad coordinates of surface");
            throw new BadCoordinates(robot.getX(), robot.getY(), "TELEPORT");
        }

        if (!field.displayRobot(robot))
        {
            log.info("Null surface or bad coordinates of surface");
            throw new NotInitSurface("null field", "TELEPORT");
        }

        log.info("Successful TELEPORT");
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
     * Function for coping Teleport classes
     * @param draftCommand what to copy
     * @throws CloneNotSupportedException what if copy impossible
     * @throws BadValue if comparable value types
     */
    @Override
    public void clone(DraftCommand draftCommand) throws CloneNotSupportedException, BadValue
    {
        if (draftCommand.getClass() == this.getClass())
        {
            this.value = ((Teleport) draftCommand).value;
            getValue(this.value);
        }
        else
            throw new CloneNotSupportedException();
    }
}
