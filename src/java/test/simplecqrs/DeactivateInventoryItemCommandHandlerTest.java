package simplecqrs;

import java.util.UUID;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DeactivateInventoryItemCommandHandlerTest
{
    @SuppressWarnings("unchecked")
    private final Repository<InventoryItem> repository = mock(Repository.class);
    private final DeactivateInventoryItemCommandHandler sut = new DeactivateInventoryItemCommandHandler(repository);

    @Test
    public void getType() throws Exception
    {
        assertThat(sut.getType(), is(equalTo(DeactivateInventoryItem.class)));
    }

    @Test
    public void handle() throws Exception
    {
        final UUID aggregateId = AggregateIds.anAggregateId();
        final int originalVersion = 1;
        final DeactivateInventoryItem anInventoryItem = new DeactivateInventoryItem(aggregateId, originalVersion);

        InventoryItem inventoryItem = new InventoryItem(aggregateId, "anInventoryItem");

        when(repository.getById(aggregateId)).thenReturn(inventoryItem);

        sut.handle(anInventoryItem);

        verify(repository).save(inventoryItem, originalVersion);
    }
}