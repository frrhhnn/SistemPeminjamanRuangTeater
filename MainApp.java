import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

        List<User> userList = new ArrayList<>();
        User admin = new User("admin", "1234", true);
        User user1 = new User("user", "abcd", false);
        userList.add(admin);
        userList.add(user1);

        IUserMgr userMgr = new UserMgrImpl(userList);
        IRuangMgt ruangMgt = new RuangMgtImpl();
        IReservasiMgt reservasiMgt = new ReservasiMgtImpl();

        // Login
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        User loginUser = userMgr.login(username, password);
        if (loginUser == null) {
            System.out.println("Login gagal.");
            scanner.close();
            return;
        }
        System.out.println("Login berhasil: " + loginUser.username);

        while (true) {
            System.out.println("\n=== Menu ===");
            System.out.println("1. Tampilkan Daftar Ruang");
            System.out.println("2. Buat Reservasi");
            System.out.println("3. Tampilkan Reservasi Saya");
            System.out.println("4. Cek Status Reservasi");
            if (loginUser.isAdmin) {
                System.out.println("5. Tambah Ruang");
                System.out.println("6. Update Ruang");
                System.out.println("7. Hapus Ruang");
                System.out.println("8. Update Status Reservasi");
            }
            System.out.println("0. Keluar");
            System.out.print("Pilih opsi: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 0) {
                break;
            }

            switch (choice) {
                case 1: // Tampilkan Daftar Ruang
                    List<RuangTeater> ruangList = ruangMgt.showRuang();
                    System.out.println("Daftar Ruang Teater:");
                    for (int i = 0; i < ruangList.size(); i++) {
                        System.out.println(i + ". " + ruangList.get(i));
                    }
                    break;

                case 2: // Buat Reservasi
                    ruangList = ruangMgt.showRuang();
                    if (ruangList.isEmpty()) {
                        System.out.println("Tidak ada ruang tersedia.");
                        break;
                    }
                    System.out.println("Daftar Ruang Teater:");
                    for (int i = 0; i < ruangList.size(); i++) {
                        System.out.println(i + ". " + ruangList.get(i));
                    }
                    System.out.print("Pilih ruang (nomor): ");
                    int ruangIndex = scanner.nextInt();
                    scanner.nextLine();
                    if (ruangIndex < 0 || ruangIndex >= ruangList.size()) {
                        System.out.println("Pilihan tidak valid.");
                        break;
                    }
                    System.out.print("Masukkan tanggal (dd-MM-yyyy): ");
                    String tanggalStr = scanner.nextLine();
                    System.out.print("Masukkan waktu mulai (HH:mm): ");
                    String mulaiStr = scanner.nextLine();
                    System.out.print("Masukkan waktu selesai (HH:mm): ");
                    String selesaiStr = scanner.nextLine();
                    try {
                        Date tanggal = dateFormat.parse(tanggalStr);
                        Date mulai = timeFormat.parse(mulaiStr);
                        Date selesai = timeFormat.parse(selesaiStr);
                        Reservasi reservasi = reservasiMgt.createReservasi(loginUser, ruangList.get(ruangIndex),
                                tanggal, mulai, selesai);
                        System.out.println("Reservasi berhasil dibuat: " + reservasi);
                    } catch (Exception e) {
                        System.out.println("Gagal membuat reservasi: " + e.getMessage());
                    }
                    break;

                case 3: // Tampilkan Reservasi Saya
                    System.out.println("Reservasi Anda:");
                    for (Reservasi r : reservasiMgt.showReservasi(loginUser)) {
                        System.out.println(r);
                    }
                    break;

                case 4: // Cek Status Reservasi
                    System.out.print("Masukkan ID reservasi: ");
                    int reservasiID = scanner.nextInt();
                    scanner.nextLine();
                    try {
                        StatusReservasi status = reservasiMgt.statusReservasi(reservasiID);
                        System.out.println("Status reservasi: " + status);
                    } catch (Exception e) {
                        System.out.println("Gagal: " + e.getMessage());
                    }
                    break;

                case 5: // Tambah Ruang (Admin)
                    if (!loginUser.isAdmin)
                        break;
                    System.out.print("Masukkan nama ruang baru: ");
                    String namaRuang = scanner.nextLine();
                    System.out.print("Masukkan kapasitas ruang: ");
                    int kapasitas = scanner.nextInt();
                    scanner.nextLine();
                    try {
                        RuangTeater rt = ruangMgt.createRuang(namaRuang, kapasitas);
                        System.out.println("Ruang berhasil dibuat: " + rt);
                    } catch (Exception e) {
                        System.out.println("Gagal: " + e.getMessage());
                    }
                    break;

                case 6: // Update Ruang (Admin)
                    if (!loginUser.isAdmin)
                        break;
                    ruangList = ruangMgt.showRuang();
                    if (ruangList.isEmpty()) {
                        System.out.println("Tidak ada ruang tersedia.");
                        break;
                    }
                    System.out.println("Daftar Ruang Teater:");
                    for (int i = 0; i < ruangList.size(); i++) {
                        System.out.println(i + ". " + ruangList.get(i));
                    }
                    System.out.print("Pilih ruang (nomor): ");
                    ruangIndex = scanner.nextInt();
                    scanner.nextLine();
                    if (ruangIndex < 0 || ruangIndex >= ruangList.size()) {
                        System.out.println("Pilihan tidak valid.");
                        break;
                    }
                    System.out.print("Masukkan nama baru: ");
                    namaRuang = scanner.nextLine();
                    System.out.print("Masukkan kapasitas baru: ");
                    kapasitas = scanner.nextInt();
                    scanner.nextLine();
                    try {
                        RuangTeater updated = ruangMgt.updateRuang(ruangList.get(ruangIndex), namaRuang, kapasitas);
                        System.out.println("Ruang berhasil diperbarui: " + updated);
                    } catch (Exception e) {
                        System.out.println("Gagal: " + e.getMessage());
                    }
                    break;

                case 7: // Hapus Ruang (Admin)
                    if (!loginUser.isAdmin)
                        break;
                    ruangList = ruangMgt.showRuang();
                    if (ruangList.isEmpty()) {
                        System.out.println("Tidak ada ruang tersedia.");
                        break;
                    }
                    System.out.println("Daftar Ruang Teater:");
                    for (int i = 0; i < ruangList.size(); i++) {
                        System.out.println(i + ". " + ruangList.get(i));
                    }
                    System.out.print("Pilih ruang (nomor): ");
                    ruangIndex = scanner.nextInt();
                    scanner.nextLine();
                    if (ruangIndex < 0 || ruangIndex >= ruangList.size()) {
                        System.out.println("Pilihan tidak valid.");
                        break;
                    }
                    if (ruangMgt.deleteRuang(ruangList.get(ruangIndex))) {
                        System.out.println("Ruang berhasil dihapus.");
                    } else {
                        System.out.println("Gagal menghapus ruang.");
                    }
                    break;

                case 8: // Update Status Reservasi (Admin)
                    if (!loginUser.isAdmin)
                        break;
                    System.out.print("Masukkan ID reservasi: ");
                    reservasiID = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Masukkan status baru (Diterima/Ditolak/Selesai): ");
                    String statusBaru = scanner.nextLine();
                    if (reservasiMgt.updateStatus(reservasiID, statusBaru)) {
                        System.out.println("Status reservasi berhasil diperbarui.");
                    } else {
                        System.out.println("Gagal memperbarui status: ID atau status tidak valid.");
                    }
                    break;

                default:
                    System.out.println("Opsi tidak valid.");
            }
        }

        scanner.close();
    }
}