package simplecqrs;

import java.util.UUID;

/**
 * @author thipau
 */
public final class AggregateIds
{
    private AggregateIds()
    {
        super();
    }

    public static UUID anAggregateId()
    {
        return UUID.fromString("f9a0d992-4106-4fe0-87e0-18b31bd6777a");
    }
}
