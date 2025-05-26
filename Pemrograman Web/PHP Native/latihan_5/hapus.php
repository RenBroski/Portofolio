<?php 
require 'koneksi.php';

// mengambil id
$nis = $_GET["nis"];

// Logika bila berhasil dihapus
if (hapus($nis) > 0) {
    echo "
        <script>
        alert('Data berhasil dihapus');
        document.location.href = 'index.php';
        </script>
        ";
} else {
    echo "
        <script>
        alert('Data gagal dihapus');
        document.location.href = 'index.php';
        </script>
        ";
}
?>