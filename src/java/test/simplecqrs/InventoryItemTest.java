package simplecqrs;

import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;

public class InventoryItemTest
{
    @Before
    public void when()
    {
        //nothing
    }

    @Test
    public void create()
    {
        final UUID aggregateId = AggregateIds.anAggregateId();
        final String inventoryItemName = "anInventoryItem";
        final InventoryItem item = new InventoryItem(aggregateId, inventoryItemName);

        final List<Event> events = item.getUncommittedChanges();
        assertThat(events, hasItem(new InventoryItemCreated(aggregateId, inventoryItemName)));
    }

}