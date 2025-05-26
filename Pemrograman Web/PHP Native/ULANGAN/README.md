# 📌 Aplikasi Catatan Perjalanan

Aplikasi berbasis web untuk mencatat data pribadi dan riwayat perjalanan pengguna, biasanya digunakan dalam konteks pelacakan saat pandemi.

## 🚀 Teknologi yang Digunakan

- PHP Native
- MySQL Database
- HTML5, CSS3

## 📋 Fitur Utama

- Input data pribadi: Nama, NIK, Jenis Kelamin, Alamat
- Input data perjalanan: Tanggal, Waktu, Lokasi, Suhu Tubuh
- Validasi input pengguna
- Penyimpanan data ke database MySQL
- Tabel daftar data perjalanan
- Fitur hapus data

## 💾 Struktur Database

**Tabel: `catatan_perjalanan`**
| Field         | Tipe             | Keterangan           |
|---------------|------------------|----------------------|
| id            | INT, AUTO_INCREMENT | Primary key       |
| nama          | VARCHAR(100)     | Nama pengguna        |
| nik           | VARCHAR(20)      | Nomor Induk Kependudukan |
| jenis_kelamin | ENUM('Laki-laki','Perempuan') | Jenis kelamin |
| alamat        | TEXT             | Alamat pengguna      |
| tanggal       | DATE             | Tanggal perjalanan   |
| waktu         | TIME             | Waktu perjalanan     |
| lokasi        | VARCHAR(100)     | Lokasi dikunjungi    |
| suhu          | VARCHAR(10)      | Suhu tubuh           |

## 📂 Struktur File

- `index.php` – Halaman form input
- `simpan.php` – Proses simpan data ke database
- `data.php` – Menampilkan semua data
- `hapus.php` – Menghapus data
- `koneksi.php` – Konfigurasi koneksi database
