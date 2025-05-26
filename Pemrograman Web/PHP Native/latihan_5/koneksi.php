<?php 
// koneksi ke database
$conn = mysqli_connect("localhost","root","","db_latihan5");

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
    $nis = htmlspecialchars($data["nis"]);
    $nama = htmlspecialchars($data["nama"]);
    $kelas = htmlspecialchars($data["kelas"]);
    $jenis_kelamin = htmlspecialchars($data["jenis_kelamin"]);

    // bikin query mengubah data
    $querys = "UPDATE siswa SET
                nama = '$nama',
                kelas = '$kelas',
                jenis_kelamin = '$jenis_kelamin'
                WHERE nis = '$nis'";
    mysqli_query($conn, $querys);

    // mengembalikan nilai untuk alert
    return mysqli_affected_rows($conn);
}

function hapus ($nis){
    global $conn;
    mysqli_query($conn, "DELETE FROM siswa WHERE nis = $nis");
    return mysqli_affected_rows($conn); 

}

function tambah($data) {
    global $conn;
    
    // Ambil data dari form
    $nis = htmlspecialchars($data["nis"]);
    $nama = htmlspecialchars($data["nama"]);
    $kelas = htmlspecialchars($data["kelas"]);
    $jenis_kelamin = htmlspecialchars($data["jenis_kelamin"]);
    
    // Buat query untuk menambah data
    $query = "INSERT INTO siswa (nis, nama, kelas, jenis_kelamin) 
              VALUES ('$nis', '$nama', '$kelas', '$jenis_kelamin')";
    
    mysqli_query($conn, $query);
    
    // Kembalikan jumlah baris yang terpengaruh
    return mysqli_affected_rows($conn);
}

?>