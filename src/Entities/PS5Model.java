package Entities;

public class PS5Model {
    private String namaModel;
    private double harga;

    // Constructor
    public PS5Model(String namaModel, double harga) {
        this.namaModel = namaModel;
        this.harga = harga;
    }

    public String getNamaModel() {
        return namaModel;
    }

    public double getHarga() {
        return harga;
    }

    @Override
    public String toString() {
        return "Model: " + namaModel + ", Harga: Rp" + String.format("%,.0f", harga);
    }
}