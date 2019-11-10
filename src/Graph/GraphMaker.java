package Graph;

import Maze.Maze;

import java.util.List;
import java.util.Map;

public class GraphMaker {

    private Maze m;
    private Map<Integer, List<Integer>> map;

    public GraphMaker(Maze m) {
        this.m = m;
        numerate();
    }


    private void numerate() {

    }

    public String toString() {
        return m.toString();
    }

}
