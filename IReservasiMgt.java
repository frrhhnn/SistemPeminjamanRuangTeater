import java.util.Date;
import java.util.List;

public interface IReservasiMgt {
    Reservasi createReservasi(User user, RuangTeater ruang, Date tanggal, Date mulai, Date selesai);

    List<Reservasi> showReservasi(User user);

    StatusReservasi statusReservasi(int reservasiID);

    boolean updateStatus(int reservasiID, String statusBaru);
}