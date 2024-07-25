package com.logoworld.environment;

import com.logoworld.exceptions.BadCoordinates;

import javax.swing.*;
import java.awt.*;

public class DisplayedSurface extends JFrame
{
    private JPanel panel;
    private final int height, width, cellSize;
    private CellType[][] data;

    public DisplayedSurface(int width, int height, int cellSize) throws InterruptedException
    {
        this.height = height;
        this.width = width;
        this.cellSize = cellSize;
        setImages();
        setData();
        initPanel();
        initFrame();
    }

    private void initPanel()
    {
        panel = new JPanel()
        {
            @Override
            protected void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                for (int y = 0; y < height; ++y)
                    for (int x = 0; x < width; ++x)
                        g.drawImage((Image) data[y][x].image, x * cellSize, y * cellSize, this);
            }
        };

        panel.setPreferredSize(new Dimension(width * cellSize, height * cellSize));
        add(panel);
    }

    private void initFrame()
    {
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Logo World");
        setIconImage(getImage("icon"));
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    private void setImages()
    {
        for (CellType cell : CellType.values())
            cell.image = getImage(cell.name());
    }

    private void setData()
    {
        data = new CellType[height][width];
        for (int y = 0; y < height; ++y)
            for (int x = 0; x < width; ++x)
                data[y][x] = CellType.PURITY;
    }

    public void setCell(int x, int y, CellType cell) throws BadCoordinates
    {
        if (y < height && x < width)
        {
            data[y][x] = cell;
            repaint();
        }
        else
            throw new BadCoordinates(x, y, "DisplayedSurface");
    }

    private Image getImage(String name)
    {
        String filename = "/images/" + name.toLowerCase() + ".png";
        System.out.println(System.getProperty("user.dir"));
        ImageIcon icon = new ImageIcon(getClass().getResource(filename));
        return icon.getImage();
    }
}
