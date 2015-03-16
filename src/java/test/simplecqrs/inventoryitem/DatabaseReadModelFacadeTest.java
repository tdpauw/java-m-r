package simplecqrs.inventoryitem;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import simplecqrs.AggregateIds;
import simplecqrs.Database;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DatabaseReadModelFacadeTest
{
    @SuppressWarnings("unchecked")
    private final Database<InventoryItemListDTO, InventoryItemDetailsDTO> database = mock(Database.class);

    private final DatabaseReadModelFacade sut = new DatabaseReadModelFacade(database);

    @Test
    public void getInventoryItems() throws Exception
    {
        InventoryItemListDTO inventoryItemListDTO = new InventoryItemListDTO(AggregateIds.anAggregateId(), "an inventory item");
        when(database.get()).thenReturn(Arrays.asList(inventoryItemListDTO));

        List<InventoryItemListDTO> actual = sut.getInventoryItems();
        assertThat(actual, hasItem(inventoryItemListDTO));
    }

    @Test
    public void getInventoryItemDetails() throws Exception
    {
        InventoryItemDetailsDTO inventoryItemDetailsDTO = new InventoryItemDetailsDTO(AggregateIds.anAggregateId(), "an inventory item", 1, 4);
        when(database.getDetails(AggregateIds.anAggregateId())).thenReturn(inventoryItemDetailsDTO);

        InventoryItemDetailsDTO actual = sut.getInventoryItemDetails(AggregateIds.anAggregateId());
        assertThat(actual, is(equalTo(inventoryItemDetailsDTO)));
    }
}