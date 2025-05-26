<?php

// Fungsi autoloader sederhana
spl_autoload_register(function ($class) {
    // Ubah namespace menjadi path file
    $file = str_replace('\\', DIRECTORY_SEPARATOR, $class) . '.php';
    
    // Cari file di beberapa direktori
    $directories = array(
        __DIR__ . '/PhpOffice/PhpWord/',
        __DIR__ . '/fpdf/',
        // Tambahkan direktori lain jika diperlukan
    );
    
    foreach ($directories as $directory) {
        if (file_exists($directory . $file)) {
            require $directory . $file;
            return true;
        }
    }
    
    return false;
});

// Periksa apakah file FPDF ada sebelum memuat
if (file_exists(__DIR__ . '/fpdf/fpdf.php')) {
    require_once __DIR__ . '/fpdf/fpdf.php';
} else {
    die("File FPDF tidak ditemukan. Pastikan Anda telah menginstal FPDF dengan benar.");
}

// Periksa apakah file bootstrap PhpWord ada sebelum memuat
if (file_exists(__DIR__ . '/PhpOffice/PhpWord/bootstrap.php')) {
    require_once __DIR__ . '/PhpOffice/PhpWord/bootstrap.php';
} else {
    die("File bootstrap PhpWord tidak ditemukan. Pastikan Anda telah menginstal PhpOffice/PhpWord dengan benar.");
}

// Library untuk mencetak PDF
class CetakPDF {
    private $pdf;

    public function __construct() {
        $this->pdf = new FPDF();
        $this->pdf->AddPage();
    }

    public function setJudul($judul) {
        $this->pdf->SetFont('Arial', 'B', 16);
        $this->pdf->Cell(0, 10, $judul, 0, 1, 'C');
        $this->pdf->Ln(10);
    }

    public function setHeader($headers) {
        $this->pdf->SetFont('Arial', 'B', 12);
        foreach ($headers as $header) {
            $this->pdf->Cell(40, 10, $header, 1);
        }
        $this->pdf->Ln();
    }

    public function setIsiTabel($data) {
        $this->pdf->SetFont('Arial', '', 12);
        foreach ($data as $row) {
            foreach ($row as $cell) {
                $this->pdf->Cell(40, 10, $cell, 1);
            }
            $this->pdf->Ln();
        }
    }

    public function cetak($namaFile) {
        $this->pdf->Output('D', $namaFile);
    }
}

?>