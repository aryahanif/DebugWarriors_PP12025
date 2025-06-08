package Entities;

import java.io.Serializable;

public class AntrianPreOrder implements Serializable {
    private Customer customer;
    private PS5Model model;
    private String nomorAntrian;
    private String waktuMasukAntrian;
    private String status;

    // Constructor
    public AntrianPreOrder(Customer customer, PS5Model model, String nomorAntrian, String waktuMasukAntrian) {
        this.customer = customer;
        this.model = model;
        this.nomorAntrian = nomorAntrian;
        this.waktuMasukAntrian = waktuMasukAntrian;
        this.status = "DALAM ANTRIAN"; // Status default
    }

    // Getters
    public Customer getCustomer() {
        return customer;
    }

    public PS5Model getModel() {
        return model;
    }

    public String getNomorAntrian() {
        return nomorAntrian;
    }

    public String getWaktuMasukAntrian() {
        return waktuMasukAntrian;
    }

    public String getStatus() {
        return status;
    }

    // Setter
    public void setStatus(String status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return "==============================" +
                "\nNomor Antrian   : " + nomorAntrian +
                "\nNama Pelanggan  : " + customer.getNamaCust() +
                "\nKontak          : " + customer.getNoKontak() +
                "\nModel PS5       : " + model.getNamaModel() +
                "\nHarga           : Rp" + String.format("%,.0f", model.getHarga()) +
                "\nWaktu Daftar    : " + waktuMasukAntrian +
                "\nStatus          : " + status +
                "\n==============================";
    }
}