package Graphs;

import Maze.Maze;

import java.util.ArrayList;
import java.util.Random;

public class TremauxGraph {

    private char[][] maze;
    private int start;
    private int exit;
    private Stack s;

    private final int UP = -1;
    private final int LEFT = -2;
    private final int DOWN = 1;
    private final int RIGHT = 2;

    public TremauxGraph(Maze m) {
        this.maze = new char[m.getHeight()][m.getWidth()];
        for(int i = 0; i < m.getHeight(); i++) {
            for(int j = 0; j < m.getWidth(); j++) {
                maze[i][j] = m.getChar(i, j);
            }
        }
        int i = 1;
        while(maze[0][i] != '#')
            i++;
        start = i;

        i = 1;
        while(maze[maze.length-1][i] != '*')
            i++;
        exit = i;

        s = new Stack();
    }

    public void search() {
        int x = start;
        int y = 1;
        int chosenPath;
        int direction;
        Random rand = new Random();

        long before = System.nanoTime();

        while(!(x == exit && y == maze.length-2)) {
            if(goodToGoPaths(x, y) > 0) {
                chosenPath = rand.nextInt(goodToGoPaths(x, y));
                direction = getPossibleDirections(y, x).get(chosenPath);
                s.push(direction);
            }
            else
                direction = -s.pop();

            switch(direction) {
                case UP:
                    maze[y-1][x]++;
                    y -= 2;
                    break;
                case LEFT:
                    maze[y][x-1]++;
                    x -= 2;
                    break;
                case DOWN:
                    maze[y+1][x]++;
                    y += 2;
                    break;
                case RIGHT:
                    maze[y][x+1]++;
                    x += 2;
                    break;
            }
        }

        long after = System.nanoTime();

        printPath(after - before);
    }

    private int goodToGoPaths(int i, int j) {
        int num = 0;

        if(maze[j-1][i] == '0')
            num++;
        if(maze[j][i-1] == '0')
            num++;
        if(maze[j+1][i] == '0')
            num++;
        if(maze[j][i+1] == '0')
            num++;

        return num;
    }

    private ArrayList<Integer> getPossibleDirections(int i, int j) {
        ArrayList<Integer> list = new ArrayList<>();
        if(maze[i-1][j] == '0')
            list.add(-1);
        if(maze[i][j-1] == '0')
            list.add(-2);
        if(maze[i+1][j] == '0')
            list.add(1);
        if(maze[i][j+1] == '0')
            list.add(2);

        return list;
    }

    private void printPath(long time) {
        int num = (start - 1) / 2 + 1;
        System.out.println("Path found with Trémaux algorithm is:");
        System.out.print("IN->" + num + "->");
        for(Integer i: s.stack) {
            switch(i) {
                case UP:
                    num -= (maze[0].length - 1) / 2;
                    break;
                case LEFT:
                    num--;
                    break;
                case RIGHT:
                    num++;
                    break;
                case DOWN:
                    num += (maze[0].length - 1) / 2;
                    break;
            }
            System.out.print(num + "->");
        }
        System.out.println("EXIT");

        System.out.println("It took " + time + " ns to find this path.");
    }

    class Stack {

        ArrayList<Integer> stack;

        Stack() {
            stack = new ArrayList<>();
        }

        void push(int x) {
            stack.add(stack.size(), x);
        }

        int pop() {
            return stack.remove(stack.size()-1);
        }
    }
}