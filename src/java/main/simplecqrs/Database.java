package simplecqrs;

import java.util.List;
import java.util.UUID;

/**
 * @author thipau
 */
public interface Database<T, S>
{
    List<T> get();

    S get(UUID id);

}
