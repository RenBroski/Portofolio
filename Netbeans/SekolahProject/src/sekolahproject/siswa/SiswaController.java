/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sekolahproject.siswa;

import java.awt.HeadlessException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import sekolahproject.home.HomeView;
import sekolahproject.utilities.Koneksi;

/**
 *
 * @author User
 */
public class SiswaController {

    private final SiswaTableModel stm = new SiswaTableModel();

    public void setMaximumFrame(SiswaView sv) {
        try {
            sv.setMaximum(true);
        } catch (Exception error) {
            System.err.println("Error at SiswaController-setMaximumFrame, details: " + error.toString());
            JOptionPane.showMessageDialog(sv, "Error at SiwwController-setMaximumFrame, details: " + error.toString());
        }
    }

    public void setUndecorated(SiswaView sv) {
        try {
            sv.putClientProperty("JInternalFrame.isPalette", Boolean.TRUE);
            BasicInternalFrameUI basicInternalFrameUI = (BasicInternalFrameUI) sv.getUI();
            basicInternalFrameUI.setNorthPane(null);
            sv.repaint();
        } catch (Exception error) {
            System.err.println("Error at SiswaController-setUndecorated, details : " + error.toString());
            JOptionPane.showMessageDialog(sv, "Error at SiswaController-setUndecorated, details : " + error.toString());
        }
    }

    public void setTableModel(SiswaView sv) {
        try {
            SiswaView.tableSiswa.setModel(stm);
            
        } catch (Exception error) {
            System.err.println("Error at SiswaController-setTableModel, details : " + error.toString());
            JOptionPane.showMessageDialog(sv, "Error at SiswaController-setTableModel, details : " + error.toString());
        }
    }

    public void loadData(SiswaView sv) {
        try {
            java.sql.Connection konek = Koneksi.koneksiDB(); // Panggil koneksi dengan benar

            String sqlSelect = "SELECT * FROM tb_siswa ORDER BY nis ASC";

            java.sql.PreparedStatement stat = konek.prepareStatement(sqlSelect);
            ResultSet rs = stat.executeQuery(sqlSelect);

            List<Siswa> list = new ArrayList<>();

            while (rs.next()) {
                String jenkel = "";
                if (rs.getString("jenkel").equals("L")) {
                    jenkel = "Laki-Laki";
                } else if (rs.getString("jenkel").equals("P")) {
                    jenkel = "Perempuan";
                }

                Siswa s = new Siswa();
                s.setNis(rs.getString("nis"));
                s.setNama(rs.getString("nama"));
                s.setJenkel(jenkel);
                s.setKelas(rs.getString("kelas"));
                s.setJurusan(rs.getString("jurusan"));
                s.setAlamat(rs.getString("alamat"));

                list.add(s);
            }

            // Asumsi ada method setList di SiswaView atau model tabel
            stm.setList(list);
        } catch (Exception error) {
            System.err.println("Error at SiswaController-loadData, details: " + error.toString());
            JOptionPane.showMessageDialog(sv, "Error at SiswaController-loadData, details: " + error.toString());
        }
    }

    public void searchData(SiswaView sv) {
        try {
            java.sql.Connection konek = Koneksi.koneksiDB(); // Panggil koneksi dengan benar
            String parameter = "";
            if (sv.comboCari.getSelectedIndex() == 0) {
                // PARAMETER BERDASARKAN nis
                parameter = "nis";
            } else if (sv.comboCari.getSelectedIndex() == 1) {
                // PARAMETER BERDASARKAN nama
                parameter = "nama";
            } else if (sv.comboCari.getSelectedIndex() == 2) {
                // PARAMETER BERDASARKAN kelas
                parameter = "kelas";
            } else if (sv.comboCari.getSelectedIndex() == 3) {
                // PARAMETER BERDASARKAN jurusan
                parameter = "jurusan";
            }

            String keyword = sv.textCari.getText();

            // Corrected SQL query - removed the asterisk before parameter
            String sqlSelect = "SELECT * FROM tb_siswa WHERE " + parameter + " LIKE '%" + keyword + "%' ORDER BY nis ASC";

            java.sql.PreparedStatement stat = konek.prepareStatement(sqlSelect);
            ResultSet rs = stat.executeQuery(sqlSelect);

            List<Siswa> list = new ArrayList<>();

            while (rs.next()) {
                String jenkel = "";
                if (rs.getString("jenkel").equals("L")) {
                    jenkel = "Laki-Laki";
                } else if (rs.getString("jenkel").equals("P")) {
                    jenkel = "Perempuan";
                }

                Siswa s = new Siswa();
                s.setNis(rs.getString("nis"));
                s.setNama(rs.getString("nama"));
                s.setJenkel(jenkel);
                s.setKelas(rs.getString("kelas"));
                s.setJurusan(rs.getString("jurusan"));
                s.setAlamat(rs.getString("alamat"));

                list.add(s);
            }

            // Close resources
         

            // Set the list to the view or table model
            stm.setList(list);

        } catch (Exception error) {
            System.err.println("Error at SiswaController-searchData, details: " + error.toString());
            JOptionPane.showMessageDialog(sv, "Error at SiswaController-searchData, details: " + error.toString());
        }
    }

