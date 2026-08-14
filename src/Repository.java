import java.util.HashMap;
import java.util.Map;
import java.util.Collection;

public class Repository<T, ID> {
    private final Map<ID, T> items = new HashMap<>();

    public void save(ID id, T item) {
        items.put(id, item);
    }

    public T findById(ID id) {
        return items.get(id);
    }

    public boolean existsById(ID id) {
        return items.containsKey(id);
    }

    public Collection<T> findAll() {
        return items.values();
    }
}