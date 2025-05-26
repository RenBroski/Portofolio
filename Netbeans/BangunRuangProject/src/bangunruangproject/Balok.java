package bangunruangproject;

public class Balok {
    private double panjang;
    private double lebar;
    private double tinggi;

    // Konstruktor
    public Balok(double panjang, double lebar, double tinggi) {
        this.panjang = panjang;
        this.lebar = lebar;
        this.tinggi = tinggi;
    }

    // Metode untuk menghitung volume
    public double getVolume() {
        return panjang * lebar * tinggi;
    }

    // Metode untuk menghitung luas permukaan
    public double getLuasPermukaan() {
        return 2 * (panjang * lebar + panjang * tinggi + lebar * tinggi);
    }
}
