package startype;

import java.util.HashMap;

/**
 * @Author: jaxon
 * @Description:
 * @Date: 2018/8/10
 * @Time: 下午9:27
 * @Project: Algorithm-Java-implements
 */
public class LRUCache {

    /**
     * LRU Cache
     */

    class Node {
        int key;
        int value;
        Node next;
        Node prev;
        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private HashMap<Integer, Node> map = null;
    private Node head = null;
    private Node tail = null;
    private int capacity;

    public LRUCache(int capacity) {
        map = new HashMap<>();
        this.capacity = capacity;
    }

    public int get(int key) {
        if(map == null || !map.containsKey(key)) {
            return -1;
        }
        Node node = map.get(key);
        updateCache(node);
        return node.value;
    }

    public void put(int key, int value) {
        if(!map.containsKey(key)) {
            if(map.size() == this.capacity && this.capacity > 0) {
                removeLast();
            }
            Node node = new Node(key, value);
            map.put(key, node);
            addNew(node);
        }else {
            Node node = map.get(key);
            updateCache(node);
            node.value = value;
        }
    }

    private void updateCache(Node node) {
        if(node == head) {
            return;
        }else {
            if( node == tail) {
                tail = node.prev;
                tail.next = null;
            }else {
                Node p = node.prev;
                p.next = node.next;
                node.next.prev = p;
            }
            node.next = head;
            head.prev = node;
            node.prev = null;
            head = node;
        }
    }

    private void addNew(Node node) {
        if(head == null) {
            head = node;
            tail = node;
        }else {
            node.next = head;
            head.prev = node;
            head = node;
        }
    }

    private void removeLast() {
        map.remove(tail.key);
        if(head == tail) {
            head = null;
            tail = null;
        }else {
            Node tmp = tail.prev;
            tmp.next = null;
            tail = tmp;
            tail.next = null;
        }
    }
}
