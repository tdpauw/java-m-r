package simplecqrs.inventoryitem;

import java.util.Objects;
import java.util.UUID;

/**
 * @author thipau
 */
public class InventoryItemListDTO
{
    public final UUID id;
    public String name;

    public InventoryItemListDTO(UUID id, String name)
    {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InventoryItemListDTO other = (InventoryItemListDTO) o;

        return Objects.equals(this.id, other.id)
            && Objects.equals(this.name, other.name);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, name);
    }
}
