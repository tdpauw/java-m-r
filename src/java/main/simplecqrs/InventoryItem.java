package simplecqrs;

import java.util.UUID;

import com.google.common.base.Strings;

/**
 * @author thipau
 */
public class InventoryItem extends AggregateRoot
{
    private UUID id;
    private String name;
    private boolean activated;

    public InventoryItem() {
        // used to create in repository ... many ways to avoid this, eg making private constructor
    }

    public InventoryItem(UUID id, String name)
    {
        if (id == null) {
            throw new IllegalArgumentException("id cannot be null");
        }
        if (name == null) {
            throw new IllegalArgumentException("name cannot be null");
        }
        applyChange(new InventoryItemCreated(id, name));
    }



    @Override
    public UUID getId() {
        return id;
    }

    public String getName()
    {
        return name;
    }

    @Override
    protected void apply(Event e)
    {
        if (e instanceof InventoryItemCreated)
        {
            apply((InventoryItemCreated) e);
        }
        else if(e instanceof InventoryItemRenamed)
        {
            apply((InventoryItemRenamed) e);
        }
        else
            throw new UnsupportedOperationException(e + " is not supported " + this);
    }

    private void apply(InventoryItemCreated e)
    {
        id = e.id;
        name = e.name;
        activated = true;
    }

    private void apply(InventoryItemRenamed e)
    {
        name = e.newName;
    }

    public void rename(String newName)
    {
        if (Strings.isNullOrEmpty(newName))  throw new IllegalArgumentException("newName must be provided");
        applyChange(new InventoryItemRenamed(id, newName));
    }
}
