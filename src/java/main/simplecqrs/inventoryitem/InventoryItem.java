package simplecqrs.inventoryitem;

import java.util.UUID;

import com.google.common.base.Strings;
import simplecqrs.AggregateRoot;
import simplecqrs.Event;

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

    public boolean isActivated()
    {
        return activated;
    }

    public void rename(String newName)
    {
        if (Strings.isNullOrEmpty(newName) || newName.trim().isEmpty())  throw new IllegalArgumentException("A new name must be provided");
        applyChange(new InventoryItemRenamed(id, newName));
    }

    public void deactivate()
    {
        if(!activated) throw new IllegalStateException("The item already deactivated");
        applyChange(new InventoryItemDeactivated(id));
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
        else if(e instanceof InventoryItemDeactivated)
        {
            apply((InventoryItemDeactivated) e);
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

    private void apply(InventoryItemDeactivated e)
    {
        activated = false;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InventoryItem that = (InventoryItem) o;

        if (activated != that.activated) return false;
        if (!id.equals(that.id)) return false;
        if (!name.equals(that.name)) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + (activated ? 1 : 0);
        return result;
    }
}