    public void refresh(SiswaView sv) {
        try {
            // Clear all input fields
            sv.labelStatus.setText("");
            sv.textNis.setText("");
            sv.textNama.setText("");
            sv.radioJenkel1.setSelected(true);
            sv.comboKelas.setSelectedIndex(0);
            sv.comboJurusan.setSelectedIndex(0);
            sv.textAreaAlamat.setText("");
            sv.comboCari.setSelectedIndex(0);
            sv.textCari.setText("");
            sv.tableSiswa.clearSelection();

            // Disable input fields
            sv.textNis.setEnabled(false);
            sv.textNama.setEnabled(false);
            sv.radioJenkel1.setEnabled(false);
            sv.radioJenkel2.setEnabled(false);
            sv.comboKelas.setEnabled(false);
            sv.comboJurusan.setEnabled(false);
            sv.textAreaAlamat.setEnabled(false);
            sv.buttonSimpan.setEnabled(false);
            sv.buttonUbah.setEnabled(false);

            // Enable navigation/search components
            sv.comboCari.setEnabled(true);
            sv.textCari.setEnabled(true);
            sv.tableSiswa.setEnabled(true);
            sv.buttonTambah.setEnabled(true);
            sv.buttonHapus.setEnabled(true);
            sv.buttonSegar.setEnabled(true);
            sv.buttonTutup.setEnabled(true);

            // Reload data
            loadData(sv);
        } catch (Exception error) {
            System.err.println("Error at SiswaController-refresh, details: " + error.toString());
            JOptionPane.showMessageDialog(sv, "Error at SiswaController-refresh, details: " + error.toString());
        }
    }

