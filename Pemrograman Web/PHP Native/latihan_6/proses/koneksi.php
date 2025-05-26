<?php
$servername = "localhost"; // nama server database
$username = "root"; // username database (default: root)
$password = ""; // password database (default kosong)
$dbname = "latihan6"; // nama database yang dibuat

// Membuat koneksi ke database
$conn = mysqli_connect($servername, $username, $password, $dbname);

// Mengecek koneksi
if (!$conn) {
    die("Koneksi gagal: " . mysqli_connect_error());
}
?>
