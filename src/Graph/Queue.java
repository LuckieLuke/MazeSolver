package Graph;

import java.util.ArrayList;

public class Queue {

    private ArrayList<Node> queue;

    public Queue() {
        queue = new ArrayList<>();

    }

    public int getSize() {
        return queue.size();
    }

    public void push(Node x) {
        queue.add(x);
    }

    public void push(ArrayList<Node> list) {
        queue.addAll(list);
    }

    public Node pull() {
        return queue.remove(0);
    }

    public Node getEl(int i) {
        return queue.get(i);
    }
}
