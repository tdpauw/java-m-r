package simplecqrs.inventoryitem;

import java.util.UUID;

import simplecqrs.Event;

/**
 * @author thipau
 */
public class InventoryItemCreated extends Event
{
    public UUID id;
    public String name;

    public InventoryItemCreated(UUID id, String name)
    {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InventoryItemCreated that = (InventoryItemCreated) o;

        if (!id.equals(that.id)) return false;
        if (!name.equals(that.name)) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }
}
