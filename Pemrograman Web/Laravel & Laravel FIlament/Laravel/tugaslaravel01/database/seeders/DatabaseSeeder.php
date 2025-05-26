<?php

namespace Database\Seeders;

use Illuminate\Database\Seeder;
use Illuminate\Support\Facades\DB;

class DatabaseSeeder extends Seeder
{
    /**
     * Seed the application's database.
     */
    public function run(): void
    {
        DB::table('barang')->insert([
            ['id' => 1, 'nama' => 'Meja', 'kategori' => 'Peralatan', 'jumlah' => 2, 'tanggal' => '2024-12-05'],

            ['id' => 2, 'nama' => 'Kursi', 'kategori' => 'Peralatan', 'jumlah' => 3, 'tanggal' => '2024-12-04'],

            ['id' => 3, 'nama' => 'Sepatu Dior', 'kategori' => 'Peralatan', 'jumlah' => 1, 'tanggal' => '2024-09-05'],

            ['id' => 4, 'nama' => 'Baju Lacoste', 'kategori' => 'Pakaian', 'jumlah' => 4, 'tanggal' => '2024-08-02'],
        
            ['id' => 5, 'nama' => 'Kacamata', 'kategori' => 'Peralatan', 'jumlah' => 2, 'tanggal' => '2024-10-10'],

            ['id' => 6, 'nama' => 'Laptop', 'kategori' => 'Elektronik', 'jumlah' => 2, 'tanggal' => '2025-02-28'],

            ['id' => 7, 'nama' => 'Handphone', 'kategori' => 'Elektronik', 'jumlah' => 2, 'tanggal' => '2025-01-03'],

            ['id' => 8, 'nama' => 'Tv', 'kategori' => 'Elektronik', 'jumlah' => 4, 'tanggal' => '2025-03-20'],

            ['id' => 9, 'nama' => 'Ac', 'kategori' => 'Elektronik', 'jumlah' => 2, 'tanggal' => '2025-02-27'],

            ['id' => 10, 'nama' => 'Pc', 'kategori' => 'Elektronik', 'jumlah' => 5, 'tanggal' => '2025-03-01']
        ]);
    }
}
