package bangunruangproject;

public class PersegiPanjang {
    private double panjang; // Panjang persegi panjang
    private double lebar;   // Lebar persegi panjang
    private double luas;    // Luas persegi panjang
    private double keliling; // Keliling persegi panjang

    // Konstruktor
    public PersegiPanjang(double panjang, double lebar) {
        this.panjang = panjang;
        this.lebar = lebar;
        hitungLuas();
        hitungKeliling();
    }

    // Metode untuk menghitung luas
    private void hitungLuas() {
        this.luas = panjang * lebar;
    }

    // Metode untuk menghitung keliling
    private void hitungKeliling() {
        this.keliling = 2 * (panjang + lebar);
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
