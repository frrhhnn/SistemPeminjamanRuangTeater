import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReservasiMgtImpl implements IReservasiMgt {
    List<Reservasi> reservasiList = new ArrayList<>();

    public Reservasi createReservasi(User user, RuangTeater ruang, Date tanggal, Date mulai, Date selesai) {
        for (Reservasi r : reservasiList) {
            if (r.ruang.equals(ruang) && r.tanggal.equals(tanggal) &&
                    !(selesai.before(r.mulai) || mulai.after(r.selesai))) {
                throw new IllegalArgumentException("Reservasi bentrok");
            }
        }
        Reservasi r = new Reservasi(user, ruang, tanggal, mulai, selesai);
        reservasiList.add(r);
        return r;
    }

    public List<Reservasi> showReservasi(User user) {
        List<Reservasi> result = new ArrayList<>();
        for (Reservasi r : reservasiList) {
            if (r.user.equals(user)) {
                result.add(r);
            }
        }
        return result;
    }

    public StatusReservasi statusReservasi(int reservasiID) {
        for (Reservasi r : reservasiList) {
            if (r.id == reservasiID) {
                return r.status;
            }
        }
        throw new IllegalArgumentException("Reservasi tidak ditemukan");
    }

    public boolean updateStatus(int reservasiID, String statusBaru) {
        if (!statusBaru.equals("Diterima") && !statusBaru.equals("Ditolak") && !statusBaru.equals("Selesai")) {
            return false;
        }
        for (Reservasi r : reservasiList) {
            if (r.id == reservasiID) {
                r.status = StatusReservasi.valueOf(statusBaru.toUpperCase());
                return true;
            }
        }
        return false;
    }
}