package simplecqrs;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EventBasedRepositoryTest
{
    private final EventStore eventStore = mock(EventStore.class);
    private final Repository<InventoryItem> sut = new EventBasedRepository<>(eventStore, new AggregateRoot.Factory<>(InventoryItem.class));

    private final UUID aggregateId = UUID.fromString("f9a0d992-4106-4fe0-87e0-18b31bd6777a");
    private String inventoryItemName = "anInventoryItem";

    @Test
    public void getById() throws Exception
    {
        when(eventStore.getEventsForAggregate(aggregateId)).thenReturn(Arrays.asList(new InventoryItemCreated(aggregateId, inventoryItemName)));

        InventoryItem actual = sut.getById(aggregateId);
        assertThat(actual.getId(), is(equalTo(aggregateId)));
        assertThat(actual.getName(), is(equalTo("anInventoryItem")));
    }

    @Test
    public void save() throws Exception
    {
        int expectedVersion = 1;
        InventoryItem inventoryItem = new InventoryItem(aggregateId, inventoryItemName);
        sut.save(inventoryItem, expectedVersion);

        List<Event> expectedEvents = Arrays.asList(new InventoryItemCreated(aggregateId, inventoryItemName));
        verify(eventStore).saveEvents(aggregateId, expectedEvents, expectedVersion);
    }
}