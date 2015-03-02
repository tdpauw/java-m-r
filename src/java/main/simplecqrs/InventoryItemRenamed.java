package simplecqrs;

import java.util.UUID;

/**
 * @author thipau
 */
public class InventoryItemRenamed extends Event
{
    public final UUID id;
    public final String newName;

    public InventoryItemRenamed(UUID id, String newName)
    {
        this.id = id;
        this.newName = newName;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InventoryItemRenamed that = (InventoryItemRenamed) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (newName != null ? !newName.equals(that.newName) : that.newName != null) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (newName != null ? newName.hashCode() : 0);
        return result;
    }
}
