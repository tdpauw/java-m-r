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
        UUID id = UUID.randomUUID();
        InventoryItem item = new InventoryItem(id, "anInventoryItem");

        List<Event> events = item.getUncommittedChanges();
        assertThat(events, hasItem(new InventoryItemCreated(id, "anInventoryItem")));
    }

}