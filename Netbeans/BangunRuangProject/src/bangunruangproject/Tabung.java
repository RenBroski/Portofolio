package bangunruangproject;

public class Tabung {
    private double radius; // Jari-jari tabung
    private double tinggi; // Tinggi tabung
    private double volume; // Volume tabung
    private double luasPermukaan; // Luas permukaan tabung

    // Konstruktor
    public Tabung(double radius, double tinggi) {
        this.radius = radius;
        this.tinggi = tinggi;
        hitungVolume();
        hitungLuasPermukaan();
    }

    // Metode untuk menghitung volume
    private void hitungVolume() {
        volume = Math.PI * radius * radius * tinggi;
    }

    // Metode untuk menghitung luas permukaan
    private void hitungLuasPermukaan() {
        luasPermukaan = 2 * Math.PI * radius * (radius + tinggi);
    }

    // Getter untuk volume
    public double getVolume() {
        return volume;
    }

    // Getter untuk luas permukaan
    public double getLuasPermukaan() {
        return luasPermukaan;
    }
}
