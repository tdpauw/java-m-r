package simplecqrs;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class InventoryItemTest
{
    private final UUID aggregateId = AggregateIds.anAggregateId();
    private final String name = "anInventoryItem";

    @Test
    public void create()
    {
        final InventoryItem sut = new InventoryItem(aggregateId, name);

        final List<Event> events = sut.getUncommittedChanges();
        assertThat(events, hasItem(new InventoryItemCreated(aggregateId, name)));
    }

    @Test
    public void rename()
    {
        final InventoryItem sut = new InventoryItem();
        sut.loadFromHistory(Arrays.asList(new InventoryItemCreated(aggregateId, name)));
        String newName = "changedInventoryItem";
        sut.rename(newName);

        final List<Event> events = sut.getUncommittedChanges();
        assertThat(events, hasItem(new InventoryItemRenamed(aggregateId, newName)));
        assertThat(sut.getName(), is(equalTo(newName)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void renameWithEmptyNewName()
    {
        final InventoryItem sut = new InventoryItem();
        sut.loadFromHistory(Arrays.asList(new InventoryItemCreated(aggregateId, name)));

        sut.rename("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void renameWithNullNewName()
    {
        final InventoryItem sut = new InventoryItem();
        sut.loadFromHistory(Arrays.asList(new InventoryItemCreated(aggregateId, name)));

        sut.rename(null);
    }


    @Test(expected = IllegalArgumentException.class)
    public void renameWithBlankNewName()
    {
        final InventoryItem sut = new InventoryItem();
        sut.loadFromHistory(Arrays.asList(new InventoryItemCreated(aggregateId, name)));

        sut.rename("   ");
    }

    @Test
    public void deactivate()
    {
        final InventoryItem sut = new InventoryItem();
        sut.loadFromHistory(Arrays.asList(new InventoryItemCreated(aggregateId, name)));

        sut.deactivate();

        final List<Event> events = sut.getUncommittedChanges();
        assertThat(events, hasItem(new InventoryItemDeactivated(aggregateId)));
        assertThat(sut.isActivated(), is(false));
    }

    @Test(expected = IllegalStateException.class)
    public void deactivateAnInactiveItem()
    {
        final InventoryItem sut = new InventoryItem();
        InventoryItemCreated inventoryItemCreated = new InventoryItemCreated(aggregateId, name);
        InventoryItemDeactivated inventoryItemDeactivated = new InventoryItemDeactivated(aggregateId);
        sut.loadFromHistory(Arrays.asList(inventoryItemCreated, inventoryItemDeactivated));

        sut.deactivate();
    }
}