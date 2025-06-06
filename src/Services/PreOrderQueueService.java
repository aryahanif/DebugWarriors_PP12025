package Services;

import Entities.PreOrder;

public class PreOrderQueueService {
    private static class Node {
        PreOrder data;
        Node next;
        
        public Node(PreOrder data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node front;
    private Node rear;

    public PreOrderQueueService() {
        front = null;
        rear = null;
    }

    // operasi enqueue
    public void enqueue(PreOrder preorder) {
        Node newNode = new Node(preorder);
        
        if (isEmpty()) {
            front = newNode;
            rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
    }

    // operasi dequeue
    public PreOrder dequeue() {
        if (isEmpty()) return null;
        
        PreOrder processed = front.data;
        
        if (front == rear) {
            front = null;
            rear = null;
        } else {
            Node temp = front;
            front = front.next;
            temp.next = null;  // hapus referensi
        }
        return processed;
    }

    // cek bila antrian kosong
    public boolean isEmpty() {
        return front == null;
    }

    // lihat front saat ini
    public PreOrder peekFront() {
        return isEmpty() ? null : front.data;
    }
}
