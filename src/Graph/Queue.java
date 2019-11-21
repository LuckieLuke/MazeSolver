package Graph;

import java.util.ArrayList;

public class Queue {

    private ArrayList<Integer> queue;

    public Queue() {
        queue = new ArrayList<>();

    }

    public int getSize() {
        return queue.size();
    }

    public void push(int x) {
        queue.add(x);
    }

    public void push(ArrayList<Integer> list) {
        queue.addAll(list);
    }

    public int pull() {
        return queue.remove(0);
    }

}
