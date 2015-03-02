package simplecqrs;

import java.util.Arrays;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EventBasedRepositoryTest
{
    private final EventStore eventStore = mock(EventStore.class);
    private final Repository<InventoryItem> sut = new EventBasedRepository<>(eventStore, new AggregateRoot.Factory<>(InventoryItem.class));

    @Test
    public void getById() throws Exception
    {
        UUID aggregateId = UUID.fromString("f9a0d992-4106-4fe0-87e0-18b31bd6777a");
        when(eventStore.getEventsForAggregate(aggregateId)).thenReturn(Arrays.asList(new InventoryItemCreated(aggregateId, "anInventoryItem")));

        InventoryItem inventoryItem = sut.getById(aggregateId);
        assertThat(inventoryItem.getId(), is(equalTo(aggregateId)));
        assertThat(inventoryItem.getName(), is(equalTo("anInventoryItem")));
    }

    @Test
    public void save() throws Exception
    {

    }
}