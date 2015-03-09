package simplecqrs;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.junit.Test;
import simplecqrs.inventoryitem.InventoryItem;
import simplecqrs.inventoryitem.InventoryItemCreated;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

public class EventBasedRepositoryTest
{
    private final EventStore eventStore = mock(EventStore.class);
    private final Repository<InventoryItem> sut = new EventBasedRepository<>(eventStore, new AggregateRoot.Factory<>(InventoryItem.class));

    private final UUID aggregateId = AggregateIds.anAggregateId();
    private String inventoryItemName = "anInventoryItem";

    @Test
    public void getById() throws Exception
    {
        final InventoryItemCreated inventoryItemCreated = new InventoryItemCreated(aggregateId, inventoryItemName);
        when(eventStore.getEventsForAggregate(aggregateId)).thenReturn(Arrays.asList(inventoryItemCreated));

        final InventoryItem actual = sut.getById(aggregateId);
        assertThat(actual.getId(), is(equalTo(aggregateId)));
        assertThat(actual.getName(), is(equalTo(inventoryItemName)));
    }

    @Test
    public void save() throws Exception
    {
        final int expectedVersion = 1;
        final InventoryItem inventoryItem = new InventoryItem(aggregateId, inventoryItemName);
        sut.save(inventoryItem, expectedVersion);

        final List<Event> expectedEvents = Arrays.asList(new InventoryItemCreated(aggregateId, inventoryItemName));
        verify(eventStore).saveEvents(aggregateId, expectedEvents, expectedVersion);
    }
}