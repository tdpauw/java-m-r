package simplecqrs.inventoryitem;

import java.util.UUID;

/**
 * @author thipau
 */
public class InventoryItemListDTO
{
    public final UUID id;
    public final String name;

    public InventoryItemListDTO(UUID id, String name)
    {
        this.id = id;
        this.name = name;
    }
}
