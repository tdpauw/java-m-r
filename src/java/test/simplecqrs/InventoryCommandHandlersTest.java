package simplecqrs;

import java.util.UUID;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class InventoryCommandHandlersTest
{
    @SuppressWarnings("unchecked")
    private final Repository<InventoryItem> repository = (Repository<InventoryItem>) mock(Repository.class);
    private final InventoryCommandHandlers sut = new InventoryCommandHandlers(repository);
    private final UUID aggregateId = AggregateIds.anAggregateId();
    private final String name = "anInventoryItem";

    @Test
    public void create() throws Exception
    {
        sut.handle(new CreateInventoryItem(aggregateId, name));

        InventoryItem actual = new InventoryItem(aggregateId, name);
        verify(repository).save(actual, -1);
    }
}