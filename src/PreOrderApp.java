import Entities.AntrianPreOrder;
import Entities.Customer;
import Entities.PS5Model;
import Services.PreOrderQueue;
import Util.InputUtil;
import Util.MenuDisplay;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PreOrderApp {

    private static final String DATA_FILE = "src/Util/data_preorder.ser";

    public static void main(String[] args) {
        // Buat antrian dengan kapasitas tertentu
        PreOrderQueue queue = new PreOrderQueue(10);

        // Coba muat data jika ada
        queue.loadFromFile(DATA_FILE);

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
                case 4:
                    AntrianPreOrder antrianDepan = queue.peekFront();
                    if (antrianDepan != null) {
                        System.out.println("\nAntrian Selanjutnya (Paling Depan):");
                        System.out.println(antrianDepan);
                    } else {
                        System.out.println("Tidak ada antrian yang tersedia.");
                    }
                    break;
                case 5:
                    String nomor = InputUtil.inputString("Masukkan Nomor Antrian: ");
                    AntrianPreOrder hasilNomor = queue.searchByNomorAntrian(nomor);
                    if (hasilNomor != null) {
                        System.out.println("Ditemukan:");
                        System.out.println(hasilNomor);
                    } else {
                        System.out.println("Data tidak ditemukan dengan nomor antrian tersebut.");
                    }
                    break;
                case 6:
                    String nama = InputUtil.inputString("Masukkan Nama Customer: ");
                    AntrianPreOrder[] hasilNama = queue.searchByNamaCustomer(nama);
                    if (hasilNama.length > 0) {
                        System.out.println("Ditemukan " + hasilNama.length + " data:");
                        for (AntrianPreOrder item : hasilNama) {
                            System.out.println(item);
                        }
                    } else {
                        System.out.println("Data tidak ditemukan dengan nama tersebut.");
                    }
                    break;

                case 7:
                    queue.saveToFile(DATA_FILE);
                    break;
                case 8:
                    queue.loadFromFile(DATA_FILE);
                    break;
                case 9:
                    queue.displayRiwayat();
                    break;
                case 0:
                    // Simpan data sebelum keluar
                    queue.saveToFile(DATA_FILE);
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
        PS5Model ps5Model;

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

        String nomorAntrian = "PS5-" + (queue.size() + 1);
        String waktuMasuk = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));

        AntrianPreOrder antrianBaru = new AntrianPreOrder(customer, ps5Model, nomorAntrian, waktuMasuk);
        queue.enqueue(antrianBaru);

        System.out.println("\nSUKSES! Pre-order berhasil ditambahkan ke antrian.");
        System.out.println(antrianBaru);
    }

    private static void prosesAntrian(PreOrderQueue queue) {
        AntrianPreOrder antrianDiproses = queue.dequeue();

        if (antrianDiproses != null) {
            System.out.println("\n--- Memproses Antrian ---");
            System.out.println("Data berikut telah selesai diproses:");
            System.out.println(antrianDiproses);
        }
    }
}
