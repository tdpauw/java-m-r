package simplecqrs;

import java.util.UUID;

/**
 * @author thipau
 */
public class InventoryItemDeactivated extends Event
{
    public final UUID id;

    public InventoryItemDeactivated(UUID id)
    {
        this.id = id;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InventoryItemDeactivated that = (InventoryItemDeactivated) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        return id != null ? id.hashCode() : 0;
    }
}
