<?php

namespace Database\Seeders;

use Illuminate\Database\Seeder;
use Illuminate\Support\Facades\DB;
use Illuminate\Support\Str;

class SiswaSeeder extends Seeder
{
    /**
     * Run the database seeds.
     */
    public function run(): void
    {
        for ($i = 1; $i <= 10; $i++) {
            DB::table('siswa')->insert([
                'nama' => 'Siswa ' . $i,
                'kelas' => 'Kelas ' . rand(1, 3),
                'alamat' => 'Alamat ' . $i,
                'tanggal_lahir' => now()->subYears(rand(10, 20))->toDateString(),
            ]);
        }
    }
} 