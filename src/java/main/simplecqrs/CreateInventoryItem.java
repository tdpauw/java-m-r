package simplecqrs;

import java.util.UUID;

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
