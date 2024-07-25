package com.logoworld.commands;

import com.logoworld.environment.Robot;
import com.logoworld.environment.Field;
import com.logoworld.exceptions.BadCoordinates;
import com.logoworld.exceptions.BadValue;
import com.logoworld.exceptions.NotInitSurface;

import java.util.logging.Logger;

/**
 * Move to up, down, left, right on some count of steps
 */
public class Move implements DraftCommand
{
    private char way;
    private int steps;
    public String value;
    private static final Logger log = Logger.getLogger(Move.class.getName());

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
     * @throws BadValue if values not contains 4 not negative integers
     */
    @Override
    public void getValue(String value) throws BadValue
    {
        if (value == null || !value.matches("^\\w+\\s+0$") && !value.matches("^\\w+\\s+[1-9]\\d*$"))
        {
            log.info("Bad value: " + value);
            throw new BadValue("MOVE");
        }

        this.value = value;
        String[] arr = value.split("\\s+");

        switch (arr[0])
        {
            case "L":
                log.info("Direction: left");
                way = 'L';
                break;
            case "R":
                log.info("Direction: right");
                way = 'R';
                break;
            case "U":
                log.info("Direction: left");
                way = 'U';
                break;
            case "D":
                log.info("Direction: down");
                way = 'D';
                break;
            default:
                log.info("Direction: unknown");
                throw new BadValue("MOVE :: bad symbol of direction");
        }

        log.info("Taking steps count ...");
        steps = Integer.parseInt(arr[1]);

        if (steps < 0)
        {
            log.info("Bad count of steps");
            throw new BadValue("MOVE :: negative count of steps");
        }

        log.info("Move direction and count of steps successfully got");
    }

    /**
     * Replacement of robot in some direction and some steps in that direction
     * @param field where
     * @param robot who
     * @throws BadCoordinates if values string had uncorrected arguments
     * @throws NotInitSurface some exception in way of environment initializations
     */
    @Override
    public void action(Field field, Robot robot) throws NotInitSurface, BadCoordinates
    {
        if (field == null || !field.isInit())
        {
            log.info("No Init: null field");
            throw new NotInitSurface("null field", "MOVE");
        }
        else if (robot == null)
        {
            log.info("No Init: null robot");
            throw new NotInitSurface("null robot", "MOVE");
        }

        for (int i = 0; i < steps; ++i)
        {
            field.hideRobot(robot);
            log.info("Number of the step: " + i);

            switch (way)
            {
                case 'L':
                    log.info("Step left");
                    robot.moveLeft();
                    break;
                case 'R':
                    log.info("Step right");
                    robot.moveRight();
                    break;
                case 'U':
                    log.info("Step up");
                    robot.moveUp();
                    break;
                case 'D':
                    log.info("Step down");
                    robot.moveDown();
                    break;
            }

            if (!field.displayRobot(robot))
            {
                log.info("Null surface or bad coordinates of surface");
                throw new NotInitSurface("null field", "MOVE");
            }

            try
            {
                Thread.sleep(100);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }

        log.info("MOVE successfully complete");
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
     * Function for coping Move classes
     * @param draftCommand what to copy
     * @throws CloneNotSupportedException what if copy impossible
     * @throws BadValue if comparable value types
     */
    @Override
    public void clone(DraftCommand draftCommand) throws CloneNotSupportedException, BadValue
    {
        if (draftCommand.getClass() == this.getClass())
        {
            this.value = ((Move) draftCommand).value;
            getValue(this.value);
        }
        else
            throw new CloneNotSupportedException();
    }
}
