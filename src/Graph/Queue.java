package Graph;

import java.util.ArrayList;

public class Queue {

    ArrayList<Integer> queue;

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
        int rem = queue.remove(0);
        return rem;
    }

}
