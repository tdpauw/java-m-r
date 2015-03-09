package simplecqrs.inventoryitem;

import java.util.UUID;

import simplecqrs.Command;

/**
 * @author thipau
 */
public class DeactivateInventoryItem extends Command
{
    private final UUID id;
    private final int originalVersion;

    public DeactivateInventoryItem(UUID id, int originalVersion)
    {
        this.id = id;
        this.originalVersion = originalVersion;
    }

    public UUID getId()
    {
        return id;
    }

    public int getOriginalVersion()
    {
        return originalVersion;
    }
}
