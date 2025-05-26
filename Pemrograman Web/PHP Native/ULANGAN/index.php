<?php 
require 'koneksi.php';
$perjalanan = query("SELECT * FROM perjalanan");

// Logika untuk menambah data perjalanan
if(isset($_POST['tambah'])) {
    // Dapatkan ID terakhir dari tabel perjalanan
    $last_id_query = mysqli_query($conn, "SELECT MAX(id_perjalanan) as last_id FROM perjalanan");
    $last_id_result = mysqli_fetch_assoc($last_id_query);
    $last_id = $last_id_result['last_id'];

    // Jika tabel kosong, mulai dari 1, jika tidak, lanjutkan dari ID terakhir
    $next_id = ($last_id) ? $last_id + 1 : 1;

    // Set ID berikutnya
    mysqli_query($conn, "ALTER TABLE perjalanan AUTO_INCREMENT = $next_id");
    
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

// Logika untuk mengubah data perjalanan
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
    <title>Data Perjalanan</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .navbar {
            background-color: #007bff;
        }
        .card {
            border: none;
            box-shadow: 0 0.5rem 1rem rgba(0, 0, 0, 0.15);
        }
        .btn-success, .btn-info, .btn-warning, .btn-danger {
            border-radius: 20px;
        }
        .modal-content {
            border-radius: 15px;
        }
        .table {
            border-radius: 10px;
            overflow: hidden;
        }
    </style>
</head>
<body>
    
    <nav class="navbar navbar-expand-lg navbar-dark">
        <div class="container">
            <a class="navbar-brand" href="#"><i class="fas fa-plane-departure me-2"></i>Data Perjalanan</a>
        </div>
    </nav>

    <div class="container mt-5">
        <div class="row mb-4">
            <div class="col-md-6">
                <button type="button" class="btn btn-success me-2" data-bs-toggle="modal" data-bs-target="#tambahPerjalananModal">
                    <i class="fas fa-plus-circle me-2"></i>Tambah Data
                </button>
                <a href="cetak.php" class="btn btn-info" target="_blank">
                    <i class="fas fa-print me-2"></i>Cetak
                </a>
            </div>
        </div>

        <!-- Modal untuk menambah data perjalanan -->
        <div class="modal fade" id="tambahPerjalananModal" tabindex="-1" aria-labelledby="tambahPerjalananModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header bg-success text-white">
                        <h5 class="modal-title" id="tambahPerjalananModalLabel"><i class="fas fa-plus-circle me-2"></i>Tambah Data Perjalanan</h5>
                        <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form action="" method="post">
                            <div class="mb-3">
                                <input type="text" class="form-control" name="destinasi" placeholder="Destinasi" required>
                            </div>
                            <div class="mb-3">
                                <select class="form-select" name="alat_transportasi" required>
                                    <option value="" disabled selected>Pilih Alat Transportasi</option>
                                    <option value="Mobil">Mobil</option>
                                    <option value="Motor">Motor</option>
                                    <option value="Pesawat">Pesawat</option>
                                    <option value="Kapal">Kapal</option>
                                    <option value="Angkutan Umum">Angkutan Umum</option>
                                </select>
                            </div>
                            <div class="mb-3">
                                <input type="text" class="form-control" name="durasi_perjalanan" placeholder="Durasi Perjalanan" required>
                            </div>
                            <div class="mb-3">
                                <input type="number" class="form-control" name="biaya_tiket" placeholder="Biaya Tiket" required>
                            </div>
                            <button type="submit" name="tambah" class="btn btn-success w-100">Tambah Data Perjalanan</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <!-- Modal untuk mengubah data perjalanan -->
        <div class="modal fade" id="ubahPerjalananModal" tabindex="-1" aria-labelledby="ubahPerjalananModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header bg-primary text-white">
                        <h5 class="modal-title" id="ubahPerjalananModalLabel"><i class="fas fa-edit me-2"></i>Ubah Data Perjalanan</h5>
                        <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form action="" method="post">
                            <input type="hidden" name="id_perjalanan" id="ubahIdPerjalanan">
                            <div class="mb-3">
                                <input type="text" class="form-control" name="destinasi" id="ubahDestinasi" placeholder="Destinasi" required>
                            </div>
                            <div class="mb-3">
                                <input type="text" class="form-control" name="alat_transportasi" id="ubahAlatTransportasi" placeholder="Alat Transportasi" required>
                            </div>
                            <div class="mb-3">
                                <input type="text" class="form-control" name="durasi_perjalanan" id="ubahDurasiPerjalanan" placeholder="Durasi Perjalanan" required>
                            </div>
                            <div class="mb-3">
                                <input type="number" class="form-control" name="biaya_tiket" id="ubahBiayaTiket" placeholder="Biaya Tiket" required>
                            </div>
                            <button type="submit" name="ubah" class="btn btn-primary w-100">Ubah Data Perjalanan</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <div class="card shadow">
            <div class="card-body">
                <table id="tablePerjalanan" class="table table-hover">
                    <thead class="table-primary">
                        <tr>
                            <th>ID</th>
                            <th>Destinasi</th>
                            <th>Transportasi</th>
                            <th>Durasi</th>
                            <th>Biaya</th>
                            <th>Aksi</th>
                        </tr>
                    </thead>
                    <tbody>
                    <?php foreach($perjalanan as $row) : ?>
                    <tr>
                        <td><?= $row["id_perjalanan"];?></td>
                        <td><?= $row["destinasi"];?></td>
                        <td><?= $row["alat_transportasi"];?></td>
                        <td><?= $row["durasi_perjalanan"];?></td>
                        <td>Rp <?= number_format($row["biaya_tiket"], 0, ',', '.');?></td>
                        <td>
                            <button type="button" class="btn btn-warning btn-sm" data-bs-toggle="modal" data-bs-target="#ubahPerjalananModal" 
                                onclick="setUbahData('<?= $row['id_perjalanan']; ?>', '<?= $row['destinasi']; ?>', '<?= $row['alat_transportasi']; ?>', '<?= $row['durasi_perjalanan']; ?>', '<?= $row['biaya_tiket']; ?>')">
                                <i class="fas fa-edit"></i> Ubah
                            </button>
                            <a href="hapus.php?id_perjalanan=<?= $row["id_perjalanan"]; ?>" class="btn btn-danger btn-sm" onclick="return confirm('Apakah Anda yakin ingin menghapus data ini?');">
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
    function setUbahData(id_perjalanan, destinasi, alat_transportasi, durasi_perjalanan, biaya_tiket) {
        document.getElementById('ubahIdPerjalanan').value = id_perjalanan;
        document.getElementById('ubahDestinasi').value = destinasi;
        document.getElementById('ubahAlatTransportasi').value = alat_transportasi;
        document.getElementById('ubahDurasiPerjalanan').value = durasi_perjalanan;
        document.getElementById('ubahBiayaTiket').value = biaya_tiket;
    }
    </script>

    <script src="https://code.jquery.com/jquery-3.7.1.js"></script>
    <script src="https://cdn.datatables.net/2.1.5/js/dataTables.js"></script>
    <script src="https://cdn.datatables.net/2.1.5/js/dataTables.bootstrap5.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
    <script>
    $(document).ready(function() {
        $('#tablePerjalanan').DataTable({
            "language": {
                "lengthMenu": "Tampilkan _MENU_ data per halaman",
                "zeroRecords": "Data tidak ditemukan",
                "info": "Menampilkan halaman _PAGE_ dari _PAGES_",
                "infoEmpty": "Tidak ada data yang tersedia",
                "infoFiltered": "(difilter dari _MAX_ total data)",
                "search": "Cari:",
                "paginate": {
                    "first": "<<",
                    "last": ">>",
                    "next": "Next",
                    "previous": "Previos"
                }
            }
        });
    });
    </script>

</body> 
</html>