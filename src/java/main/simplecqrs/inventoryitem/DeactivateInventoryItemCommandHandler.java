package simplecqrs.inventoryitem;

import simplecqrs.Handler;
import simplecqrs.Repository;

/**
 * @author thipau
 */
public class DeactivateInventoryItemCommandHandler implements Handler<DeactivateInventoryItem>
{
    private final Repository<InventoryItem> repository;

    public DeactivateInventoryItemCommandHandler(Repository<InventoryItem> repository)
    {
        this.repository = repository;
    }

    @Override
    public Class<DeactivateInventoryItem> getType()
    {
        return DeactivateInventoryItem.class;
    }

    @Override
    public void handle(DeactivateInventoryItem message)
    {
        InventoryItem inventoryItem = repository.getById(message.getId());
        inventoryItem.deactivate();
        repository.save(inventoryItem, message.getOriginalVersion());
    }
}
