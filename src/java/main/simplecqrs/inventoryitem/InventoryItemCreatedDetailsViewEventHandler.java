package simplecqrs.inventoryitem;

import simplecqrs.Database;
import simplecqrs.Handler;

/**
 * @author thipau
 */
public class InventoryItemCreatedDetailsViewEventHandler implements Handler<InventoryItemCreated>
{
    private final Database<InventoryItemListDTO, InventoryItemDetailsDTO> database;

    public InventoryItemCreatedDetailsViewEventHandler(Database<InventoryItemListDTO, InventoryItemDetailsDTO> database)
    {
        this.database = database;
    }

    @Override
    public Class<InventoryItemCreated> getType()
    {
        return InventoryItemCreated.class;
    }

    @Override
    public void handle(InventoryItemCreated message)
    {
        database.put(message.id, new InventoryItemDetailsDTO(message.id, message.name, 0, 0));
    }
}
