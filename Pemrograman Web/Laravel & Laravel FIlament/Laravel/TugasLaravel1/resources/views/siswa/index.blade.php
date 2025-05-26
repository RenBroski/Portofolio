<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Daftar Siswa</title>
    <link href="{{ asset('css/app.css') }}" rel="stylesheet">
</head>
<body class="bg-gray-100">
    <div class="container mx-auto mt-10">
        <h1 class="text-2xl font-bold mb-5">Daftar Siswa</h1>
        <a href="{{ route('siswa.create') }}" class="bg-blue-500 text-white px-4 py-2 rounded">Tambah Siswa</a>
        <table class="min-w-full bg-white mt-5">
            <thead>
                <tr>
                    <th class="py-2">Nama</th>
                    <th class="py-2">Kelas</th>
                    <th class="py-2">Alamat</th>
                    <th class="py-2">Tanggal Lahir</th>
                    <th class="py-2">Aksi</th>
                </tr>
            </thead>
            <tbody>
                @foreach($siswa as $item)
                <tr>
                    <td class="border px-4 py-2">{{ $item->nama }}</td>
                    <td class="border px-4 py-2">{{ $item->kelas }}</td>
                    <td class="border px-4 py-2">{{ $item->alamat }}</td>
                    <td class="border px-4 py-2">{{ $item->tanggal_lahir }}</td>
                    <td class="border px-4 py-2">
                        <a href="{{ route('siswa.edit', $item->id) }}" class="bg-yellow-500 text-white px-2 py-1 rounded">Edit</a>
                        <form action="{{ route('siswa.destroy', $item->id) }}" method="POST" class="inline">
                            @csrf
                            @method('DELETE')
                            <button type="submit" class="bg-red-500 text-white px-2 py-1 rounded">Hapus</button>
                        </form>
                    </td>
                </tr>
                @endforeach
            </tbody>
        </table>
    </div>
</body>
</html> 