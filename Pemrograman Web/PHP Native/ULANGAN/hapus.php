<?php 
require 'koneksi.php';

// mengambil id_perjalanan
$id_perjalanan = $_GET["id_perjalanan"];

// Logika bila berhasil dihapus
if (hapus($id_perjalanan) > 0) {
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