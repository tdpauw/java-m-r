package simplecqrs;

import java.util.Arrays;
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
        final String name = "anInventoryItem";
        final InventoryItem item = new InventoryItem(aggregateId, name);

        final List<Event> events = item.getUncommittedChanges();
        assertThat(events, hasItem(new InventoryItemCreated(aggregateId, name)));
    }

    @Test
    public void rename()
    {
        final UUID aggregateId = AggregateIds.anAggregateId();
        final String name = "anInventoryItem";
        final InventoryItem item = new InventoryItem();
        item.loadFromHistory(Arrays.asList(new InventoryItemCreated(aggregateId, name)));
        String newName = "changedInventoryItem";
        item.rename(newName);

        final List<Event> events = item.getUncommittedChanges();
        assertThat(events, hasItem(new InventoryItemRenamed(aggregateId, newName)));
    }


}