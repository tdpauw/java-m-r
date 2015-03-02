package simplecqrs;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;

public class InMemoryEventStoreTest
{
    private final EventStore sut = new InMemoryEventStore();
    private final UUID aggregateId = AggregateIds.anAggregateId();
    private final String name = "anInventoryItem";

    @Test
    public void saveFollowedByGet()
    {
        InventoryItemCreated actualEvent = new InventoryItemCreated(aggregateId, name);
        final List<Event> actualEvents = Arrays.asList(actualEvent);
        sut.saveEvents(aggregateId, actualEvents, 1);

        List<Event> expectedEvents = sut.getEventsForAggregate(aggregateId);
        assertThat(expectedEvents, hasItem(actualEvent));
    }

    @Test
    public void saveTwiceFollowedByGet()
    {
        InventoryItemCreated inventoryItemCreated = new InventoryItemCreated(aggregateId, name);
        List<Event> actualEvents = Arrays.asList(inventoryItemCreated);
        sut.saveEvents(aggregateId, actualEvents, 1);

        InventoryItemRenamed inventoryItemRenamed = new InventoryItemRenamed(aggregateId, "newName");
        actualEvents = Arrays.asList(inventoryItemRenamed);
        sut.saveEvents(aggregateId, actualEvents, 2);

        List<Event> expectedEvents = sut.getEventsForAggregate(aggregateId);
        assertThat(expectedEvents, hasItems(inventoryItemCreated, inventoryItemRenamed));
    }

    @Test(expected = ConcurrencyException.class)
    public void concurrentSave()
    {
        InventoryItemCreated inventoryItemCreated = new InventoryItemCreated(aggregateId, name);
        List<Event> actualEvents = Arrays.asList(inventoryItemCreated);
        sut.saveEvents(aggregateId, actualEvents, 1);

        InventoryItemRenamed inventoryItemRenamed = new InventoryItemRenamed(aggregateId, "newName");
        actualEvents = Arrays.asList(inventoryItemRenamed);
        sut.saveEvents(aggregateId, actualEvents, 1);
    }

    @Test(expected = AggregateNotFoundException.class)
    public void getInexistingAggregate()
    {
        sut.getEventsForAggregate(aggregateId);
    }
}