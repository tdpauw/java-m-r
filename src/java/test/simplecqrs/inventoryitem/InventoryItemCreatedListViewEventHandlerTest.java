package simplecqrs.inventoryitem;

import java.util.UUID;

import org.junit.Test;
import simplecqrs.AggregateIds;
import simplecqrs.Database;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class InventoryItemCreatedListViewEventHandlerTest
{
    @SuppressWarnings("unchecked")
    private Database<InventoryItemListDTO, InventoryItemDetailsDTO> database = mock(Database.class);
    private final InventoryItemCreatedListViewEventHandler sut = new InventoryItemCreatedListViewEventHandler(database);

    @Test
    public void handle() throws Exception
    {
        final UUID aggregateId = AggregateIds.anAggregateId();
        final String name = "an inventory item";
        CreateInventoryItem createInventoryItem = new CreateInventoryItem(aggregateId, name);
        sut.handle(createInventoryItem);

        verify(database).add(new InventoryItemListDTO(aggregateId, name));
    }
}