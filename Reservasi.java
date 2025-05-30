import java.text.SimpleDateFormat;
import java.util.Date;

public class Reservasi {
    static int counter = 0;
    int id;
    User user;
    RuangTeater ruang;
    Date tanggal;
    Date mulai;
    Date selesai;
    StatusReservasi status;

    public Reservasi(User user, RuangTeater ruang, Date tanggal, Date mulai, Date selesai) {
        if (ruang == null || tanggal == null || mulai == null || selesai == null ||
                mulai.after(selesai) || tanggal.before(new Date())) {
            throw new IllegalArgumentException("Data tidak valid: ruang, tanggal, atau waktu tidak valid");
        }
        this.id = ++counter;
        this.user = user;
        this.ruang = ruang;
        this.tanggal = tanggal;
        this.mulai = mulai;
        this.selesai = selesai;
        this.status = StatusReservasi.MENUNGGU;
    }

    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        return "Reservasi ID: " + id + ", Ruang: " + ruang.nama + ", Tanggal: " + dateFormat.format(tanggal) +
                ", Mulai: " + timeFormat.format(mulai) + ", Selesai: " + timeFormat.format(selesai) +
                ", Status: " + status;
    }
}
