<?php 
require 'koneksi.php';
$siswa = query("SELECT * FROM siswa");

// Logika untuk menambah data siswa
if(isset($_POST['tambah'])) {
    if(tambah($_POST) > 0) {
        echo "<script>
            alert('Data berhasil ditambahkan');
            document.location.href = 'index.php';
        </script>";
    } else {
        echo "<script>
            alert('Data gagal ditambahkan');
            document.location.href = 'index.php';
        </script>";
    }
}

// Logika untuk mengubah data siswa
if(isset($_POST['ubah'])) {
    if(ubah($_POST) > 0) {
        echo "<script>
            alert('Data berhasil diubah');
            document.location.href = 'index.php';
        </script>";
    } else {
        echo "<script>
            alert('Data gagal diubah');
            document.location.href = 'index.php';
        </script>";
    }
}

?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tabel Latihan 5</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body class="bg-light">
    
    <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
        <div class="container">
            <a class="navbar-brand" href="#">Latihan 5</a>
        </div>
    </nav>

    <div class="container mt-5">
        <div class="row mb-4">
            <div class="col-md-6">
                <button type="button" class="btn btn-success me-2" data-bs-toggle="modal" data-bs-target="#tambahSiswaModal">
                    <i class="fas fa-plus-circle me-2"></i>Tambah Data Siswa
                </button>
                <a href="cetak.php" class="btn btn-info" target="_blank">
                    <i class="fas fa-print me-2"></i>Cetak
                </a>
            </div>
        </div>

        <!-- Modal untuk menambah data siswa -->
        <div class="modal fade" id="tambahSiswaModal" tabindex="-1" aria-labelledby="tambahSiswaModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header bg-success text-white">
                        <h5 class="modal-title" id="tambahSiswaModalLabel">Tambah Data Siswa</h5>
                        <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form action="" method="post">
                            <div class="mb-3">
                                <input type="text" class="form-control" name="nis" placeholder="NIS" required>
                            </div>
                            <div class="mb-3">
                                <input type="text" class="form-control" name="nama" placeholder="Nama" required>
                            </div>
                            <div class="mb-3">
                                <input type="text" class="form-control" name="kelas" placeholder="Kelas" required>
                            </div>
                            <div class="mb-3">
                                <select class="form-select" name="jenis_kelamin" required>
                                    <option value="">Pilih Jenis Kelamin</option>
                                    <option value="L">Laki-laki</option>
                                    <option value="P">Perempuan</option>
                                </select>
                            </div>
                            <button type="submit" name="tambah" class="btn btn-success w-100">Tambah Data Siswa</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <!-- Modal untuk mengubah data siswa -->
        <div class="modal fade" id="ubahSiswaModal" tabindex="-1" aria-labelledby="ubahSiswaModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header bg-primary text-white">
                        <h5 class="modal-title" id="ubahSiswaModalLabel">Ubah Data Siswa</h5>
                        <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form action="" method="post">
                            <input type="hidden" name="nis" id="ubahNis">
                            <div class="mb-3">
                                <input type="text" class="form-control" name="nama" id="ubahNama" placeholder="Nama" required>
                            </div>
                            <div class="mb-3">
                                <input type="text" class="form-control" name="kelas" id="ubahKelas" placeholder="Kelas" required>
                            </div>
                            <div class="mb-3">
                                <select class="form-select" name="jenis_kelamin" id="ubahJenisKelamin" required>
                                    <option value="">Pilih Jenis Kelamin</option>
                                    <option value="L">Laki-laki</option>
                                    <option value="P">Perempuan</option>
                                </select>
                            </div>
                            <button type="submit" name="ubah" class="btn btn-primary w-100">Ubah Data Siswa</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <div class="card shadow">
            <div class="card-body">
                <table id="tableSiswa" class="table table-hover">
                    <thead class="table-primary">
                        <tr>
                            <th>NIS</th>
                            <th>Nama</th>
                            <th>Kelas</th>
                            <th>Jenis Kelamin</th>
                            <th>Aksi</th>
                        </tr>
                    </thead>
                    <tbody>
                    <?php foreach($siswa as $row) : ?>
                    <tr>
                        <td><?= $row["nis"];?></td>
                        <td><?= $row["nama"];?></td>
                        <td><?= $row["kelas"];?></td>
                        <td><?= $row["jenis_kelamin"] == 'L' ? 'Laki-laki' : 'Perempuan';?></td>
                        <td>
                            <button type="button" class="btn btn-warning btn-sm" data-bs-toggle="modal" data-bs-target="#ubahSiswaModal" 
                                onclick="setUbahData('<?= $row['nis']; ?>', '<?= $row['nama']; ?>', '<?= $row['kelas']; ?>', '<?= $row['jenis_kelamin']; ?>')">
                                <i class="fas fa-edit"></i> Ubah
                            </button>
                            <a href="hapus.php?nis=<?= $row["nis"]; ?>" class="btn btn-danger btn-sm" onclick="return confirm('Apakah Anda yakin ingin menghapus data ini?');">
                                <i class="fas fa-trash-alt"></i> Hapus
                            </a>
                        </td>
                    </tr>
                    <?php endforeach; ?>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
   
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script>
    function setUbahData(nis, nama, kelas, jenisKelamin) {
        document.getElementById('ubahNis').value = nis;
        document.getElementById('ubahNama').value = nama;
        document.getElementById('ubahKelas').value = kelas;
        document.getElementById('ubahJenisKelamin').value = jenisKelamin;
    }
    </script>

    <script src="https://code.jquery.com/jquery-3.7.1.js"></script>
     <script src="https://cdn.datatables.net/2.1.5/js/dataTables.js"></script>
     <script src="https://cdn.datatables.net/2.1.5/js/dataTables.bootstrap5.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
    <script>new DataTable("#tableSiswa");</script>
    

</body> 
</html>