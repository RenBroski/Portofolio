<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>{{ $siswa ? 'Edit' : 'Tambah' }} Siswa</title>
    <link href="{{ asset('css/app.css') }}" rel="stylesheet">
</head>
<body class="bg-gray-100">
    <div class="container mx-auto mt-10">
        <h1 class="text-2xl font-bold mb-5">{{ $siswa ? 'Edit' : 'Tambah' }} Siswa</h1>
        <form action="{{ $siswa ? route('siswa.update', $siswa->id) : route('siswa.store') }}" method="POST">
            @csrf
            @if($siswa)
                @method('PUT')
            @endif
            <div class="mb-4">
                <label for="nama" class="block text-gray-700">Nama</label>
                <input type="text" name="nama" id="nama" value="{{ old('nama', $siswa->nama ?? '') }}" class="w-full px-3 py-2 border rounded" required>
            </div>
            <div class="mb-4">
                <label for="kelas" class="block text-gray-700">Kelas</label>
                <input type="text" name="kelas" id="kelas" value="{{ old('kelas', $siswa->kelas ?? '') }}" class="w-full px-3 py-2 border rounded" required>
            </div>
            <div class="mb-4">
                <label for="alamat" class="block text-gray-700">Alamat</label>
                <input type="text" name="alamat" id="alamat" value="{{ old('alamat', $siswa->alamat ?? '') }}" class="w-full px-3 py-2 border rounded" required>
            </div>
            <div class="mb-4">
                <label for="tanggal_lahir" class="block text-gray-700">Tanggal Lahir</label>
                <input type="date" name="tanggal_lahir" id="tanggal_lahir" value="{{ old('tanggal_lahir', $siswa->tanggal_lahir ?? '') }}" class="w-full px-3 py-2 border rounded" required>
            </div>
            <button type="submit" class="bg-blue-500 text-white px-4 py-2 rounded">{{ $siswa ? 'Update' : 'Simpan' }}</button>
        </form>
    </div>
</body>
</html> 