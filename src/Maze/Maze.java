package Maze;
import Main.FileManager;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Formatter;
import java.util.Random;

public class Maze {

    private char[][] maze;
    private int height;
    private int width;
    private int in;
    private int out;

    public Maze(String path, FileManager fm) {
        try {
        maze = fm.readData(path);
        } catch(IOException e) {
            System.out.println("Cannot read file!");
            System.exit(5);
        }

        if(maze == null) {
            System.err.println("Did not read correct maze.");
            System.exit(2);
        }

        height = maze.length;
        width = maze[0].length;

        int index = 0;
        while(maze[0][index++] != '#');
        in = (index-2)/2;

        index = 0;
        while(maze[height-1][index++] != '*');
        out = (index-2)/2 + ((height-1)/2 - 1) * (width-1)/2;
    }

    public Maze(int a, int b) {
        if(a <= 1 || b <= 1) {
            System.err.println("Wrong size! Both dimensions should be greater than 1!");
            System.exit(4);
        }

        height = 2*a + 1;
        width = 2*b + 1;

        maze = new char[height][width];
        initialize();
        generateInAndOut();
    }

    private void initialize() {
        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                if(i == 0 || j == 0 || i == height-1 || j == width-1)
                    maze[i][j] = '+';
                else
                    maze[i][j] = '0';
            }
        }
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public char getChar(int i, int j) {
        return maze[i][j];
    }

    public int getIn() {
        return in;
    }

    public int getOut() {
        return out;
    }

    public void generate() {
        generateMaze(1, width-2, 1, height-2, width > height);
    }

    private void generateMaze(int xFrom, int xTo, int yFrom, int yTo, boolean isVertical) { // vertical = pionowy
        if(((yTo-yFrom) <= 0) || ((xTo-xFrom) <= 0))
            return;

        Random rand = new Random();
        int wallPos;
        int holePos;
        if(isVertical) {
            wallPos = (rand.nextInt((xTo - xFrom) / 2)) * 2 + xFrom + 1;
            holePos = (rand.nextInt((yTo - yFrom) / 2)) * 2 + yFrom;
            drawWall(wallPos, holePos, yFrom, yTo, true);
            // left
            generateMaze(xFrom, wallPos-1, yFrom, yTo, ((wallPos-1-xFrom) == (yTo-yFrom) ? rand.nextBoolean() : (wallPos-1-xFrom) > (yTo-yFrom)));
            // right
            generateMaze(wallPos+1, xTo, yFrom, yTo, ((wallPos-1-xFrom) == (yTo-yFrom) ? rand.nextBoolean() : (wallPos-1-xFrom) > (yTo-yFrom)));
        }
        else {
            wallPos = (rand.nextInt((yTo - yFrom) / 2)) * 2 + yFrom + 1;
            holePos = (rand.nextInt((xTo - xFrom) / 2)) * 2 + xFrom;
            drawWall(wallPos, holePos, xFrom, xTo, false);
            // up
            generateMaze(xFrom, xTo, yFrom, wallPos-1, ((xTo-xFrom) == (wallPos-1-yFrom) ? rand.nextBoolean() : (xTo-xFrom) > (wallPos-1-yFrom)));
            // down
            generateMaze(xFrom, xTo, wallPos+1, yTo, ((xTo-xFrom) == (wallPos-1-yFrom) ? rand.nextBoolean() : (xTo-xFrom) > (wallPos-1-yFrom)));
        }
    }

    private void generateInAndOut() {
        Random rand = new Random();
        int inPos = (rand.nextInt((width-1)/2) * 2) + 1;
        int outPos = (rand.nextInt((width-1)/2) * 2) + 1;

        in = (inPos-1)/2;
        out = (outPos-1)/2 + ((height-1)/2-1) * (width-1)/2;

        maze[0][inPos] = '#';
        maze[height-1][outPos] = '*';
    }

    private void drawWall(int pos, int hole, int from, int to, boolean isVertical) {
        if(isVertical) {
            for(int i = from; i <= to; i++)
                if(i != hole)
                    maze[i][pos] = '+';
        }
        else {
            for(int i = from; i <= to; i++)
                if(i != hole)
                    maze[pos][i] = '+';
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                if(maze[i][j] == '0')
                    sb.append("\u001B[32m" + "0" + "\u001B[0m");
                else if(maze[i][j] == '+')
                    sb.append("\u001B[31m" + "+" + "\u001B[0m");
                else if(maze[i][j] == '#' || maze[i][j] == '*')
                    sb.append("\u001B[34m" + maze[i][j] + "\u001B[0m");

                else
                    sb.append(maze[i][j]);
            }
            sb.append('\n');
        }
        sb.append("in: " + in + ", out: " + out);

        return sb.toString();
    }
}
