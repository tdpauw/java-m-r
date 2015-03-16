package simplecqrs.inventoryitem;

import simplecqrs.Database;
import simplecqrs.Handler;

/**
 * @author thipau
 */
public class InventoryItemCreatedListViewEventHandler implements Handler<InventoryItemCreated>
{
    private final Database<InventoryItemListDTO, InventoryItemDetailsDTO> database;

    public InventoryItemCreatedListViewEventHandler(Database<InventoryItemListDTO, InventoryItemDetailsDTO> database)
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
        database.add(new InventoryItemListDTO(message.id, message.name));
    }
}
