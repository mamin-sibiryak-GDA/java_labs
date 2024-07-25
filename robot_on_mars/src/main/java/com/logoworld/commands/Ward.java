package com.logoworld.commands;

import com.logoworld.environment.Robot;
import com.logoworld.environment.Field;
import com.logoworld.exceptions.BadValue;
import com.logoworld.exceptions.NotInitSurface;

import java.util.logging.Logger;

/**
 * This is the command unsets draw mode on robot.
 */
public class Ward implements DraftCommand
{
    private static final Logger log = Logger.getLogger(Ward.class.getName());

    /**
     * {@code getValue} method sets values for the next action.
     * @param value should be {@code null}, else it would be exception
     * @throws BadValue
     */
    @Override
    public void getValue(String value) throws BadValue
    {
        if (value != null)
        {
            log.info("Value getting: not null");
            throw new BadValue("WARD");
        }

        log.info("Value getting: good");
    }

    /**
     * {@code action} method takes args, that was prepared in getValue. So robot starts no draw after that.
     * @param field where you are planning to run
     * @param robot who starting no drawing
     * @throws NotInitSurface
     */
    @Override
    public void action(Field field, Robot robot) throws NotInitSurface
    {
        log.info("Taking action");

        if (field == null || !field.isInit())
        {
            log.info("No Init: null field");
            throw new NotInitSurface("null field", "WARD");
        }
        else if (robot == null)
        {
            log.info("No Init: null robot");
            throw new NotInitSurface("null robot", "WARD");
        }

        robot.setDrawerCondition(false);
        log.info("Ward mode set up finished");
    }

    /**
     * {@code getValue() + action()}
     */
    @Override
    public void action(Field field, Robot robot, String value) throws BadValue, NotInitSurface
    {
        getValue(value);
        action(field, robot);
    }

    /**
     * Function for coping Ward classes
     * @param draftCommand what to copy
     * @throws CloneNotSupportedException what if copy impossible
     * @throws BadValue if comparable value types
     */
    @Override
    public void clone(DraftCommand draftCommand) throws CloneNotSupportedException
    {
        if (draftCommand.getClass() == this.getClass())
            return;
        else
            throw new CloneNotSupportedException();
    }
}
