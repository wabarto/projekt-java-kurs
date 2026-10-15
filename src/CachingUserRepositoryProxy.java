import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class CachingUserRepositoryProxy implements UserRepository {

    private final UserRepository delegate;
    private final Map<Long, User> cache = new ConcurrentHashMap<>();

    public CachingUserRepositoryProxy(UserRepository delegate) {
        this.delegate = delegate;
    }

    @Override
    public Optional<User> findById(Long id) {
        System.out.println("cached");
        User cached = cache.get(id);

        if (cached != null) {
            return Optional.of(cached);
        }

        Optional<User> result = delegate.findById(id);
        result.ifPresent(user -> cache.put(id, user));
        return result;
    }

    @Override
    public User save(User user) {
        cache.remove(user.id());
        return delegate.save(user);
    }

    @Override
    public List<User> findAll() {
        return delegate.findAll();
    }
}
