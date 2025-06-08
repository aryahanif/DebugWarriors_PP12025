package Services;

import Entities.AntrianPreOrder;
import java.io.*;
import java.util.Arrays;

public class PreOrderQueue{
    private int capacity;
    private AntrianPreOrder[] antrianArray;
    private int front;
    private int rear;
    private int size;
    private AntrianPreOrder[] riwayatPemesanan;
    private int jumlahRiwayat = 0;
    private final int MAX_RIWAYAT = 100;

    public PreOrderQueue(int capacity) {
        this.capacity = capacity;
        this.antrianArray = new AntrianPreOrder[capacity];
        this.riwayatPemesanan = new AntrianPreOrder[MAX_RIWAYAT];
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

    public void enqueue(AntrianPreOrder antrian) {
        if (isFull()) {
            System.out.println("Antrian sudah penuh.");
            return;
        }
        rear = (rear + 1) % capacity;
        antrianArray[rear] = antrian;
        size++;
    }

    public AntrianPreOrder dequeue() {
        if (isEmpty()) {
            System.out.println("Tidak ada antrian untuk diproses.");
            return null;
        }

        AntrianPreOrder data = antrianArray[front];
        front = (front + 1) % capacity;
        size--;

        data.setStatus("SELESAI DIPROSES");

        // Simpan ke riwayat
        if (jumlahRiwayat < MAX_RIWAYAT) {
            riwayatPemesanan[jumlahRiwayat++] = data;
        }

        return data;
    }

    public AntrianPreOrder peekFront() {
        if (isEmpty()) {
            return null;
        }
        return antrianArray[front];
    }

    public AntrianPreOrder searchByNomorAntrian(String nomor) {
        for (int i = 0; i < size; i++) {
            int index = (front + i) % capacity;
            if (antrianArray[index].getNomorAntrian().equalsIgnoreCase(nomor)) {
                return antrianArray[index];
            }
        }
        return null;
    }

    public AntrianPreOrder[] searchByNamaCustomer(String nama) {
        // Hitung dulu berapa banyak yang cocok untuk alokasi array
        int count = 0;
        for (int i = 0; i < size; i++) {
            int index = (front + i) % capacity;
            if (antrianArray[index].getCustomer().getNamaCust().equalsIgnoreCase(nama)) {
                count++;
            }
        }

        // Jika tidak ada, return array kosong
        if (count == 0) {
            return new AntrianPreOrder[0];
        }

        // Alokasikan array hasil
        AntrianPreOrder[] hasil = new AntrianPreOrder[count];
        int pos = 0;
        for (int i = 0; i < size; i++) {
            int index = (front + i) % capacity;
            if (antrianArray[index].getCustomer().getNamaCust().equalsIgnoreCase(nama)) {
                hasil[pos] = antrianArray[index];
                pos++;
            }
        }

        return hasil;
    }


    public void displayAntrian() {
        if (isEmpty()) {
            System.out.println("|| Antrian Pre-Order saat ini KOSONG. ||");
            return;
        }
        System.out.println("||===== DAFTAR ANTRIAN PRE-ORDER =====||");
        for (int i = 0; i < size; i++) {
            int index = (front + i) % capacity;
            System.out.println(antrianArray[index].toString());
        }
        System.out.println("||====================================||");
    }

    // Menyimpan seluruh data (antrian dan riwayat) ke satu file
    public void saveToFile(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            // Simpan atribut dasar antrian
            oos.writeInt(capacity);
            oos.writeInt(front);
            oos.writeInt(rear);
            oos.writeInt(size);
            oos.writeObject(antrianArray);

            // Simpan riwayat
            oos.writeInt(jumlahRiwayat);
            oos.writeObject(Arrays.copyOf(riwayatPemesanan, jumlahRiwayat));

            System.out.println("Antrian dan Riwayat berhasil disimpan ke file: " + filename);
        } catch (IOException e) {
            System.out.println("Gagal menyimpan data: " + e.getMessage());
        }
    }

    // Memuat seluruh data (antrian dan riwayat) dari satu file
    public void loadFromFile(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            // Baca atribut dasar antrian
            capacity = ois.readInt();
            front = ois.readInt();
            rear = ois.readInt();
            size = ois.readInt();
            antrianArray = (AntrianPreOrder[]) ois.readObject();

            // Baca riwayat
            jumlahRiwayat = ois.readInt();
            AntrianPreOrder[] loadedRiwayat = (AntrianPreOrder[]) ois.readObject();
            riwayatPemesanan = new AntrianPreOrder[MAX_RIWAYAT];
            System.arraycopy(loadedRiwayat, 0, riwayatPemesanan, 0, jumlahRiwayat);

            System.out.println("Antrian dan Riwayat berhasil dimuat dari file: " + filename);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Gagal memuat data: " + e.getMessage());
        }
    }

    public void displayRiwayat() {
        if (jumlahRiwayat == 0) {
            System.out.println("|| Riwayat pemrosesan masih kosong. ||");
            return;
        }

        System.out.println("||===== RIWAYAT PEMESANAN SELESAI =====||");
        for (int i = 0; i < jumlahRiwayat; i++) {
            System.out.println(riwayatPemesanan[i].toString());
        }
        System.out.println("||======================================||");
    }

}
