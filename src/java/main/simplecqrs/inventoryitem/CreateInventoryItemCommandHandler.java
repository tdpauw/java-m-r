package simplecqrs.inventoryitem;

import simplecqrs.Handler;
import simplecqrs.Repository;

/**
 * @author thipau
 */
public class CreateInventoryItemCommandHandler implements Handler<CreateInventoryItem>
{
    private final Repository<InventoryItem> repository;

    public CreateInventoryItemCommandHandler(Repository<InventoryItem> repository)
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
