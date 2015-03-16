package simplecqrs.inventoryitem;

import java.util.List;
import java.util.UUID;

import org.junit.Test;
import simplecqrs.AggregateIds;
import simplecqrs.Database;

import static org.hamcrest.CoreMatchers.*;
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

    @Test
    public void getById() throws Exception
    {
        UUID id = AggregateIds.anAggregateId();
        InventoryItemListDTO item = new InventoryItemListDTO(id, "an inventory item");
        sut.add(item);

        InventoryItemListDTO actual = sut.get(id);
        assertThat(actual, is(equalTo(item)));
    }

    @Test
    public void getDetailsById() throws Exception
    {
        UUID id = AggregateIds.anAggregateId();
        InventoryItemDetailsDTO item = new InventoryItemDetailsDTO(id, "an inventory item", 2, 4);
        sut.put(id, item);

        InventoryItemDetailsDTO actual = sut.getDetails(id);
        assertThat(actual, is(equalTo(item)));
    }
}