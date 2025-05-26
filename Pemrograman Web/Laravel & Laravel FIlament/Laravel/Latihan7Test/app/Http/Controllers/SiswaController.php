<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;
use Illuminate\Http\RedirectResponse;

class SiswaController extends Controller
{
    public function index()
    {
        $data = DB::table('siswa')->get();
        return view('siswa', ['data' => $data]);
    }

    public function create(Request $request): RedirectResponse 
    {
        $validasi = $request->validate([
            'nama' => 'required|max:50',
            'kelas' => 'required',
            'alamat' => 'required|max:100',
            'tanggal_lahir' => 'required|date'
        ]);

        if ($validasi) {
            DB::table('siswa')->insert([
                'nama' => $request->nama,
                'kelas' => $request->kelas,
                'alamat' => $request->alamat,
                'tanggal_lahir' => $request->tanggal_lahir
            ]);
        }

        return redirect('/siswa');
    }

    public function update(Request $request): RedirectResponse 
    {
        $validasi = $request->validate([
            'nama' => 'required|max:50',
            'kelas' => 'required',
            'id' => 'required|exists:siswa,id',
            'tanggal_lahir' => 'required|date'
        ]);

        DB::table('siswa')->where('id', $request->id)->update([
            'nama' => $request->nama,
            'kelas' => $request->kelas,
            'tanggal_lahir' => $request->tanggal_lahir
        ]);
        
        return redirect('/siswa');
    }

    public function delete(Request $request): RedirectResponse
    {
        $request->validate([
            'id' => 'required|exists:siswa,id'
        ]);

        DB::table('siswa')->where('id', $request->id)->delete();

        return redirect('/siswa');
    }
}
