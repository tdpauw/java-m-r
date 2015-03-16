package simplecqrs.inventoryitem;

import simplecqrs.Database;
import simplecqrs.Handler;

/**
 * @author thipau
 */
public class InventoryItemCreatedListViewEventHandler implements Handler<CreateInventoryItem>
{
    private final Database<InventoryItemListDTO, InventoryItemDetailsDTO> database;

    public InventoryItemCreatedListViewEventHandler(Database<InventoryItemListDTO, InventoryItemDetailsDTO> database)
    {
        this.database = database;
    }

    @Override
    public Class<CreateInventoryItem> getType()
    {
        return CreateInventoryItem.class;
    }

    @Override
    public void handle(CreateInventoryItem message)
    {
        database.add(new InventoryItemListDTO(message.id, message.name));
    }
}
