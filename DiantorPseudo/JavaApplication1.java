/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplication1;

/**
 *
 * @author BED
 */
public class JavaApplication1 {

    static class MyLinkedList {
        int SIZE = 100;
        int[] values = new int[SIZE];
        int[] next = new int[SIZE];
        int head = -1, tail = -1, free = 0;

        public MyLinkedList() {
            for (int i = 0; i < SIZE - 1; i++) {
                next[i] = i + 1;
            }
            next[SIZE - 1] = -1;
        }

        // Add value to the end
        void add(int value) {
            if (free == -1) {
                System.out.println("List is full");
                return;
            }

            int newNode = free;
            free = next[free];
            values[newNode] = value;
            next[newNode] = -1;

            if (head == -1) {
                head = tail = newNode;
            } else {
                next[tail] = newNode;
                tail = newNode;
            }
        }

        // Return first value
        int peek() {
            if (head == -1) {
                throw new RuntimeException("List is empty");
            }
            return values[head];
        }

        // Remove and return first value
        int poll() {
            if (head == -1) {
                throw new RuntimeException("List is empty");
            }
            int val = values[head];
            int oldHead = head;
            head = next[head];

            if (head == -1) tail = -1;

            next[oldHead] = free;
            free = oldHead;
            return val;
        }

        // Remove and return last value
        int pop() {
            if (tail == -1) {
                throw new RuntimeException("List is empty");
            }

            int val = values[tail];

            if (head == tail) {
                next[tail] = free;
                free = tail;
                head = tail = -1;
            } else {
                int prev = head;
                while (next[prev] != tail) {
                    prev = next[prev];
                }
                next[tail] = free;
                free = tail;
                tail = prev;
                next[tail] = -1;
            }

            return val;
        }

        // Print all elements
        void print() {
            int curr = head;
            while (curr != -1) {
                System.out.print(values[curr] + " ");
                curr = next[curr];
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        MyLinkedList list = new MyLinkedList();

        list.add(10);
        list.add(20);
        list.add(30);
        list.print(); // 10 20 30

        System.out.println("Peek: " + list.peek()); // 10

        System.out.println("Poll: " + list.poll()); // 10
        list.print(); // 20 30

        System.out.println("Pop: " + list.pop()); // 30
        list.print(); // 20
    }
}
