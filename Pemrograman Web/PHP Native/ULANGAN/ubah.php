<?php 
require 'koneksi.php';

// ambil data id_perjalanan di URL
$id_perjalanan = $_GET["id_perjalanan"];

// mengambil data perjalanan yang idnya terkirim
$perjalanan = query("SELECT * FROM perjalanan WHERE id_perjalanan = $id_perjalanan")[0];

// bikin logic jika tombol nya dipencet
if ( isset($_POST["submit"]) ){
    
    // cek keberhasilan diubah atau tidak
    if( ubah($_POST) > 0){
        echo "
        <script>
        alert('Data perjalanan berhasil diubah');
        document.location.href = 'index.php';
        </script>
        ";
    }else{
        echo "
        <script>
        alert('Data perjalanan gagal diubah');
        document.location.href = 'index.php';
        </script>
        ";
    }
}

?>