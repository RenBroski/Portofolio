<?php

use Illuminate\Support\Facades\Route;
use App\Http\Controllers\BarangController;
use Illuminate\Support\Facades\DB;
use Illuminate\Support\Facades\View;

/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider and all of them will
| be assigned to the "web" middleware group. Make something great!
|
*/

Route::get('/', function () {
    return view('welcome');
});

Route::get('/barang', [BarangController::class, 'index'])->name('barang.index');

Route::get('/barang/tambah_barang', function () {
    return view('tambah_barang');
})->name('barang.create');

Route::get('/barang/edit/{id}', function ($id) {
    $data = DB::table('barang')->where('id', $id)->first();
    return view('edit_barang', ['data' => $data]);
})->name('barang.edit');

Route::post('/barang/store', [BarangController::class, 'store'])->name('barang.store');
Route::post('/barang/update/{id}', [BarangController::class, 'update'])->name('barang.update');
Route::post('/barang/hapus/{id}', [BarangController::class, 'destroy'])->name('barang.destroy');
