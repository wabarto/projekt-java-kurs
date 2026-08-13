import java.util.List;
import java.util.Optional;

// interface AccountRepository extends JpaRepository<BankAccount, String> { }

public interface Repository<T, ID> {
    void save(T entity);
    Optional<T> findById(ID id);
    List<T> findAll();
    void deleteById(ID id);
    boolean existsById(ID id);
}
