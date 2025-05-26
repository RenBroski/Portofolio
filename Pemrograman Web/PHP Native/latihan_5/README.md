# 🧑‍🎓 Aplikasi CRUD Data Siswa

Aplikasi ini digunakan untuk mengelola data siswa (tambah, edit, hapus) dengan penyimpanan data di MySQL.

## 🚀 Teknologi yang Digunakan

- PHP Native
- MySQL Database
- HTML5, CSS3

## 📋 Fitur Utama

- Tambah data siswa
- Edit dan hapus data siswa
- Menampilkan data dalam tabel
- Validasi form input

## 💾 Struktur Database

**Tabel: `siswa`**
| Field  | Tipe              | Keterangan        |
|--------|-------------------|-------------------|
| id     | INT, AUTO_INCREMENT | Primary key     |
| nama   | VARCHAR(100)      | Nama siswa        |
| nis    | VARCHAR(20)       | Nomor Induk Siswa |
| kelas  | VARCHAR(20)       | Kelas siswa       |
| alamat | TEXT              | Alamat siswa      |

## 📂 Struktur File

- `index.php` – Menampilkan form dan tabel data siswa
- `proses.php` – Proses simpan data siswa
- `edit.php` – Form dan proses edit data
- `hapus.php` – Menghapus data siswa
- `koneksi.php` – Koneksi ke database
