package services;

import entity.PreOrder;
import java.util.LinkedList;
import java.util.Queue;

public class PreOrderQueue {
    private Queue<PreOrder> queue;

    public PreOrderQueue() {
        this.queue = new LinkedList<>();
    }

    //Menambahkan preorder ke dalam antrian. Hanya order dengan status "Paid" yang boleh ditambahkan.
    public void addPreOrder(PreOrder order) {
        if ("Paid".equalsIgnoreCase(order.getStatus())) {
            System.out.println("Order belum dibayar. Tidak dapat ditambahkan ke antrian.");
            return;
        }
        queue.add(order);
        System.out.println("PreOrder ditambahkan ke antrian: " + order.getOrderId());
    }

    // Mengonfirmasi pembayaran pelanggan dan ubah status menjadi "Paid"
    public boolean confirmPayment(PreOrder order) {
        if ("Paid".equalsIgnoreCase(order.getStatus())) {
            System.out.println("Pembayaran sudah dikonfirmasi sebelumnya.");
            return false;
        }

        order.setStatus("Paid");
        System.out.println("Pembayaran berhasil untuk " + order.getCustomerName());
        return true;
    }

    //Memproses preorder pertama dalam antrian dan mengubah status menjadi "Processed"
    public PreOrder processNextOrder() {
        if (queue.isEmpty()) {
            System.out.println("Tidak ada preorder dalam antrian.");
            return null;
        }

        PreOrder nextOrder = queue.poll();
        nextOrder.setStatus("Processed");
        System.out.println("Memproses preorder: " + nextOrder.getOrderId());
        return nextOrder;
    }

    //Menampilkan semua preorder dalam antrian
    public void viewAllOrders() {
        if (queue.isEmpty()) {
            System.out.println("Antrian preorder kosong.");
            return;
        }

        System.out.println("Daftar PreOrder:");
        for (PreOrder order : queue) {
            System.out.printf("ID:  | Customer:  | Product:  | Qty:  | Status: ",
                    order.getOrderId(), order.getCustomerName(), order.getProductName(),
                    order.getQuantity(), order.getStatus());
        }
    }

    //Mendapatkan jumlah preorder dalam antrian
    public int getQueueSize() {
        return queue.size();
    }

    //Mengecek apakah antrian kosong
    public boolean isQueueEmpty() {
        return queue.isEmpty();
    }
}