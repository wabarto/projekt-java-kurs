import java.util.List;
import java.util.Optional;

public class LoggingUserRepositoryProxy implements UserRepository {

    private final UserRepository delegate;

    public LoggingUserRepositoryProxy(UserRepository delegate) {
        this.delegate = delegate;
    }

    @Override
    public Optional<User> findById(Long id) {
        System.out.println("[LOG] findById(" + id + ")");
        Optional<User> user = delegate.findById(id);
        return user;
    }

    @Override
    public User save(User user) {
        return delegate.save(user);
    }

    @Override
    public List<User> findAll() {
        return delegate.findAll();
    }
}
