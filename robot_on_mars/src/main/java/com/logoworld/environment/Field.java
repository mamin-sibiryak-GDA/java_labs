package com.logoworld.environment;

import com.logoworld.exceptions.BadCoordinates;

public class Field
{
    private int height = 0, width = 0;
    private DisplayedSurface surface = null;
    private boolean[][] coloredPoints = null;

    public boolean isInit()
    {
        return surface != null;
    }

    public void setDisplayedSurface(int width, int height, Robot robot) throws InterruptedException
    {
        surface = new DisplayedSurface(width, height, 50);
        robot.setLimits(width, height);
        coloredPoints = new boolean[height][width];
        initColoredPoints();
    }

    public boolean hideRobot(Robot robot) throws BadCoordinates
    {
        if (surface != null)
        {
            if (robot.getDrawerCondition())
                addColoredPoint(robot.getX(), robot.getY());

            if (robot.getDrawerCondition() || isColoredPoint(robot.getX(), robot.getY()))
                surface.setCell(robot.getX(), robot.getY(), CellType.COLOR);
            else
                surface.setCell(robot.getX(), robot.getY(), CellType.PURITY);

            return true;
        }
        else
            return false;
    }

    public boolean displayRobot(Robot robot)
    {
        if (surface != null)
        {
            if (robot.getDrawerCondition())
                addColoredPoint(robot.getX(), robot.getY());

            try
            {
                if (isColoredPoint(robot.getX(), robot.getY()))
                    surface.setCell(robot.getX(), robot.getY(), CellType.COLORED_ROBOT);
                else
                    surface.setCell(robot.getX(), robot.getY(), CellType.ROBOT);
            }
            catch (BadCoordinates e)
            {
                e.printStackTrace();
                return false;
            }

            return true;
        }
        else
            return false;
    }

    private void initColoredPoints()
    {
        for (int y = 0; y < height; ++y)
            for (int x = 0; x < width; ++x)
                coloredPoints[y][x] = false;
    }

    private void addColoredPoint(int x, int y)
    {
        coloredPoints[y][x] = true;
    }

    private boolean isColoredPoint(int x, int y)
    {
        return coloredPoints[y][x];
    }

}
