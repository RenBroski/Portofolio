<?php
include 'koneksi.php';
include '../assets/templates/navbar.php';
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Penggajian Karyawan</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<script>
    function hitung() {
        let golongan = document.getElementById('golongan').value;
        let gapok = parseInt(document.getElementById('gaji_pokok').value) || 0;
        let tunjab = parseInt(document.getElementById('tunjangan_jabatan').value) || 0;
        let tunjangan1 = parseInt(document.getElementById('tunjangan_nikah').value) || 0;

        // Ambil nilai dari radio button tunjangan anak yang dipilih
        let tunjangan2 = parseInt(document.querySelector('input[name="tunjangan_anak"]:checked').value) || 0;

        let bpjs = 340000;
        let pendapatan = gapok + tunjab + tunjangan1 + tunjangan2;
        let bersih = 0;
        let potongan = 0;

        const arr = golongan.split('/');
        let gol = arr[0];

        if (gol == "III") { 
            potongan = pendapatan * 0.05;
        } else if (gol == "IV") {
            potongan = pendapatan * 0.15;
        }
        bersih = pendapatan - (potongan + bpjs);

        // Tampilkan hasil di tabel
        document.getElementById('gaji').innerHTML = "Rp. " + new Intl.NumberFormat().format(gapok);
        document.getElementById('jabatan').innerHTML = "Rp. " + new Intl.NumberFormat().format(tunjab);
        document.getElementById('nikah').innerHTML = "Rp. " + new Intl.NumberFormat().format(tunjangan1);
        document.getElementById('anak').innerHTML = "Rp. " + new Intl.NumberFormat().format(tunjangan2);
        document.getElementById('bpjs').innerHTML = "Rp. " + new Intl.NumberFormat().format(bpjs);
        document.getElementById('pajak').innerHTML = "Rp. " + new Intl.NumberFormat().format(potongan);
        document.getElementById('gaji_bersih').innerHTML = "Rp. " + new Intl.NumberFormat().format(bersih);

        console.log("PENDAPATAN : " + pendapatan);
        console.log("POTONGAN : " + potongan);
        console.log("GAJI BERSIH : " + bersih);
    }
</script>

<div class="container px-4 mt-4">
    <h3>Penggajian Karyawan</h3>
    <div class="card">
        <div class="card-header bg-secondary text-light">
            Buat Slip Gaji
        </div>
        <div class="card-body">
            <form action="">
                <label for="">Bulan</label>
                <select class="form-control mb-2">
                    <option value="Januari">Januari</option>
                    <option value="Februari">Februari</option>
                    <option value="Maret">Maret</option>
                    <option value="April">April</option>
                    <option value="Mei">Mei</option>
                    <option value="Juni">Juni</option>
                    <option value="Juli">Juli</option>
                    <option value="Agustus">Agustus</option>
                    <option value="September">September</option>
                    <option value="Oktober">Oktober</option>
                    <option value="November">November</option>
                </select>
                <label for="">Nama Karyawan</label>
                <select class="form-control mb-2" name="karyawan_id" id="">
                    <?php
                    $sql = "SELECT * FROM karyawan JOIN golongan ON karyawan.kode_golongan = golongan.kode_golongan";
                    $result = $conn->query($sql);
                    while ($data = $result->fetch_array(MYSQLI_ASSOC)):
                    ?>
                    <option value="<?= $data['nip'] ?>"><?= $data['nama'] ?> (Golongan : <?= $data['golongan'] ?>)</option>
                    <?php
                    endwhile;
                    ?>
                </select>

                <input type="hidden" name="golongan" id="golongan" value="III/A">
                
                <label for="">Gaji Pokok</label>
                <input type="number" name="gaji_pokok" id="gaji_pokok" class="form-control mb-2">
                <label for="">Tunjangan Jabatan</label>
                <select class="form-control mb-2" name="tunjangan_jabatan" id="tunjangan_jabatan">
                    <option value="0">Tidak Ada</option>
                    <option value="5000000">Kepala Bagian</option>
                    <option value="2500000">Staff</option>
                </select>
                <hr>
                <p class="fw-bold">Tunjangan Lainnya</p>
                <label for="">Menikah</label>
                <select class="form-control mb-2" name="tunjangan_nikah" id="tunjangan_nikah">
                    <option value="0">Belum</option>
                    <option value="250000">Sudah</option>
                </select>
                <label for="">Tunjangan Anak</label>
                <div>
                    <input class="form-check-input" type="radio" name="tunjangan_anak" id="tunjangan_anak_0" value="0" checked>
                    <label for="tunjangan_anak_0">Tidak Ada</label>
                </div>
                <div>
                    <input class="form-check-input" type="radio" name="tunjangan_anak" id="tunjangan_anak_1" value="200000">
                    <label for="tunjangan_anak_1">1 Anak</label>
                </div>
                <div>
                    <input class="form-check-input" type="radio" name="tunjangan_anak" id="tunjangan_anak_2" value="400000">
                    <label for="tunjangan_anak_2">2 Anak</label>
                </div>
                <hr>
                <p class="fw-bold">Potongan</p>
                <blockquote style="font-family: monospace;" class="fw-bold fst-italic text-danger">
                    * Potongan BPJS Kesehatan Sebesar Rp. 340.000,- <br>
                    * Potongan Pajak 15% bagi Golongan IV, dan 5% bagi golongan III
                </blockquote>

                <hr>
                <p class="fw-bold">Rincian Slip Gaji</p>
                <table class="table">
                    <tr>
                        <td>Gaji</td>
                        <td id="gaji">Rp. 0</td>
                    </tr>
                    <tr>
                        <td>Tunjangan Jabatan</td>
                        <td id="jabatan">Rp. 0</td>
                    </tr>
                    <tr>
                        <td>Tunjangan Suami/Istri</td>
                        <td id="nikah">Rp. 0</td>
                    </tr>
                    <tr>
                        <td>Tunjangan Anak</td>
                        <td id="anak">Rp. 0</td>
                    </tr>
                    <tr>
                        <td>Potongan BPJS</td>
                        <td id="bpjs">Rp. 0</td>
                    </tr>
                    <tr>
                        <td>Potongan Pajak</td>
                        <td id="pajak">Rp. 0</td>
                    </tr>
                    <tr class="fw-bold">
                        <td>Gaji Bersih</td>
                        <td id="gaji_bersih">Rp. 0</td>
                    </tr>
                </table>
                <button type="button" class="form-control btn btn-info mt-1" onclick="hitung()">Hitung Pendapatan</button>
                <input type="submit" class="form-control btn btn-success mb-3 mt-1" value="Buat Slip">
            </form>
        </div>
    </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

<?php include '../assets/templates/footer.php';?>
</body>
</html>