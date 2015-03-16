package simplecqrs.inventoryitem;

import java.util.List;

import org.junit.Test;
import simplecqrs.AggregateIds;
import simplecqrs.Database;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

public class BullShitDatabaseTest
{
    private final Database<InventoryItemListDTO, InventoryItemDetailsDTO> sut = new BullShitDatabase();

    @Test
    public void addListDTO() throws Exception
    {
        InventoryItemListDTO item = new InventoryItemListDTO(AggregateIds.anAggregateId(), "an inventory item");
        sut.add(item);

        List<InventoryItemListDTO> actual = sut.get();
        assertThat(actual, hasSize(1));
        assertThat(actual, hasItem(item));
    }
}