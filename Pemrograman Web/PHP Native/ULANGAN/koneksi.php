<?php 
// koneksi ke database
$conn = mysqli_connect("localhost","root","","ulanganweb");

//function yang membuat mengambil data dari database kemudian mendeklaraasikan array membuat perulangan untuk mengambil data yang ada di tiap table
//mengembalikan nilainya
function query ($querys){
    global $conn;
    $result = mysqli_query($conn,$querys);
    $rows = [];
    while ($row = mysqli_fetch_assoc($result)){
        $rows[] = $row;
    }
return $rows;
}

function ubah($data){
    global $conn;

    // ambil data dari form
    $id_perjalanan = htmlspecialchars($data["id_perjalanan"]);
    $destinasi = htmlspecialchars($data["destinasi"]);
    $alat_transportasi = htmlspecialchars($data["alat_transportasi"]);
    $durasi_perjalanan = htmlspecialchars($data["durasi_perjalanan"]);
    $biaya_tiket = htmlspecialchars($data["biaya_tiket"]);

    // bikin query mengubah data
    $querys = "UPDATE perjalanan SET
                destinasi = '$destinasi',
                alat_transportasi = '$alat_transportasi',
                durasi_perjalanan = '$durasi_perjalanan',
                biaya_tiket = '$biaya_tiket'
                WHERE id_perjalanan = '$id_perjalanan'";
    mysqli_query($conn, $querys);

    // mengembalikan nilai untuk alert
    return mysqli_affected_rows($conn);
}

function hapus ($id_perjalanan){
    global $conn;
    mysqli_query($conn, "DELETE FROM perjalanan WHERE id_perjalanan = $id_perjalanan");
    return mysqli_affected_rows($conn); 

}

function tambah($data) {
    global $conn;
    
    // Ambil data dari form
    $destinasi = htmlspecialchars($data["destinasi"]);
    $alat_transportasi = htmlspecialchars($data["alat_transportasi"]);
    $durasi_perjalanan = htmlspecialchars($data["durasi_perjalanan"]);
    $biaya_tiket = htmlspecialchars($data["biaya_tiket"]);
    
    // Buat query untuk menambah data
    $query = "INSERT INTO perjalanan (destinasi, alat_transportasi, durasi_perjalanan, biaya_tiket) 
              VALUES ('$destinasi', '$alat_transportasi', '$durasi_perjalanan', '$biaya_tiket')";
    
    mysqli_query($conn, $query);
    
    // Kembalikan jumlah baris yang terpengaruh
    return mysqli_affected_rows($conn);
}

?>