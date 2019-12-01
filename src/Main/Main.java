package Main;

import Graphs.BFSGraph;
import Graphs.TremauxGraph;
import Maze.Maze;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    static Maze m;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        FileManager fm = new FileManager();
        System.out.println("How to create a maze?");
        System.out.println("1) by size");
        System.out.println("2) by reading from file\n" + "\u001B[35m" + "   (please, remember that every line should end with a 'new line' character)" + "\u001B[0m");
        String answer = sc.next();

        if(answer.equals("1")) {
            System.out.println("What size? (height width)");
            int h = sc.nextInt();
            int w = sc.nextInt();
            m = new Maze(h, w);
            m.generate();
        }
        else if(answer.equals("2")) {
            System.out.println("Path to the file:");
            String path = sc.next();

            m = new Maze(path, fm);
        }
        else {
            System.out.println("Cannot continue - wrong option!");
            System.exit(1);
        }

        System.out.println("Want to save the maze? (yes/no)");
        answer = sc.next();
        if(answer.equals("yes")) {
            System.out.println("Give path to the file:");
            String path = sc.next();
            try {
                fm.writeData(path, m);
            }
            catch(IOException e) {
                System.out.println("Cannot save to the file!");
                System.exit(3);
            }
            System.out.println("Saved!");
        }

        /*
        *  This line below makes x random holes in the walls,
        *  creating more possible paths to the exit.
        *  Just comment it (or change the argument to 0) to get
        *  the same solutions from both searching algorithms.
        *  You can also adjust number of random holes
        *  by changing argument of the function.
        */

        m.makeRandomHoles(10);

        System.out.println("Your maze looks like this:\n" + m);
        BFSGraph bfs = new BFSGraph(m);
        bfs.search();
        TremauxGraph tg = new TremauxGraph(m);
        tg.search();

    }

}
