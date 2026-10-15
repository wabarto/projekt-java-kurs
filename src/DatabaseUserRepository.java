import java.util.List;
import java.util.Optional;

public class DatabaseUserRepository implements UserRepository{
    @Override
    public Optional<User> findById(Long id) {
        return Optional.of(new User(5L, "test", "test"));
    }

    @Override
    public User save(User user) {
        return user;
    }

    @Override
    public List<User> findAll() {
        return List.of(new User(5L, "test", "test"));
    }
}
