import java.util.List;

public interface IRuangMgt {
    List<RuangTeater> showRuang();

    RuangTeater createRuang(String nama, int kapasitas);

    RuangTeater updateRuang(RuangTeater r, String nama, int kapasitas);

    boolean deleteRuang(RuangTeater r);
}