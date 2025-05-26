@extends('app')
@section('content')
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card mt-4">
                <div class="card-header">
                    <h3 class="text-center">Form Edit Siswa</h3>
                </div>
                <div class="card-body">
                    <form action="{{ url('siswa/update') }}" method="POST">
                        @csrf
                        <input type="hidden" name="id" value="{{ $data->id }}">
                        
                        <div class="mb-3">
                            <label for="nama" class="form-label">Nama Siswa</label>
                            <input type="text" class="form-control" name="nama" value="{{ $data->nama }}" required>
                        </div>

                        <div class="mb-3">
                            <label for="kelas" class="form-label">Kelas</label>
                            <select class="form-select" name="kelas" required>
                                <option value="X RPL 1" {{ $data->kelas == 'X RPL 1' ? 'selected' : '' }}>X RPL 1</option>
                                <option value="X RPL 2" {{ $data->kelas == 'X RPL 2' ? 'selected' : '' }}>X RPL 2</option>
                                <option value="XI RPL 1" {{ $data->kelas == 'XI RPL 1' ? 'selected' : '' }}>XI RPL 1</option>
                                <option value="XI RPL 2" {{ $data->kelas == 'XI RPL 2' ? 'selected' : '' }}>XI RPL 2</option>
                                <option value="XII RPL 1" {{ $data->kelas == 'XII RPL 1' ? 'selected' : '' }}>XII RPL 1</option>
                                <option value="XII RPL 2" {{ $data->kelas == 'XII RPL 2' ? 'selected' : '' }}>XII RPL 2</option>
                                <option value="XI KULINER 1" {{ $data->kelas == 'XI KULINER 1' ? 'selected' : '' }}>XI KULINER 1</option>
                            </select>
                        </div>

                        <div class="mb-3">
                            <label for="tanggal_lahir" class="form-label">Tanggal Lahir</label>
                            <input type="date" class="form-control" name="tanggal_lahir" value="{{ $data->tanggal_lahir }}" required>
                        </div>

                        <div class="mb-3">
                            <label for="alamat" class="form-label">Alamat</label>
                            <textarea class="form-control" name="alamat" rows="3" required>{{ $data->alamat }}</textarea>
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
