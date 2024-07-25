package com.logoworld.environment;

public class Robot
{
    private int x, y, xLimit, yLimit;
    private boolean drawerCondition;
    private String iconPath;

    public boolean setCoordinates(int x, int y)
    {
        if (x < xLimit && y < yLimit)
        {
            this.x = x;
            this.y = y;
            return true;
        }
        else
            return false;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public void setLimits(int xLimit, int yLimit)
    {
        this.xLimit = xLimit;
        this.yLimit = yLimit;
    }

    public void setDrawerCondition(boolean drawerCondition)
    {
        this.drawerCondition = drawerCondition;
    }

    public boolean getDrawerCondition()
    {
        return drawerCondition;
    }

    public void moveRight()
    {
        x = (x + xLimit + 1) % xLimit;
    }

    public void moveLeft()
    {
        x = (x + xLimit - 1) % xLimit;
    }

    public void moveUp()
    {
        y = (y + yLimit - 1) % yLimit;
    }

    public void moveDown()
    {
        y = (y + yLimit + 1) % yLimit;
    }
}
