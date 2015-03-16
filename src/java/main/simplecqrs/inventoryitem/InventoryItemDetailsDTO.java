package simplecqrs.inventoryitem;

import java.util.Objects;
import java.util.UUID;

/**
 * @author thipau
 */
public class InventoryItemDetailsDTO
{
    public final UUID id;
    public final String name;
    public final int currentCount;
    public final int version;

    public InventoryItemDetailsDTO(UUID id, String name, int currentCount, int version)
    {
        this.id = id;
        this.name = name;
        this.currentCount = currentCount;
        this.version = version;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InventoryItemDetailsDTO other = (InventoryItemDetailsDTO) o;

        return Objects.equals(this.id, other.id)
            && Objects.equals(this.name, other.name)
            && Objects.equals(this.currentCount, other.currentCount)
            && Objects.equals(this.version, other.version);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, name, currentCount, version);
    }
}
