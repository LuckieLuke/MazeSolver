package Graph;

import Maze.Maze;

import java.util.ArrayList;

public class BFSGraph {

    private Maze m;
    private ArrayList<Integer>[] adjList;

    public BFSGraph(Maze m) {
        this.m = m;

        if(m == null) {
            System.out.println("Maze is empty.");
            System.exit(6);
        }

        adjList = new ArrayList[((m.getWidth()-1)/2)*((m.getHeight()-1)/2)];

        for(int i = 0; i < adjList.length; i++) {
            adjList[i] = new ArrayList<>();
        }
        createGraph();
    }

    private void createGraph() {
        int x = (m.getWidth()-1)/2;
        int y = (m.getHeight()-1)/2;
        int num = 0;

        for(int i = 1; i < m.getHeight()-1; i+=2) {
            for(int j = 1; j < m.getWidth()-1; j+=2) {
                if(m.getChar(i, j-1) == '0')
                    adjList[num].add(num - 1);

                if(m.getChar(i-1, j) == '0')
                    adjList[num].add(num-x);

                if(m.getChar(i, j+1) == '0')
                    adjList[num].add(num+1);

                if(m.getChar(i+1, j) == '0')
                    adjList[num].add(num+x);

                num++;
            }
        }
    }

    public void printGraph() {
        for(int i = 0; i < adjList.length; i++) {
            System.out.print(i);

            for(Integer j: adjList[i])
                System.out.println(" -> " + j);
            System.out.println("\n");
        }
    }

}
