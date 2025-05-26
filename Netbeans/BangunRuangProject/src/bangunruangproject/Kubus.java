package bangunruangproject;

public class Kubus extends Persegi {
    private double luasKubus;
    private double volumeKubus;

    public double getLuasKubus() {
        return luasKubus;
    }

    public void setLuasKubus(double sisi) {
        this.luasKubus = 6 * sisi * sisi; // Perbaikan: Hitung luas persegi langsung di sini
    }

    public double getVolumeKubus() {
        return volumeKubus;
    }

    public void setVolumeKubus(double sisi) {
        this.volumeKubus = sisi * sisi * sisi; // Perbaikan: Rumus volume kubus yang benar
    }
}
