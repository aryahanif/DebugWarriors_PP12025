package Entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Customer {
    private String nama, noKontak, alamat;

    private LocalDateTime waktuPesan; 

    // Kontruksi
    public Customer(String nama, String noKontak, String alamat) {
        this.nama = nama;
        this.noKontak = noKontak;
        this.alamat = alamat;
        this.waktuPesan = LocalDateTime.now(); 
    }

    public String getNamaCust() {
        return nama;
    }

    public String getNoKontak() {
        return noKontak;
    }

    public String getAlamat() {
        return alamat;
    }

    public LocalDateTime getWaktuPesan() {
        return waktuPesan;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return "Nama Pelanggan: " + nama + "\nNo Kontak: " + noKontak + "\nAlamat: " + alamat + "\nWaktu Pesan: " + waktuPesan.format(formatter);
    }
}