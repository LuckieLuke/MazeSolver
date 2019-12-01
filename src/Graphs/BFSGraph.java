package Graphs;

import Maze.Maze;

import java.util.ArrayList;
import java.util.Iterator;

public class BFSGraph {

    private Maze m;
    private ArrayList<Node>[] adjList;
    private Node[] nodes;

    public BFSGraph(Maze m) {
        this.m = m;

        if(m == null) {
            System.out.println("Maze is empty.");
            System.exit(6);
        }

        adjList = new ArrayList[((m.getWidth() - 1) / 2) * ((m.getHeight() - 1) / 2)];
        nodes = new Node[((m.getWidth() - 1) / 2) * ((m.getHeight() - 1) / 2)];

        for(int i = 0; i < adjList.length; i++) {
            adjList[i] = new ArrayList<>();
        }

        for(int i = 0; i < nodes.length; i++)
            nodes[i] = new Node(i);

        createGraph();
    }

    private void createGraph() {
        int x = (m.getWidth()-1)/2;
        int num = 0;

        for(int i = 1; i < m.getHeight()-1; i+=2) {
            for(int j = 1; j < m.getWidth()-1; j+=2) {
                if(m.getChar(i, j-1) == '0')
                    adjList[num].add(nodes[num-1]);

                if(m.getChar(i-1, j) == '0')
                    adjList[num].add(nodes[num-x]);

                if(m.getChar(i, j+1) == '0')
                    adjList[num].add(nodes[num+1]);

                if(m.getChar(i+1, j) == '0')
                    adjList[num].add(nodes[num+x]);

                num++;
            }
        }
    }

    public void search() {
        BFSSearch(m.getIn(), m.getOut());
    }

    private void BFSSearch(int start, int end) {
        ArrayList<Integer> result = new ArrayList<>();
        Queue q = new Queue();

        long before = System.nanoTime();

        nodes[start].visit();
        q.push(nodes[start]);

        Node tmp;
        boolean run = true;

        while(run && q.getSize() != 0) {
            tmp = q.pull();
            result.add(tmp.getValue());

            if(tmp.getValue() == end)
                run = false;

            Iterator<Node> it = adjList[tmp.getValue()].listIterator();
            while(it.hasNext()) {
                Node n = it.next();
                if(!n.isVisited()) {
                    n.visit();
                    q.push(n);
                }
            }
        }

        print(shortestPath(result), before);
    }

    private ArrayList<Integer> shortestPath(ArrayList<Integer> res) {
        int index = res.size()-1;

        while(index > 0) {
            if(!adjList[res.get(index)].contains(nodes[res.get(index-1)])) {
                res.remove(index-1);
            }
            index--;
        }

        return res;
    }

    private void print(ArrayList<Integer> nodes, long before) {
        long after = System.nanoTime();
        System.out.println("Your BFSed shortest path to the exit is:");
        System.out.print("IN->");
        for(Integer i: nodes)
            System.out.print((i+1) + "->");
        System.out.println("EXIT");
        System.out.println("It took " + (after - before) + " ns to find this path.\n");
    }

    class Queue {

        private ArrayList<Node> queue;

        Queue() {
            queue = new ArrayList<>();
        }

        int getSize() {
            return queue.size();
        }

        void push(Node x) {
            queue.add(x);
        }

        Node pull() {
            return queue.remove(0);
        }

    }

    class Node {
        private boolean visited;
        private int value;

         Node(int value) {
            this.value = value;
            visited = false;
        }

        int getValue() {
            return value;
        }

        void visit() {
            visited = true;
        }

        boolean isVisited() {
            return visited;
        }
    }
}
