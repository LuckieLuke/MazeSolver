package Main;

import Maze.Maze;

import java.io.*;

public class FileManager {

    public char[][] readData(String path) throws IOException {
        FileReader fr = new FileReader(path);
        int rows = 0;
        int chars = 0;

        int tmp;
        while((tmp = fr.read()) != -1) {
            chars++;
            if(tmp == '\n')
                rows++;
        }
        int columns = chars/rows;
        columns--;

        char[][] maze = new char[rows][columns];

        fr = new FileReader(path);

        int i = 0;
        int j = 0;
        while((tmp = fr.read()) != -1) {
            if((char)tmp == '\n') {
                i++;
                j = 0;
            }
            else {
                maze[i][j] = (char)tmp;
                j++;
            }
        }

        return maze;
    }

    public void writeData(String path, Maze maze) throws IOException {
        PrintWriter pw = new PrintWriter(path);

        for(int i = 0; i < maze.getHeight(); i++) {
            for(int j = 0; j < maze.getWidth(); j++) {
                pw.append(maze.getChar(i, j));
            }
            pw.append('\n');
        }

        pw.close();
    }

}
