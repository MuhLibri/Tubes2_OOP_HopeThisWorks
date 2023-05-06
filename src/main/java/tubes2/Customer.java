package tubes2;
import java.util.ArrayList;
import java.util.List;

public abstract class Customer {
    private final int id;
    private List<FixedBill> historiTransaksi;
    public Customer(int id) {
        this.id = id;
        this.historiTransaksi = new ArrayList<>();
    }
    public Customer(int id, List<FixedBill> historiTransaksi){
        this.id=id;
        this.historiTransaksi=historiTransaksi;
    }

    public int getId() {
        return this.id;
    }
    public void setHistoriTransaksi(List<FixedBill> historiTransaksi){
        this.historiTransaksi=historiTransaksi;
    }

    public void addHistoriTransaksi(FixedBill historiTransaksi) {
        this.historiTransaksi.add(historiTransaksi);
    }

    public List<FixedBill> getHistoriTransaksi() {
        return this.historiTransaksi;
    }

    public abstract void pesan(FixedBill historiTransaksi, int hargaTotal);
}
