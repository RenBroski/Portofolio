/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author User
 */
import java.sql.Connection; 
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class DataPelanggan extends javax.swing.JFrame {
    private DefaultTableModel tabmode;
    private Connection koneksi;
    
    /**
     * Creates new form DataPelanggan
     */
    public DataPelanggan() {
        initComponents();
        koneksi = Koneksi.getKoneksi();
        kosongkanForm();
        tampildata();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("INPUT DATA PELANGGAN");

        jLabel3.setText("Kode Pelanggan");

        jTextField1.setText("jTextField1");

        jLabel4.setText("Nama Pelanggan");

        jTextField2.setText("jTextField1");

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable2);

        jButton3.setText("DELETE");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton1.setText("RESET");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("SAVE");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton4.setText("EDIT");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(97, 97, 97)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3)
                        .addGap(18, 18, 18)
                        .addComponent(jButton4)))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton1)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        hapusData();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        kosongkanForm();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        simpanData();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        updateData();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        // TODO add your handling code here:
        // Ketika baris tabel diklik
        int baris = jTable2.getSelectedRow();
        if (baris != -1) {
            jTextField1.setText(jTable2.getValueAt(baris, 0).toString());
            jTextField2.setText(jTable2.getValueAt(baris, 1).toString());
        }
    }//GEN-LAST:event_jTable2MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DataPelanggan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DataPelanggan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DataPelanggan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DataPelanggan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DataPelanggan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables

    private void kosongkanForm() {
        jTextField1.setText("");  // Kode Pelanggan
        jTextField2.setText("");  // Nama
        jTextField1.requestFocus();
    }
    
    public void tampildata() {
        Object[] baris = {"Kode Pelanggan", "Nama"};
        tabmode = new DefaultTableModel(null, baris);
        jTable2.setModel(tabmode);
        
        String sql = "SELECT * FROM tb_pelanggang";
        try {
            Statement stmt = koneksi.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String[] data = {
                    rs.getString("kode_pelanggan"),
                    rs.getString("nama")
                };
                tabmode.addRow(data);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal menampilkan data: " + e.getMessage());
        }
    }
    
    private void simpanData() {
        try {
            // Validasi input
            if (jTextField1.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Kode Pelanggan tidak boleh kosong!");
                jTextField1.requestFocus();
                return;
            }
            if (jTextField2.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nama tidak boleh kosong!");
                jTextField2.requestFocus();
                return;
            }
            
            // Simpan ke database
            String sql = "INSERT INTO tb_pelanggang (kode_pelanggan, nama) VALUES (?, ?)";
            PreparedStatement ps = koneksi.prepareStatement(sql);
            ps.setString(1, jTextField1.getText().trim());
            ps.setString(2, jTextField2.getText().trim());
            ps.executeUpdate();
            
            JOptionPane.showMessageDialog(this, "Data berhasil disimpan!");
            tampildata();
            kosongkanForm();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal menyimpan: " + e.getMessage());
        }
    }
    
    private void updateData() {
        try {
            // Validasi input
            if (jTextField1.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Kode Pelanggan tidak boleh kosong!");
                return;
            }
            if (jTextField2.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nama tidak boleh kosong!");
                return;
            }

            // Update data
            String sql = "UPDATE tb_pelanggang SET nama=? WHERE kode_pelanggan=?";
            PreparedStatement ps = koneksi.prepareStatement(sql);
            ps.setString(1, jTextField2.getText().trim());
            ps.setString(2, jTextField1.getText().trim());
            ps.executeUpdate();

            JOptionPane.showMessageDialog(this, "Data berhasil diupdate!");
            tampildata();
            kosongkanForm();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal update: " + e.getMessage());
        }
    }
    
    private void hapusData() {
        try {
            if (jTextField1.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Pilih data yang akan dihapus!");
                return;
            }

            int konfirmasi = JOptionPane.showConfirmDialog(this, 
                "Yakin ingin menghapus data ini?", 
                "Konfirmasi", 
                JOptionPane.YES_NO_OPTION);

            if (konfirmasi == JOptionPane.YES_OPTION) {
                String sql = "DELETE FROM tb_pelanggang WHERE kode_pelanggan=?";
                PreparedStatement ps = koneksi.prepareStatement(sql);
                ps.setString(1, jTextField1.getText().trim());
                ps.executeUpdate();

                JOptionPane.showMessageDialog(this, "Data berhasil dihapus!");
                tampildata();
                kosongkanForm();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal menghapus: " + e.getMessage());
        }
    }
    
    
    

}
