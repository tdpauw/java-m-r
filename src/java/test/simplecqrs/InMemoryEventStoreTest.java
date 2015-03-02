package simplecqrs;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;

public class InMemoryEventStoreTest
{
    private final EventStore sut = new InMemoryEventStore();
    private final UUID aggregateId = AggregateIds.anAggregateId();

    @Test
    public void saveFollowedByGet()
    {
        InventoryItemCreated actualEvent = new InventoryItemCreated(aggregateId, "anInventoryItem");
        final List<Event> actualEvents = Arrays.asList(actualEvent);
        sut.saveEvents(aggregateId, actualEvents, 1);

        List<Event> expectedEvents = sut.getEventsForAggregate(aggregateId);
        assertThat(expectedEvents, hasItem(actualEvent));
    }
}