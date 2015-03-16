package simplecqrs.inventoryitem;

import java.util.UUID;

import org.junit.Test;
import simplecqrs.AggregateIds;
import simplecqrs.Database;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class InventoryItemDeactivatedListViewEventHandlerTest
{

    @SuppressWarnings("unchecked")
    private Database<InventoryItemListDTO, InventoryItemDetailsDTO> database = mock(Database.class);
    private final InventoryItemDeactivatedListViewEventHandler sut = new InventoryItemDeactivatedListViewEventHandler(database);

    @Test
    public void handle() throws Exception
    {
        final UUID aggregateId = AggregateIds.anAggregateId();
        InventoryItemDeactivated createInventoryItem = new InventoryItemDeactivated(aggregateId);
        sut.handle(createInventoryItem);

        verify(database).remove(aggregateId);
    }
}