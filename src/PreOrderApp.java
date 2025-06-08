import Entities.AntrianPreOrder;
import Entities.Customer;
import Entities.PS5Model;
import Services.PreOrderQueue;
import Util.InputUtil;
import Util.MenuDisplay;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PreOrderApp {

    public static void main(String[] args) {
        // Kapasitas antrian bisa sesuai kebutuhan
        PreOrderQueue queue = new PreOrderQueue(10);

        while (true) {
            MenuDisplay.MainMenuDisplay();
            int choice = InputUtil.inputInt("Pilih menu: ");

            switch (choice) {
                case 1:
                    tambahAntrianBaru(queue);
                    break;
                case 2:
                    prosesAntrian(queue);
                    break;
                case 3:
                    queue.displayAntrian();
                    break;
                case 0:
                    System.out.println("Terima kasih telah menggunakan aplikasi. Sampai jumpa!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        }
    }


    private static void tambahAntrianBaru(PreOrderQueue queue) {
        if (queue.isFull()) {
            System.out.println("MAAF: Antrian sudah penuh, tidak bisa menambah data baru.");
            return;
        }

        System.out.println("\n--- Form Pre-Order Baru ---");
        String nama = InputUtil.inputString("Masukkan Nama: ");
        String noKontak = InputUtil.inputString("Masukkan No. Kontak: ");
        String alamat = InputUtil.inputString("Masukkan Alamat: ");
        Customer customer = new Customer(nama, noKontak, alamat);

        MenuDisplay.MenuModelPS5();
        int modelChoice = InputUtil.inputInt("Pilih model PS5: ");
        PS5Model ps5Model = null;

        switch (modelChoice) {
            case 1:
                ps5Model = new PS5Model("Standard Edition", 9_699_000);
                break;
            case 2:
                ps5Model = new PS5Model("Digital Edition", 8_199_000);
                break;
            case 3:
                ps5Model = new PS5Model("God of War Ragnarok Bundle", 10_599_000);
                break;
            case 4:
                ps5Model = new PS5Model("Marvel's Spider-Man 2 Bundle", 10_599_000);
                break;
            default:
                System.out.println("Pilihan model tidak valid.");
                return;
        }

        // Membuat nomor antrian unik
        String nomorAntrian = "PS5-" + (queue.size() + 1);
        String waktuMasuk = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));

        AntrianPreOrder antrianBaru = new AntrianPreOrder(customer, ps5Model, nomorAntrian, waktuMasuk);
        queue.enqueue(antrianBaru);

        System.out.println("\nSUKSES! Pre-order berhasil ditambahkan ke antrian.");
        System.out.println(antrianBaru.toString());
    }

    private static void prosesAntrian(PreOrderQueue queue) {
        AntrianPreOrder antrianDiproses = queue.dequeue();

        if (antrianDiproses != null) {
            // Ubah statusnya
            antrianDiproses.setStatus("SELESAI DIPROSES"); // Status diubah

            System.out.println("\n--- Memproses Antrian ---");
            System.out.println("Data berikut telah selesai diproses:");
            System.out.println(antrianDiproses.toString()); // menampilkan status "SELESAI DIPROSES"
        }
    }
}
