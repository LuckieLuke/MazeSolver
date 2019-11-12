package Graph;

import Maze.Maze;

import java.util.ArrayList;

public class BFSGraph {

    private Maze m;
    private ArrayList<Node>[] adjList;

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
        int num = 0;

        for(int i = 1; i < m.getHeight()-1; i+=2) {
            for(int j = 1; j < m.getWidth()-1; j+=2) {
                if(m.getChar(i, j-1) == '0')
                    adjList[num].add(new Node(num-1));

                if(m.getChar(i-1, j) == '0')
                    adjList[num].add(new Node(num-x));

                if(m.getChar(i, j+1) == '0')
                    adjList[num].add(new Node(num+1));

                if(m.getChar(i+1, j) == '0')
                    adjList[num].add(new Node(num+x));

                num++;
            }
        }
    }

    public void printGraph() {
        for(int i = 0; i < adjList.length; i++) {
            System.out.print(i);

            for(Node j: adjList[i])
                System.out.print(" -> " + j.value);
            System.out.println();
        }
    }

    class Node {
        private boolean visited;
        private int value;

        public Node(int value) {
            this.value = value;
            visited = false;
        }

        public void visit() {
            visited = true;
        }

        public void unvisit() {
            visited = false;
        }
    }

}
