package simplecqrs;

/**
 * @author thipau
 */
public class InventoryCommandHandlers
{
    private final Repository<InventoryItem> repository;

    public InventoryCommandHandlers(Repository<InventoryItem> repository)
    {
        this.repository = repository;
    }

    public void handle(CreateInventoryItem command)
    {
        InventoryItem item = new InventoryItem(command.id, command.name);
        repository.save(item, -1);
    }
}
