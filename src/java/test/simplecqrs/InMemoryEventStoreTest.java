package simplecqrs;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.junit.Test;
import simplecqrs.inventoryitem.InventoryItemCreated;
import simplecqrs.inventoryitem.InventoryItemRenamed;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class InMemoryEventStoreTest
{
    private EventPublisher<Event> publisher = mock(EventPublisher.class);
    private final EventStore sut = new InMemoryEventStore(publisher);
    private final UUID aggregateId = AggregateIds.anAggregateId();
    private final String name = "anInventoryItem";

    @Test
    public void inventoryCreated()
    {
        InventoryItemCreated actualEvent = new InventoryItemCreated(aggregateId, name);
        final List<Event> actualEvents = Arrays.asList(actualEvent);
        sut.saveEvents(aggregateId, actualEvents, 1);

        verify(publisher).publish(actualEvent);
    }

    public void inventoryCreatedAndRenamed()
    {
        InventoryItemCreated inventoryItemCreated = new InventoryItemCreated(aggregateId, name);
        List<Event> actualEvents = Arrays.asList(inventoryItemCreated);
        sut.saveEvents(aggregateId, actualEvents, 1);

        InventoryItemRenamed inventoryItemRenamed = new InventoryItemRenamed(aggregateId, "newName");
        actualEvents = Arrays.asList(inventoryItemRenamed);
        sut.saveEvents(aggregateId, actualEvents, 2);

        verify(publisher).publish(inventoryItemCreated);
        verify(publisher).publish(inventoryItemRenamed);
    }

    @Test
    public void saveAndGet()
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