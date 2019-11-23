package Graph;

import Maze.Maze;

import java.lang.reflect.Array;
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
        ArrayList<Integer> nodes = BFSSearch(m.getIn(), m.getOut());

        System.out.println("Your path to the exit is:");
        System.out.print("IN->");
        for(Integer i: nodes)
            System.out.print((i+1) + "->");
        System.out.println("OUT");
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

    public ArrayList<Integer> BFSSearch(int start, int end) {
        ArrayList<Integer> result = new ArrayList<>();
        Queue q = new Queue();

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

        return shortestPath(result);
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
}
