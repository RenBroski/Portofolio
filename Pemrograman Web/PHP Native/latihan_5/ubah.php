<?php 
require 'koneksi.php';



// ambil data nis di URL
$nis = $_GET["nis"];

// mengambil data mahasiswa yang idnya terkirim
$sws = query("SELECT * FROM siswa WHERE nis = $nis")[0];


// bikin logic jika tombol nya dipencet
if ( isset($_POST["submit"]) ){
    
    // cek keberhasilan diubah atau tidak
    if( ubah($_POST) > 0){
        echo "
        <script>
        alert('Data Berhasil diubah');
        document.location.href = 'index.php';
        </script>
        ";
    }else{
        echo "
        <script>
        alert('Data gagal diubah');
        document.location.href = 'index.php';
        </script>
        ";
    }
}

?>