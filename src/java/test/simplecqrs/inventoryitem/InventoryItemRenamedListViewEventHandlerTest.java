package simplecqrs.inventoryitem;

import java.util.UUID;

import org.junit.Test;
import simplecqrs.AggregateIds;
import simplecqrs.Database;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class InventoryItemRenamedListViewEventHandlerTest
{

    @SuppressWarnings("unchecked")
    private Database<InventoryItemListDTO, InventoryItemDetailsDTO> database = mock(Database.class);
    private final InventoryItemRenamedListViewEventHandler sut = new InventoryItemRenamedListViewEventHandler(database);


    @Test
    public void handle() throws Exception
    {
        final UUID aggregateId = AggregateIds.anAggregateId();
        final String newName = "new name for inventory item";

        InventoryItemListDTO item = new InventoryItemListDTO(aggregateId, "an inventory item");
        when(database.get(aggregateId)).thenReturn(item);

        InventoryItemRenamed inventoryItemRenamed = new InventoryItemRenamed(aggregateId, newName);
        sut.handle(inventoryItemRenamed);

        assertThat(item, is(equalTo(new InventoryItemListDTO(aggregateId, newName))));

    }
}