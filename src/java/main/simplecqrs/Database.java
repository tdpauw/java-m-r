package simplecqrs;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author thipau
 */
public interface Database<T, S>
{
    List<T> get();

    Optional<T> get(UUID id);

    void add(T listDTO);

    Optional<S> getDetails(UUID id);

    void put(UUID id, S detailsDTO);

    void remove(UUID id);
}
