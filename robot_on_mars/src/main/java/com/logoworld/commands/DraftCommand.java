package com.logoworld.commands;

import com.logoworld.environment.*;
import com.logoworld.exceptions.*;

/**
 * Base class for all commands
 */
public interface DraftCommand
{
    /**
     * Args getting, preparing and checking.
     * @param value options, that specific for every command
     * @throws BadValue if no values responsibility
     */
    void getValue(String value) throws BadValue;

    /**
     * Some specific method for each command.
     * @param field where
     * @param robot who
     * @throws NotInitSurface problems with {@code field} or {@code robot} initialization
     * @throws BadCoordinates uncorrected {@code robot} position on {@code field}
     */
    void action(Field field, Robot robot) throws NotInitSurface, BadCoordinates;

    /**
     * This meth is running like {@code getValue(String) + action(Field, Robot)}
     */
    void action(Field field, Robot robot, String value) throws BadValue, NotInitSurface, BadCoordinates;

    /**
     * Meth for future operations with command. Coping from one to another.
     * @param draftCommand what to copy
     * @throws CloneNotSupportedException if coping impossible
     * @throws BadValue if value from other and this object incomparable
     */
    void clone(DraftCommand draftCommand) throws CloneNotSupportedException, BadValue;
}
