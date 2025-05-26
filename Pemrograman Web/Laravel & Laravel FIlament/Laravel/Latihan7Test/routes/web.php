<?php

use App\Http\Controllers\SiswaController;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;
use Illuminate\Support\Facades\Route;

Route::get('/', function () {
    return view('welcome');
});

Route::get('/siswa', [SiswaController::class, 'index']);

Route::get('/siswa/tambah_siswa', function () {
    return view('tambah_siswa');
});

Route::get('/siswa/edit/{id}', function ($id) {
    $data = DB::table('siswa')->where('id', $id)->first();
    return view('edit_siswa', ['data' => $data]);
});

Route::post('/siswa/post', [SiswaController::class, 'create']);
Route::post('/siswa/update', [SiswaController::class, 'update']);
Route::post('/siswa/hapus', [SiswaController::class, 'delete']);
