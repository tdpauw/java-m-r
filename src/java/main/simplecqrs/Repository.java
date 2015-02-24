package simplecqrs;

import java.util.UUID;

/**
 * @author thipau
 */
public interface Repository<T extends AggregateRoot>
{
    void save(T aggregate, int expectedVersion);

    T getById(UUID id);
}
