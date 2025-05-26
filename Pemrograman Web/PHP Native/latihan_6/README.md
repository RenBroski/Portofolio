# 🏢 Aplikasi Data Karyawan & Penggolongan

Aplikasi ini mencatat data karyawan dan menentukan gaji pokok berdasarkan jabatan secara otomatis.

## 🚀 Teknologi yang Digunakan

- PHP Native
- MySQL Database
- HTML5, CSS3

## 📋 Fitur Utama

- Input data karyawan: Nama, NIP, Jabatan
- Penentuan otomatis gaji pokok berdasarkan jabatan:
  - Manager → Rp10.000.000
  - Staff → Rp5.000.000
  - Cleaning Service → Rp2.000.000
- Tabel data karyawan
- Fitur cetak data karyawan
- Fitur hapus data

## 💾 Struktur Database

**Tabel: `karyawan`**
| Field   | Tipe               | Keterangan          |
|---------|--------------------|---------------------|
| id      | INT, AUTO_INCREMENT | Primary key        |
| nama    | VARCHAR(100)       | Nama karyawan       |
| nip     | VARCHAR(30)        | Nomor Induk Pegawai |
| jabatan | VARCHAR(50)        | Jabatan             |
| gaji    | INT                | Gaji Pokok          |

## 📂 Struktur File

- `index.php` – Input dan tampilkan data
- `proses.php` – Hitung & simpan data ke database
- `cetak.php` – Halaman khusus untuk mencetak data
- `hapus.php` – Menghapus data
- `koneksi.php` – Konfigurasi koneksi ke MySQL
