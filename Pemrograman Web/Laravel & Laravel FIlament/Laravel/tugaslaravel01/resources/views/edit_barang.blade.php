@extends('app')
@section('content')
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card mt-4">
                <div class="card-header">
                    <h3 class="text-center">Form Edit Barang</h3>
                </div>
                <div class="card-body">
                    <form action="{{ route('barang.update', $data->id) }}" method="POST">
                        @csrf
                        @method('POST')
                        <input type="hidden" name="id" value="{{ $data->id }}">
                        
                        <div class="mb-3">
                            <label for="nama" class="form-label">Nama Barang</label>
                            <input type="text" class="form-control" name="nama" value="{{ $data->nama }}" required>
                        </div>

                        <div class="mb-3">
                            <label for="kategori" class="form-label">Kategori</label>
                            <select class="form-select" name="kategori" required>
                                <option value="Elektronik" {{ $data->kategori == 'Elektronik' ? 'selected' : '' }}>Elektronik</option>
                                <option value="Pakaian" {{ $data->kategori == 'Pakaian' ? 'selected' : '' }}>Pakaian</option>
                                <option value="Makanan" {{ $data->kategori == 'Makanan' ? 'selected' : '' }}>Makanan</option>
                                <option value="Peralatan" {{ $data->kategori == 'Peralatan' ? 'selected' : '' }}>Peralatan</option>
                            </select>
                        </div>

                        <div class="mb-3">
                            <label for="stok" class="form-label">Stok</label>
                            <input type="number" class="form-control" name="stok" value="{{ $data->stok }}" required>
                        </div>

                        <div class="mb-3">
                            <label for="tanggal_masuk" class="form-label">Tanggal Masuk</label>
                            <input type="date" class="form-control" name="tanggal_masuk" value="{{ $data->tanggal_masuk }}" required>
                        </div>

                        <div class="text-center">
                            <button type="submit" class="btn btn-success">Simpan</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
@endsection
