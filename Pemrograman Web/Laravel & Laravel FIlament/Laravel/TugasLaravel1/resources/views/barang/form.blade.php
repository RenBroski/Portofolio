<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>{{ $barang ? 'Edit' : 'Tambah' }} Barang</title>
    <link href="{{ asset('css/app.css') }}" rel="stylesheet">
</head>
<body class="bg-gray-100">
    <div class="container mx-auto mt-10">
        <h1 class="text-2xl font-bold mb-5">{{ $barang ? 'Edit' : 'Tambah' }} Barang</h1>
        <form action="{{ $barang ? route('barang.update', $barang->id) : route('barang.store') }}" method="POST">
            @csrf
            @if($barang)
                @method('PUT')
            @endif
            <div class="mb-4">
                <label for="nama" class="block text-gray-700">Nama</label>
                <input type="text" name="nama" id="nama" value="{{ old('nama', $barang->nama ?? '') }}" class="w-full px-3 py-2 border rounded" required>
            </div>
            <div class="mb-4">
                <label for="kategori" class="block text-gray-700">Kategori</label>
                <input type="text" name="kategori" id="kategori" value="{{ old('kategori', $barang->kategori ?? '') }}" class="w-full px-3 py-2 border rounded" required>
            </div>
            <div class="mb-4">
                <label for="harga" class="block text-gray-700">Harga</label>
                <input type="number" name="harga" id="harga" value="{{ old('harga', $barang->harga ?? '') }}" class="w-full px-3 py-2 border rounded" required>
            </div>
            <div class="mb-4">
                <label for="stok" class="block text-gray-700">Stok</label>
                <input type="number" name="stok" id="stok" value="{{ old('stok', $barang->stok ?? '') }}" class="w-full px-3 py-2 border rounded" required>
            </div>
            <div class="mb-4">
                <label for="deskripsi" class="block text-gray-700">Deskripsi</label>
                <textarea name="deskripsi" id="deskripsi" class="w-full px-3 py-2 border rounded">{{ old('deskripsi', $barang->deskripsi ?? '') }}</textarea>
            </div>
            <button type="submit" class="bg-blue-500 text-white px-4 py-2 rounded">{{ $barang ? 'Update' : 'Simpan' }}</button>
        </form>
    </div>
</body>
</html> 