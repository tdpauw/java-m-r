package simplecqrs.inventoryitem;

import java.util.UUID;

import simplecqrs.Command;

/**
 * @author thipau
 */
public class CreateInventoryItem extends Command
{
    public final UUID id;
    public final String name;

    public CreateInventoryItem(UUID id, String name)
    {
        this.id = id;
        this.name = name;
    }
}
