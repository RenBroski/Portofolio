/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author User
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Koneksi {
    private static Connection koneksi;
    
    public static Connection getKoneksi() {
        if (koneksi == null) {
            try {
                // Load driver MySQL
                Class.forName("com.mysql.cj.jdbc.Driver");
                
                String url = "jdbc:mysql://localhost:3306/latihannetbean";
                String user = "root";
                String pass = "";
                
                koneksi = DriverManager.getConnection(url, user, pass);
                System.out.println("Koneksi Berhasil!");
            } catch (ClassNotFoundException e) {
                JOptionPane.showMessageDialog(null, 
                    "Driver MySQL tidak ditemukan!\nError: " + e.getMessage(),
                    "Error Driver",
                    JOptionPane.ERROR_MESSAGE);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, 
                    "Koneksi Gagal!\nError: " + e.getMessage(),
                    "Error Koneksi",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
        return koneksi;
    }
}


