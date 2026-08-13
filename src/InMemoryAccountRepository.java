import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class InMemoryAccountRepository implements Repository<BankAccount, String>{
    private final Map<String, BankAccount> storage = new HashMap<>();

    @Override
    public void save(BankAccount entity) {
        storage.put(entity.accountNumber, entity);
    }

    @Override
    public Optional<BankAccount> findById(String number) {
        return Optional.ofNullable(storage.get(number));
    }

    @Override
    public List<BankAccount> findAll() {
        return List.copyOf(storage.values());
    }

    @Override
    public void deleteById(String number) {
        storage.remove(number);
    }

    @Override
    public boolean existsById(String number) {
        return storage.containsKey(number);
    }
}
