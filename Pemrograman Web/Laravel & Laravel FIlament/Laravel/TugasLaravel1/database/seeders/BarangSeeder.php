<?php

namespace Database\Seeders;

use Illuminate\Database\Seeder;
use Illuminate\Support\Facades\DB;

class BarangSeeder extends Seeder
{
    /**
     * Run the database seeds.
     */
    public function run(): void
    {
        for ($i = 1; $i <= 10; $i++) {
            DB::table('barang')->insert([
                'nama' => 'Barang ' . $i,
                'kategori' => 'Kategori ' . rand(1, 3),
                'harga' => rand(10000, 100000),
                'stok' => rand(1, 100),
                'deskripsi' => 'Deskripsi barang ' . $i,
            ]);
        }
    }
} 