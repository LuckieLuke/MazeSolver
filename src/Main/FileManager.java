package Main;

import Maze.Maze;

import java.io.*;

public class FileManager {

    public char[][] readData(String path) throws FileNotFoundException {
        FileReader fr = new FileReader(path);
        int rows = 0;
        int chars = 0;

        int tmp;
        try {
            while ((tmp = fr.read()) != -1) {
                chars++;
                if (tmp == '\n')
                    rows++;
            }
        } catch (IOException e) {
            System.out.println("Error while reading from file.");
            System.exit(8);
        }
        int columns = chars/rows;
        columns--;

        char[][] maze = new char[rows][columns];

        fr = new FileReader(path);

        int i = 0;
        int j = 0;
        try {
            while ((tmp = fr.read()) != -1) {
                if ((char) tmp == '\n') {
                    i++;
                    j = 0;
                } else {
                    maze[i][j] = (char) tmp;
                    j++;
                }
            }
        } catch (IOException e) {
            System.out.println("Error while reading from file.");
            System.exit(8);
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
