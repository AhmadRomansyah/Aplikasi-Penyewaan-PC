
## Deskripsi
Aplikasi Penyewaan PC adalah aplikasi berbasis Java Swing yang dirancang untuk mengelola sistem penyewaan komputer personal (PC). Aplikasi ini memungkinkan pengelolaan data pelanggan, data PC, data karyawan, serta pencatatan transaksi penyewaan. Selain itu, aplikasi ini mendukung ekspor data ke dalam format CSV untuk keperluan pelaporan.
## Fitur Utama
1.Manajemen Karyawan

Tambah, ubah, hapus, dan lihat data karyawan.

Ekspor data karyawan ke file CSV.

2.Manajemen Pelanggan

Tambah, ubah, hapus, dan lihat data pelanggan.

Ekspor data pelanggan ke file CSV.

3.Manajemen PC

Tambah, ubah, hapus, dan lihat data PC, termasuk merk, tipe, dan harga sewa.

Ekspor data PC ke file CSV.

4.Manajemen Transaksi

Catat transaksi penyewaan, hitung total harga berdasarkan durasi penyewaan dan tarif PC.

Ekspor data transaksi ke file CSV.

5.Pembaruan Dinamis

ComboBox untuk pelanggan dan PC diperbarui secara dinamis untuk mempermudah perekaman transaksi.
## Persyaratan sistem
java Development Kit (JDK): JDK 8 atau yang lebih baru.

Database: MySQL atau database relasional lain dengan struktur tabel berikut:

Karyawan: id_karyawan, nama, alamat, no_telepon

Pelanggan: id_pelanggan, nama, alamat, no_telepon

PC: id_pc, merk, tipe, harga_sewa

Transaksi: id_transaksi, id_pelanggan, id_pc, lama_sewa, total_harga
## Cara Menggunakan
Cara Menggunakan

1.Persiapan

Konfigurasikan koneksi database di kelas DatabaseConnection.

Pastikan tabel database sudah dibuat sesuai dengan struktur yang diperlukan.

2.Menjalankan Aplikasi

Kompilasi dan jalankan file utama (MainForm.java).

Gunakan tab antarmuka untuk mengakses fitur manajemen karyawan, pelanggan, PC, dan transaksi.

3.Operasi CRUD

Gunakan formulir input dan tombol aksi untuk menambahkan, mengedit, atau menghapus data.

Data akan diperbarui secara langsung pada tabel setelah operasi berhasil.

4.Ekspor Data

Gunakan tombol "Report" untuk mengekspor data dari tabel ke file CSV.

File CSV akan disimpan pada lokasi yang ditentukan oleh pengguna.

5.Struktur Proyek

MainForm.java: File utama yang berisi logika GUI aplikasi.

DatabaseConnection.java: Kelas utilitas untuk mengatur koneksi database.

Direktori CSV: Menyimpan file hasil ekspor data.
## Dibuat
Ahmad Romansyah 2210010410 Kelas 5B REG PAGI BANJARMASIN