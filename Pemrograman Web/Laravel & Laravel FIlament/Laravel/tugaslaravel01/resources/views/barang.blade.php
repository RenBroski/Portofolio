@extends('app')
@section('content')
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-10">
            <div class="card mt-4">
                <div class="card-header">
                    <h3 class="mb-0">Data Barang</h3>
                </div>
                <div class="card-body">
                    <a href="{{ route('barang.create') }}" class="btn btn-primary mb-3">
                        <i class="fas fa-plus"></i> Tambah Barang
                    </a>

                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th>No</th>
                                <th>Nama</th>
                                <th>Kategori</th>
                                <th>Stok</th>
                                <th>Tanggal Masuk</th>
                                <th>Aksi</th>
                            </tr>
                        </thead>
                        <tbody>
                            @foreach($data as $key => $row)
                            <tr>
                                <td>{{ $key + 1 }}</td>
                                <td>{{ $row->nama }}</td>
                                <td>{{ $row->kategori }}</td>
                                <td>{{ $row->stok }}</td>
                                <td>{{ $row->tanggal_masuk }}</td>
                                <td class="text-center">
                                    <a href="{{ route('barang.edit', $row->id) }}" class="btn btn-warning btn-sm me-2">
                                        <i class="fas fa-edit"></i> Edit
                                    </a>
                                    <form action="{{ route('barang.destroy', $row->id) }}" method="POST" style="display:inline;">
                                        @csrf
                                        @method('POST')
                                        <button type="submit" class="btn btn-danger btn-sm">
                                            <i class="fas fa-trash"></i> Hapus
                                        </button>
                                    </form>
                                </td>
                            </tr>
                            @endforeach
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
@endsection