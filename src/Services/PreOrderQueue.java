package Services;

import java.util.ArrayList;
import java.util.List;

import Entities.AntrianPreOrder;

public class PreOrderQueue {
    private int capacity;
    private AntrianPreOrder[] antrianArray;
    private int front; // elemen depan
    private int rear;  // elemen belakang
    private int size;  // elemen saat ini

    // Constructor
    public PreOrderQueue(int capacity) {
        this.capacity = capacity;
        this.antrianArray = new AntrianPreOrder[capacity];
        this.front = 0;
        this.rear = -1;
        this.size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

    public int size() {
        return size;
    }

    // Menambahkan antrian baru
    public void enqueue(AntrianPreOrder antrian) {
        if (isFull()) {
            System.out.println("Antrian sudah penuh.");
            return;
        }
        rear = (rear + 1) % capacity;
        antrianArray[rear] = antrian;
        size++;
    }

    // Memproses mengeluarkan antrian terdepan
    public AntrianPreOrder dequeue() {
        if (isEmpty()) {
            System.out.println("Tidak ada antrian untuk diproses.");
            return null;
        }

        AntrianPreOrder data = antrianArray[front];
        front = (front + 1) % capacity; // Circular queue
        size--;
        return data;
    }

    // Melihat antrian terdepan tanpa mengeluarkan
    public AntrianPreOrder peekFront() {
        if (isEmpty()) {
            return null;
        }
        return antrianArray[front];
    }

    // Cari berdasarkan nomor antrian
    public AntrianPreOrder searchByNomorAntrian(String nomor) {
        for (int i = 0; i < size; i++) {
            int index = (front + i) % capacity;
            if (antrianArray[index].getNomorAntrian().equalsIgnoreCase(nomor)) {
                return antrianArray[index];
            }
        }
        return null;
    }

    // Cari berdasarkan nama customer
    public List<AntrianPreOrder> searchAllByNamaCustomer(String nama) {
        List<AntrianPreOrder> hasil = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            int index = (front + i) % capacity;
            if (antrianArray[index].getCustomer().getNamaCust().equalsIgnoreCase(nama)) {
                hasil.add(antrianArray[index]);
            }
        }   
        return hasil;
    }

    // Menampilkan semua data dalam antrian
    public void displayAntrian() {
        if (isEmpty()) {
            System.out.println("|| Antrian Pre-Order saat ini KOSONG. ||");
            return;
        }
        System.out.println("||===== DAFTAR ANTRIAN PRE-ORDER =====||");
        int current = front;
        for (int i = 0; i < size; i++) {
            System.out.println(antrianArray[current].toString());
            current = (current + 1) % capacity;
        }
        System.out.println("||====================================||");
    }
}