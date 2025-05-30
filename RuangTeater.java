public class RuangTeater {
    static int counter = 0;
    int id;
    String nama;
    int kapasitas;

    public RuangTeater(String nama, int kapasitas) {
        this.id = ++counter;
        this.nama = nama;
        this.kapasitas = kapasitas;
    }

    public String toString() {
        return id + ". " + nama + " (Kapasitas: " + kapasitas + ")";
    }
}