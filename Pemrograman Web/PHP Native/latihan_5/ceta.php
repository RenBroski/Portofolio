<?php 
require 'koneksi.php';
require 'dompdf/autoload.inc.php'; 

use Dompdf\Dompdf;
use Dompdf\Options;

$siswa = query("SELECT * FROM siswa");

$options = new Options();
$options->set('isHtml5ParserEnabled', true);
$options->set('isPhpEnabled', true);

$dompdf = new Dompdf($options);

$html = '
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Daftar Siswa</title>
    <style>
        body { font-family: Arial, sans-serif; }
        table { width: 100%; border-collapse: collapse; }
        th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
        th { background-color: #f2f2f2; }
    </style>
</head>
<body>
    <h1>Daftar Siswa</h1>
    <table>
        <thead>
            <tr>
                <th>NIS</th>
                <th>Nama</th>
                <th>Kelas</th>
                <th>Jenis Kelamin</th>
            </tr>
        </thead>
        <tbody>';

foreach($siswa as $row) {
    $html .= '
            <tr>
                <td>'.$row['nis'].'</td>
                <td>'.$row['nama'].'</td>
                <td>'.$row['kelas'].'</td>
                <td>'.($row['jenis_kelamin'] == 'L' ? 'Laki-laki' : 'Perempuan').'</td>
            </tr>';
}

$html .= '
        </tbody>
    </table>
</body>
</html>';

$dompdf->loadHtml($html);
$dompdf->setPaper('A4', 'portrait');
$dompdf->render();
$dompdf->stream("daftar_siswa.pdf", array("Attachment" => false));

exit(0);
?>