    public void tableSiswaAction(final SiswaView sv) {
        sv.tableSiswa.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int row = sv.tableSiswa.getSelectedRow();
                // nilai -1 menunjukkan bahwa tidak ada data yang dipilih pada tabel

                if (row != -1) {
                    // Asumsi ada model SiswaTableModel dengan method getList()
                    Siswa s = stm.get(row);

                    sv.textNis.setText(s.getNis());
                    sv.textNama.setText(s.getNama());

                    if (s.getJenkel().equals("Laki-Laki")) {
                        sv.radioJenkel1.setSelected(true);
                        sv.radioJenkel2.setSelected(false);
                    } else if (s.getJenkel().equals("Perempuan")) {
                        sv.radioJenkel1.setSelected(false);
                        sv.radioJenkel2.setSelected(true);
                    }

                    sv.comboKelas.setSelectedItem(s.getKelas());
                    sv.comboJurusan.setSelectedItem(s.getJurusan());
                    sv.textAreaAlamat.setText(s.getAlamat());

                    // Enable edit buttons
                    sv.buttonUbah.setEnabled(true);
                    sv.buttonHapus.setEnabled(true);
                }
            }
        });
    }

    private String getNisOtomatis(SiswaView sv) {
        String nis = "";
        try {
            java.sql.Connection konek = Koneksi.koneksiDB(); // Panggil koneksi dengan benar
            String sqlSelect = "SELECT nis FROM tb_siswa ORDER BY nis DESC";

            java.sql.PreparedStatement stat = konek.prepareStatement(sqlSelect);
            ResultSet rs = stat.executeQuery();

            if (rs.next()) {
                String nisOnDB = rs.getString("nis");
                // Extract numeric part from NIS (assuming format "WIS-XXXX")
                int nisTerakhir = Integer.parseInt(nisOnDB.substring(4));

                if (nisTerakhir >= 1 && nisTerakhir < 9) {
                    nis = "NIS-000" + (nisTerakhir + 1);
                } else if (nisTerakhir >= 9 && nisTerakhir < 99) {
                    nis = "NIS-00" + (nisTerakhir + 1);
                } else if (nisTerakhir >= 99 && nisTerakhir < 999) {
                    nis = "NIS-0" + (nisTerakhir + 1);
                } else if (nisTerakhir >= 999 && nisTerakhir < 9999) {
                    nis = "NIS-" + (nisTerakhir + 1);
                }
            } else {
                // If no records found, start with WIS-0001
                nis = "NIS-0001";
            }

            // Close resources
            rs.close();
            stat.close();

        } catch (SQLException | NumberFormatException error) {
            System.err.println("Error at SiswaController-getNisOtomatis, details: " + error.toString());
            JOptionPane.showMessageDialog(sv, "Error at SiswaController-getNisOtomatis, details: " + error.toString());
            nis = "WIS-0001"; // Return default value on error
        }
        return nis;
    }

    public void buttonTambahAction(SiswaView sv) {
        try {
            // Set status and clear fields
            sv.labelStatus.setText("INSERT");
            sv.textNis.setText(getNisOtomatis(sv));
            sv.textNama.setText("");
            sv.radioJenkel1.setSelected(true);
            sv.comboKelas.setSelectedIndex(0);
            sv.comboJurusan.setSelectedIndex(0);
            sv.textAreaAlamat.setText("");
            sv.comboCari.setSelectedIndex(0);
            sv.textCari.setText("");
            sv.tableSiswa.clearSelection();

            // Enable input fields
            sv.textNis.setEnabled(true);
            sv.textNama.setEnabled(true);
            sv.radioJenkel1.setEnabled(true);
            sv.radioJenkel2.setEnabled(true);
            sv.comboKelas.setEnabled(true);
            sv.comboJurusan.setEnabled(true);
            sv.textAreaAlamat.setEnabled(true);
            sv.buttonSimpan.setEnabled(true);
            sv.buttonBatal.setEnabled(true);

            // Disable other controls
            sv.comboCari.setEnabled(false);
            sv.textCari.setEnabled(false);
            sv.tableSiswa.setEnabled(false);
            sv.buttonTambah.setEnabled(false);
            sv.buttonUbah.setEnabled(false);
            sv.buttonHapus.setEnabled(false);
            sv.buttonSegar.setEnabled(false);
            sv.buttonTutup.setEnabled(false);

            // Set focus to nama field
            sv.textNama.requestFocus();
        } catch (Exception error) {
            System.err.println("Error at SiswaController-buttonTambahAction, details: " + error.toString());
            JOptionPane.showMessageDialog(sv, "Error at SiswaController-buttonTambahAction, details: " + error.toString());
        }
    }

    public void buttonUbahAction(SiswaView sv) {
        try {
            int row = sv.tableSiswa.getSelectedRow();
            // nilai -1 menunjukkan bahwa tidak ada data yang dipilih pada tabel
            if (row == -1) {
                JOptionPane.showMessageDialog(sv, "Silahkan pilih data yang ingin diubah");
            } else {

                // Set status to UPDATE
                sv.labelStatus.setText("UPDATE");

                // Enable input fields for editing
                sv.textNis.setEnabled(true);
                sv.textNama.setEnabled(true);
                sv.radioJenkel1.setEnabled(true);
                sv.radioJenkel2.setEnabled(true);
                sv.comboKelas.setEnabled(true);
                sv.comboJurusan.setEnabled(true);
                sv.textAreaAlamat.setEnabled(true);

                // Enable action buttons
                sv.buttonSimpan.setEnabled(true);
                sv.buttonBatal.setEnabled(true);

                // Disable search and navigation controls
                sv.comboCari.setEnabled(false);
                sv.textCari.setEnabled(false);
                sv.tableSiswa.setEnabled(false);
                sv.buttonTambah.setEnabled(false);
                sv.buttonUbah.setEnabled(false);
                sv.buttonHapus.setEnabled(false);
                sv.buttonSegar.setEnabled(false);
                sv.buttonTutup.setEnabled(false);

                // Set focus to nama field
                sv.textNama.requestFocus();
            }
        } catch (Exception error) {
            System.err.println("Error at SiswaController-buttonUbahAction, details: " + error.toString());
            JOptionPane.showMessageDialog(sv, "Error at SiswaController-buttonUbahAction, details: " + error.toString());
        }
    }

    public void buttonHapusAction(SiswaView sv) {
        try {
            // Get selected NIS from table
            int row = sv.tableSiswa.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(sv, "Silahkan pilih data yang ingin dihapus", "Peringatan", JOptionPane.WARNING_MESSAGE);
            } else {

                // Get NIS from selected row
                String nis = stm.get(row).getNis();

                // Confirmation dialog
                int konfirmasi = JOptionPane.showConfirmDialog(
                        sv,
                        "Apakah anda yakin ingin menghapus data NIS " + nis + "?",
                        "Konfirmasi",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE
                );

                if (konfirmasi == JOptionPane.YES_OPTION) {
                    // Delete from database
                    java.sql.Connection konek = Koneksi.koneksiDB(); // Panggil koneksi dengan benar
                    String sqlDelete = "DELETE FROM tb_siswa WHERE nis = ?";

                    java.sql.PreparedStatement ps = konek.prepareStatement(sqlDelete);
                    ps.setString(1, nis);

                    int isSuccess = ps.executeUpdate();

                    if (isSuccess == 0) {
                        JOptionPane.showMessageDialog(
                                sv,
                                "Data berhasil dihapus",
                                "Informasi",
                                JOptionPane.INFORMATION_MESSAGE);
                        refresh(sv); // Refresh table after deletion
                    } else {
                        JOptionPane.showMessageDialog(
                                sv,
                                "Data berhasil di hapus",
                                "Informasi",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                refresh(sv);
            }
        } catch (HeadlessException | SQLException error) {
            System.err.println("Error at SiswaController-buttonHapusAction, details: " + error.toString());
            JOptionPane.showMessageDialog(
                    sv,
                    "Error at SiswaController-buttonHapusAction, details: " + error.toString(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public void buttonSegarAction(SiswaView sv) {
        try {
            refresh(sv); // Memanggil method refresh untuk memperbarui data
            JOptionPane.showMessageDialog(sv, "Data berhasil diperbarui", "Informasi",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception error) {
            System.err.println("Error at SiswaController-buttonSegarAction, details: " + error.toString());
            JOptionPane.showMessageDialog(sv,
                    "Gagal memperbarui data: " + error.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void buttonTutupAction(SiswaView sv) {
        try {
            // Konfirmasi sebelum menutup
            int confirm = JOptionPane.showConfirmDialog(sv,
                    "Anda yakin ingin menutup form siswa?",
                    "Konfirmasi",
                    JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                sv.dispose(); // Menutup form siswa
                HomeView.menuItemSiswa.setEnabled(true);
            }
        } catch (Exception error) {
            System.err.println("Error at SiswaController-buttonTutupAction, details: " + error.toString());
            JOptionPane.showMessageDialog(sv,
                    "Gagal menutup form: " + error.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean validasiData(SiswaView sv) {
        try {
            // Validasi NIS
            if (sv.textNis.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(sv, "NIS tidak boleh kosong!",
                        "Peringatan", JOptionPane.WARNING_MESSAGE);
            }

            // Validasi Nama
            if (sv.textNama.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(sv, "Nama tidak boleh kosong!",
                        "Peringatan", JOptionPane.WARNING_MESSAGE);
                return false;
            }

            // Validasi Alamat
            if (sv.textAreaAlamat.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(sv, "Alamat tidak boleh kosong!",
                        "Peringatan", JOptionPane.WARNING_MESSAGE);
                return false;
            }

            // Validasi Jenis Kelamin
            if (!sv.radioJenkel1.isSelected() && !sv.radioJenkel2.isSelected()) {
                JOptionPane.showMessageDialog(sv, "Pilih jenis kelamin!",
                        "Peringatan", JOptionPane.WARNING_MESSAGE);
                return false;
            }

            // Jika semua validasi passed
            return true;

        } catch (Exception error) {
            System.err.println("Error at SiswaController-validasiData, details: " + error.toString());
            JOptionPane.showMessageDialog(sv,
                    "Terjadi kesalahan saat validasi data: " + error.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public void buttonSimpanAction(SiswaView sv) {
        java.sql.Connection konek = null;
        java.sql.PreparedStatement ps = null;

        try {
            // Validasi data sebelum disimpan
            if (!validasiData(sv)) {
                return;
            }

            // Set jenis kelamin
            String jenkel = "";
            if (sv.radioJenkel1.isSelected()) {
                jenkel = "L";
            } else if (sv.radioJenkel2.isSelected()) {
                jenkel = "P";
            }

            // Buat objek Siswa
            Siswa s = new Siswa();
            s.setNis(sv.textNis.getText());
            s.setNama(sv.textNama.getText());
            s.setJenkel(jenkel);
            s.setKelas(sv.comboKelas.getSelectedItem().toString());
            s.setJurusan(sv.comboJurusan.getSelectedItem().toString());
            s.setAlamat(sv.textAreaAlamat.getText());

            konek = Koneksi.koneksiDB();

            if (sv.labelStatus.getText().equals("INSERT")) {
                // Insert data baru
                String sqlInsert = "INSERT INTO tb_siswa (nis, nama, jenkel, kelas, jurusan, alamat) VALUES (?, ?, ?, ?, ?, ?)";
                ps = konek.prepareStatement(sqlInsert);
                ps.setString(1, s.getNis());
                ps.setString(2, s.getNama());
                ps.setString(3, s.getJenkel());
                ps.setString(4, s.getKelas());
                ps.setString(5, s.getJurusan());
                ps.setString(6, s.getAlamat());

            } else if (sv.labelStatus.getText().equals("UPDATE")) {
                // Update data yang ada
                String sqlUpdate = "UPDATE tb_siswa SET nama = ?, jenkel = ?, kelas = ?, jurusan = ?, alamat = ? WHERE nis = ?";
                ps = konek.prepareStatement(sqlUpdate);
                ps.setString(1, s.getNama());
                ps.setString(2, s.getJenkel());
                ps.setString(3, s.getKelas());
                ps.setString(4, s.getJurusan());
                ps.setString(5, s.getAlamat());
                ps.setString(6, s.getNis());
            }

            int isSuccess = ps.executeUpdate();

            if (isSuccess == 1) {
                String message = sv.labelStatus.getText().equals("INSERT") ? "disimpan" : "diubah";
                JOptionPane.showMessageDialog(sv, "Data berhasil " + message, "Informasi",
                        JOptionPane.INFORMATION_MESSAGE);
                refresh(sv);
            } else {
                String message = sv.labelStatus.getText().equals("INSERT") ? "disimpan" : "diubah";
                JOptionPane.showMessageDialog(sv, "Data gagal " + message, "Error",
                        JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException | HeadlessException error) {
            System.err.println("Error at SiswaController-buttonSimpanAction, details: " + error.toString());
            JOptionPane.showMessageDialog(sv,
                    "Terjadi kesalahan: " + error.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            // Tutup prepared statement dan koneksi di blok finally
            try {
                if (ps != null) {
                    ps.close();
                }
                if (konek != null) {
                    konek.close();
                }
            } catch (SQLException ex) {
                System.err.println("Error closing resources: " + ex.toString());
            }
        }
    }

    public void buttonBatalAction(SiswaView sv) {
        try {
            refresh(sv);
        } catch (Exception error) {
            System.err.println("Error at SiswaController-buttonBatalAction, details: " + error.toString());
            JOptionPane.showMessageDialog(sv,
                    "Terjadi kesalahan saat membatalkan operasi.\nSilakan coba lagi.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}