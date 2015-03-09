package simplecqrs;

/**
 * @author thipau
 */
public class CreateInventoryCommandHandler implements Handler<CreateInventoryItem>
{
    private final Repository<InventoryItem> repository;

    public CreateInventoryCommandHandler(Repository<InventoryItem> repository)
    {
        this.repository = repository;
    }

    public Class<CreateInventoryItem> getType() {
        return CreateInventoryItem.class;
    }

    public void handle(CreateInventoryItem message)
    {
        final InventoryItem item = new InventoryItem(message.id, message.name);
        repository.save(item, -1);
    }
}
