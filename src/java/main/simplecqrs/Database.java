package simplecqrs;

import java.util.List;
import java.util.UUID;

/**
 * @author thipau
 */
public interface Database<T, S>
{
    List<T> get();

    T get(UUID id);

    void add(T listDTO);

    S getDetails(UUID id);

    void put(UUID id, S detailsDTO);
}
