<?php

namespace Database\Seeders;

use Illuminate\Database\Seeder;
use Illuminate\Support\Facades\DB;

class SiswaSeeder extends Seeder
{
    public function run()
    {
        DB::table('siswa')->insert([
            ['nama' => 'Rafei', 'kelas' => 'XI RPL 1'],
            ['nama' => 'Ihsan', 'kelas' => 'XI RPL 2'],
            ['nama' => 'Yoga', 'kelas' => 'XI RPL 3'],
        ]);
    }
}