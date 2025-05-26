<?php

namespace App\Http\Controllers;

use App\Models\Siswa;
use Illuminate\Http\Request;

class SiswaController extends Controller
{
    public function index()
    {
        $siswa = Siswa::all();
        return view('siswa.index', compact('siswa'));
    }

    public function create()
    {
        return view('siswa.form', ['siswa' => null]);
    }

    public function store(Request $request)
    {
        $request->validate([
            'nama' => 'required',
            'kelas' => 'required',
            'alamat' => 'required',
            'tanggal_lahir' => 'required|date',
        ]);

        Siswa::create($request->all());
        return redirect()->route('siswa.index');
    }

    public function edit(Siswa $siswa)
    {
        return view('siswa.form', compact('siswa'));
    }

    public function update(Request $request, Siswa $siswa)
    {
        $request->validate([
            'nama' => 'required',
            'kelas' => 'required',
            'alamat' => 'required',
            'tanggal_lahir' => 'required|date',
        ]);

        $siswa->update($request->all());
        return redirect()->route('siswa.index');
    }

    public function destroy(Siswa $siswa)
    {
        $siswa->delete();
        return redirect()->route('siswa.index');
    }
} 