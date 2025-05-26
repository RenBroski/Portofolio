/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sekolahproject.utilities;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class Koneksi {
    public static Connection koneksiDB() {
        Connection mysqlkonek = null;
        try {
            String url = "jdbc:mysql://localhost:3306/db_sekolah"; 
            String user = "root";
            String pass = "";
            mysqlkonek = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal koneksi: " + e.getMessage());
        }
        return mysqlkonek;
    }
}