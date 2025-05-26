/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package bukutelepon;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class koneksi {
    private static Connection mysqlkonek;

    public static Connection koneksiDB() throws SQLException {
        if (mysqlkonek == null) {
            try {
                String url = "jdbc:mysql://localhost:3306/bukutelepon"; // Sesuaikan dengan host dan port
                String user = "root"; // 
                String password = ""; // 

                DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                mysqlkonek = (Connection)
                DriverManager.getConnection(url, user, password);
                System.out.println("Koneksi ke database berhasil!");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Koneksi gagal ");
            }
        }
        return mysqlkonek;
    }
}
