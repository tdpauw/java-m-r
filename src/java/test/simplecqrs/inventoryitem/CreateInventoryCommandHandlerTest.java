package simplecqrs.inventoryitem;

import java.util.UUID;

import org.junit.Test;
import simplecqrs.AggregateIds;
import simplecqrs.Repository;
import simplecqrs.inventoryitem.CreateInventoryItem;
import simplecqrs.inventoryitem.CreateInventoryItemCommandHandler;
import simplecqrs.inventoryitem.InventoryItem;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class CreateInventoryCommandHandlerTest
{

    @SuppressWarnings("unchecked")
    private final Repository<InventoryItem> repository = mock(Repository.class);
    private final CreateInventoryItemCommandHandler sut = new CreateInventoryItemCommandHandler(repository);

    @Test
    public void getType() throws Exception
    {
        assertThat(sut.getType(), is(equalTo(CreateInventoryItem.class)));
    }

    @Test
    public void handle() throws Exception
    {
        final UUID aggregateId = AggregateIds.anAggregateId();
        final String name = "anInventoryItem";
        final CreateInventoryItem anInventoryItem = new CreateInventoryItem(aggregateId, name);

        sut.handle(anInventoryItem);

        verify(repository).save(new InventoryItem(aggregateId, name), -1);
    }
}