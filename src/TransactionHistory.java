import java.util.ArrayList;
import java.util.List;

//public class TransactionHistory extends ArrayList<String> {
//
//    public void record(String entry) {
//        add(entry);
//    } <- tak nie robimy, dziedziczenie nie jest potrzebne w tym przypadku


public class TransactionHistory {
    private final List<String> entries = new ArrayList<>();

    public void record(String entry) {
        entries.add(entry);
    }

    public List<String> getEntries() {
        return List.copyOf(entries);
    }

    public int size() {
        return entries.size();
    }
 }
