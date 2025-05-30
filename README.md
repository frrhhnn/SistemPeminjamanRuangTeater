# Sistem Manajemen Reservasi Teater

## Deskripsi Proyek

Sistem Manajemen Reservasi Teater adalah aplikasi berbasis Java yang dirancang untuk mengelola reservasi ruang teater. Aplikasi ini mendukung dua jenis pengguna: **admin** dan **pengguna biasa**. Pengguna biasa dapat membuat reservasi, melihat daftar reservasi mereka, dan memeriksa status reservasi. Admin memiliki hak tambahan untuk mengelola ruang teater (menambah, memperbarui, menghapus) dan memperbarui status reservasi. Program ini dibangun dengan pendekatan berbasis komponen, di mana setiap kelas memiliki tanggung jawab spesifik, dan antarmuka digunakan untuk mendefinisikan kontrak operasi.

Fitur utama:

- **Manajemen Pengguna**: Autentikasi pengguna melalui login.
- **Manajemen Ruang Teater**: Membuat, memperbarui, dan menghapus ruang teater (khusus admin).
- **Manajemen Reservasi**: Membuat reservasi, melihat reservasi pengguna, memeriksa status, dan memperbarui status (khusus admin).
- Validasi data dan pemeriksaan konflik waktu untuk reservasi.

## Penjelasan OCL

Object Constraint Language (OCL) digunakan untuk mendefinisikan precondisi dan postcondisi pada operasi dalam antarmuka (`IUserMgr`, `IRuangMgt`, `IReservasiMgt`). OCL memastikan bahwa operasi memenuhi syarat tertentu sebelum dan setelah eksekusi. Berikut adalah ringkasan OCL yang digunakan:

- **Precondisi**: Memastikan input valid (misalnya, tidak null, tanggal tidak di masa lalu, tidak ada konflik waktu untuk reservasi).
- **Postcondisi**: Menjamin hasil operasi sesuai dengan yang diharapkan (misalnya, reservasi baru dibuat dengan status "Menunggu", atau ruang teater berhasil dihapus).
- Contoh: Untuk `createReservasi`, precondisi `ValidDanTidakBentrok` memastikan data valid dan tidak ada konflik waktu, sedangkan postcondisi `ReservasiDitambahkan` memastikan reservasi baru tercatat dengan status yang benar.

OCL membantu memformalkan kontrak antarmuka, sehingga implementasi dapat diverifikasi untuk memenuhi spesifikasi.

## Kontributor

- **Farhanul Khair** (2208107010076)
- **Alfi Zamriza** (2208107010080)

## Cara Menjalankan Program

### Prasyarat

- **Java Development Kit (JDK)**: Pastikan JDK 8 atau lebih tinggi terinstal.
- **Lingkungan Pengembangan**: Gunakan IDE seperti IntelliJ IDEA, Eclipse, atau kompilasi manual melalui terminal.
- **Struktur Direktori**: Pastikan semua file Java berada dalam direktori `src/com/theater/`.

### Langkah-langkah Menjalankan

1. **Kompilasi Program**:

   - Buka terminal dan arahkan ke direktori proyek (misalnya, `cd path/to/project`).
   - Jalankan perintah:

     ```bash
     javac src/com/theater/*.java
     ```
   - Ini akan mengkompilasi semua file Java dalam paket `com.theater`.

2. **Jalankan Program**:

   - Jalankan kelas utama dengan perintah:

     ```bash
     java com.theater.MainApp
     ```

3. **Interaksi dengan Program**:

   - Masukkan username dan password untuk login (contoh: `admin`/`1234` untuk admin, atau `user`/`abcd` untuk pengguna biasa).
   - Pilih opsi dari menu interaktif:
     - **1**: Tampilkan daftar ruang teater.
     - **2**: Buat reservasi baru.
     - **3**: Lihat reservasi Anda.
     - **4**: Cek status reservasi.
     - **5-8** (khusus admin): Kelola ruang teater dan status reservasi.
     - **0**: Keluar dari program.

### Catatan

- Pastikan format tanggal (`dd-MM-yyyy`) dan waktu (`HH:mm`) dimasukkan dengan benar saat membuat reservasi.
- Program akan memvalidasi input, seperti memastikan tidak ada konflik waktu untuk reservasi atau data yang tidak valid.
- Jika menggunakan IDE, impor proyek ke IDE dan jalankan `MainApp.java` langsung.

## Contoh Penggunaan

1. Login sebagai admin:

   ```
   Username: admin
   Password: 1234
   ```
2. Tambah ruang teater (opsi 5):

   ```
   Masukkan nama ruang baru: Teater Utama
   Masukkan kapasitas ruang: 100
   ```
3. Buat reservasi (opsi 2):

   ```
   Pilih ruang (nomor): 0
   Masukkan tanggal (dd-MM-yyyy): 31-05-2025
   Masukkan waktu mulai (HH:mm): 14:00
   Masukkan waktu selesai (HH:mm): 16:00
   ```
