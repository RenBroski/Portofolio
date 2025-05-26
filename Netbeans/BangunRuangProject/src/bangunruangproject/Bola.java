package bangunruangproject;

public class Bola {
    private double radius; // Jari-jari bola
    private double volume; // Volume bola
    private double luasPermukaan; // Luas permukaan bola

    // Konstruktor
    public Bola(double radius) {
        this.radius = radius;
        hitungVolume();
        hitungLuasPermukaan();
    }

    // Metode untuk menghitung volume
    private void hitungVolume() {
        this.volume = (4.0 / 3.0) * Math.PI * Math.pow(radius, 3);
    }

    // Metode untuk menghitung luas permukaan
    private void hitungLuasPermukaan() {
        this.luasPermukaan = 4 * Math.PI * Math.pow(radius, 2);
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
