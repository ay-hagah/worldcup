package app;

import java.util.StringJoiner;

public class LinkedList extends Node {

    private Node head;

    private int size;

    public LinkedList() {
        head = null;
        size = 0;
    }

    public LinkedList(Node head, int size) {
        this.head = head;
        this.size = size;
    }

    Node reverseList(Node node) {
        Node prevNode = head;
        while (prevNode.next != node) {
            prevNode = prevNode.next;
        }
        Node prev = null, curr = node, next;
        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        prevNode.next = prev;
        return head;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    public int count() {
        int count = 0;
        Node cur = head;
        while (cur != null) {
            cur = cur.next;
            count++;
        }
        return count;
    }

    public boolean search(int key) {
        if (key == 0) return true;
        Node cur = head;
        while (cur != null) {
            if (cur.value == key) {
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    public void printAllMatches() {
        Node cur = head;
        System.out.println("----- MATCH DATA -----");
        for (int i = 0; i < count(); i++) {
            cur.m.print();
            System.out.println("----------------------");
            cur = cur.next;
        }
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner("->");
        Node cur = head;
        while (cur != null) {
            joiner.add(cur.value + "");
            cur = cur.next;
        }
        return joiner.toString();
    }

    public void print() {
        Node temp = head;
        while (temp != null && temp.next != null) {
            System.out.print(temp.value + "->");
            temp = temp.next;
        }
        if (temp != null) {
            System.out.print(temp.value);
            System.out.println();
        }
    }

    public void insertHead(Match x) {
        insertNth(x, 0);
    }

    public void insert(Match data) {
        if (data == null) {
            return;
        }
        value = data.id;
        insertNth(data, size);
    }

    public void insertNth(Match data, int position) {
        checkBounds(position, 0, size);
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            size++;
            return;
        }
        if (position == 0) {
            newNode.next = head;
            head = newNode;
            size++;
            return;
        }

        Node cur = head;
        for (int i = 0; i < position - 1; ++i) {
            cur = cur.next;
        }
        newNode.next = cur.next;
        cur.next = newNode;
        size++;
    }

    public void deleteHead() {
        deleteNth(0);
    }

    public void delete() {
        deleteNth(size - 1);
    }

    public void deleteNth(int position) {
        checkBounds(position, 0, size - 1);
        if (position == 0) {
            Node destroy = head;
            head = head.next;
            destroy = null;
            size--;
            return;
        }
        Node cur = head;
        for (int i = 0; i < position - 1; ++i) {
            cur = cur.next;
        }

        Node destroy = cur.next;
        cur.next = cur.next.next;
        destroy = null;

        size--;
    }

    public Match getNth(int index) {
        checkBounds(index, 0, size - 1);
        Node cur = head;
        for (int i = 0; i < index; ++i) {
            cur = cur.next;
        }
        return cur.m;
    }

    public void checkBounds(int position, int low, int high) {
        if (position > high || position < low) {
            throw new IndexOutOfBoundsException(position + "");
        }
    }
}

class Node {

    int value;

    Match m;

    public Match getMatch() {
        return this.m;
    }

    Node next;

    Node() {
    }

    Node(Match m) {
        this.m = m;
        this.value = m.id;
    }

    Node(int value, Node next, Match m) {
        this.value = value;
        this.next = next;
        this.m = m;
    }
}
