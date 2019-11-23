package Graph;

public class Node {
    private boolean visited;
    private int value;

    public Node(int value) {
        this.value = value;
        visited = false;
    }

    public int getValue() {
        return value;
    }

    public void visit() {
        visited = true;
    }

    public void unvisit() {
        visited = false;
    }

    public boolean isVisited() {
        return visited;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(value);
        sb.append(", visited: " + visited);
        return sb.toString();
    }
}