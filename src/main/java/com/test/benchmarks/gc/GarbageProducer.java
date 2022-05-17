package com.test.benchmarks.gc;

public class GarbageProducer {

    volatile Node head;
    volatile Node tail;
    volatile Node tmp;

    private class Node {
        public Node next;
    }

    public Node produceGarbage() {

        createLinkedList();

        // No we just keep the queue at the same length.
        for (int j = 0; j < 100_000; ++j) {
            justGarbage();

            reproduce();
        }
        return head;
    }

    private void createLinkedList() {
        head = new Node();
        tail = head;

        // grow the list
        for (int i = 0; i < 10; ++i) {
            Node node = new Node();
            node.next = head;
            head = node;
        }

    }

    private void justGarbage() {
        // Allocate some garbage
        for (int i = 0; i < 20; ++i) {
            tmp = new Node();
        }
    }

    private void reproduce() {
        head = head.next;
        tail.next = new Node();
        tail = tail.next;
    }
}
