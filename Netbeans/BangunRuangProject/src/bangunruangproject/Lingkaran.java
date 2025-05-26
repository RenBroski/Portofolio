package bangunruangproject;

public class Lingkaran {
    private double radius; // Jari-jari lingkaran
    private double luas;   // Luas lingkaran
    private double keliling; // Keliling lingkaran

    // Konstruktor
    public Lingkaran(double radius) {
        this.radius = radius;
        hitungLuas();
        hitungKeliling();
    }

    // Metode untuk menghitung luas
    private void hitungLuas() {
        this.luas = Math.PI * radius * radius; // πr²
    }

    // Metode untuk menghitung keliling
    private void hitungKeliling() {
        this.keliling = 2 * Math.PI * radius; // 2πr
    }

    // Getter untuk luas
    public double getLuas() {
        return luas;
    }

    // Getter untuk keliling
    public double getKeliling() {
        return keliling;
    }
}
