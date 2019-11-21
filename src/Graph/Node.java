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
}