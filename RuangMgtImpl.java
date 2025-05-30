import java.util.ArrayList;
import java.util.List;

public class RuangMgtImpl implements IRuangMgt {
    List<RuangTeater> ruangList = new ArrayList<>();

    public List<RuangTeater> showRuang() {
        return ruangList;
    }

    public RuangTeater createRuang(String nama, int kapasitas) {
        if (nama == null || nama.isEmpty() || kapasitas <= 0) {
            throw new IllegalArgumentException("Nama tidak boleh kosong dan kapasitas harus > 0");
        }
        RuangTeater rt = new RuangTeater(nama, kapasitas);
        ruangList.add(rt);
        return rt;
    }

    public RuangTeater updateRuang(RuangTeater r, String nama, int kapasitas) {
        if (r == null || !ruangList.contains(r) || nama == null || nama.isEmpty() || kapasitas <= 0) {
            throw new IllegalArgumentException("Ruang tidak valid, nama tidak boleh kosong, atau kapasitas harus > 0");
        }
        r.nama = nama;
        r.kapasitas = kapasitas;
        return r;
    }

    public boolean deleteRuang(RuangTeater r) {
        if (r == null || !ruangList.contains(r)) {
            return false;
        }
        return ruangList.remove(r);
    }
}