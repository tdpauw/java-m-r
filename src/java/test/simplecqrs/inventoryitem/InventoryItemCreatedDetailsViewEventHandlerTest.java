package simplecqrs.inventoryitem;

import java.util.UUID;

import org.junit.Test;
import simplecqrs.AggregateIds;
import simplecqrs.Database;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class InventoryItemCreatedDetailsViewEventHandlerTest
{
    @SuppressWarnings("unchecked")
    private Database<InventoryItemListDTO, InventoryItemDetailsDTO> database = mock(Database.class);
    private final InventoryItemCreatedDetailsViewEventHandler sut = new InventoryItemCreatedDetailsViewEventHandler(database);

    @Test
    public void handle() throws Exception
    {
        final UUID aggregateId = AggregateIds.anAggregateId();
        final String name = "an inventory item";
        InventoryItemCreated createInventoryItem = new InventoryItemCreated(aggregateId, name);
        sut.handle(createInventoryItem);

        verify(database).put(aggregateId, new InventoryItemDetailsDTO(aggregateId, name, 0, 0));
    }
}