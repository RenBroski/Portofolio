@extends('app')
@section('content')
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card mt-4">
                <div class="card-header">
                    <h3 class="mb-0">Data Siswa</h3>
                </div>
                <div class="card-body">
                    <a href="siswa/tambah_siswa" class="btn btn-primary mb-3">
                        <i class="fas fa-plus"></i> Tambah Siswa
                    </a>

                    @foreach($data as $row)
                    <div class="card mb-3">
                        <div class="card-body">
                            <div class="row">
                                <div class="col-md-8">
                                    <h5 class="card-title">{{ $row->nama }}</h5>
                                    <p class="card-text">Kelas: {{ $row->kelas }}</p>
                                    <p class="card-text">Alamat: {{ $row->alamat }}</p>
                                    <p class="card-text">Tanggal Lahir: {{ $row->tanggal_lahir }}</p>
                                </div>
                                <div class="col-md-4 text-end">
                                    <a href="siswa/edit/{{$row->id}}" class="btn btn-warning btn-sm me-2">
                                        <i class="fas fa-edit"></i> Edit
                                    </a>
                                    <a href="siswa/hapus/{{$row->id}}" class="btn btn-danger btn-sm">
                                        <i class="fas fa-trash"></i> Hapus
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                    @endforeach
                </div>
            </div>
        </div>
    </div>
</div>
@endsection