<?php
include 'koneksi.php';
require 'dompdf/autoload.inc.php';
use Dompdf\Dompdf;

// Periksa apakah file dan kelas Dompdf tersedia
if (!file_exists('dompdf/autoload.inc.php') || !class_exists('Dompdf\Dompdf')) {
    die("Error: File Dompdf tidak ditemukan atau kelas tidak tersedia.");
}

$dompdf = new Dompdf();

// Periksa koneksi database
if (!$conn) {
    die("Koneksi database gagal: " . mysqli_connect_error());
}

$query = $conn->query("SELECT * FROM perjalanan ORDER BY id_perjalanan ASC");

// Periksa apakah query berhasil dijalankan
if (!$query) {
    die("Error dalam menjalankan query: " . $conn->error);
}

$html = '<center><h3>Daftar Perjalanan</h3></center><hr/><br/>';
$html .= '<table border="1" width="100%">
<tr>
<th>No</th>
<th>Destinasi</th>
<th>Alat Transportasi</th>
<th>Durasi Perjalanan</th>
<th>Biaya Tiket</th>
</tr>';
$no = 1;
while($row = mysqli_fetch_array($query))
{
$html .= "<tr>
<td>".$no."</td>
<td>".$row['destinasi']."</td>
<td>".$row['alat_transportasi']."</td>
<td>".$row['durasi_perjalanan']."</td>
<td>".$row['biaya_tiket']."</td>
</tr>";
$no++;
}
$html .= "</table>";

// Tangani kesalahan saat memuat HTML
try {
    $dompdf->loadHtml($html);
} catch (Exception $e) {
    die("Error saat memuat HTML: " . $e->getMessage());
}

$dompdf->setPaper('A4');

// Tangani kesalahan saat merender PDF
try {
    $dompdf->render();
} catch (Exception $e) {
    die("Error saat merender PDF: " . $e->getMessage());
}

// Atur header untuk mengunduh file
header('Content-Type: application/pdf');
header('Content-Disposition: attachment; filename="laporan_perjalanan.pdf"');

// Keluarkan PDF
echo $dompdf->output();

// Tutup koneksi database
$conn->close();
?>