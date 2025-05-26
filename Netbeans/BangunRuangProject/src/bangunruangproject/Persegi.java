package bangunruangproject;

/**
 *
 * @author User
 */
public class Persegi {
    
    private double luasPersegi;
    private double kelilingPersegi;

    public double getLuasPersegi() {
        return luasPersegi;
    }

    public void setLuasPersegi(double sisi) {
        this.luasPersegi = sisi * sisi;
        this.setKelilingPersegi(sisi); // Memanggil metode untuk menghitung keliling
    }

    public double getKelilingPersegi() {
        return kelilingPersegi;
    }

    public void setKelilingPersegi(double sisi) {
        this.kelilingPersegi = 4 * sisi;
    }
}
