package simplecqrs.inventoryitem;

import simplecqrs.Database;
import simplecqrs.Handler;

/**
 * @author thipau
 */
public class InventoryItemDeactivatedListViewEventHandler implements Handler<InventoryItemDeactivated>
{
    private final Database<InventoryItemListDTO, InventoryItemDetailsDTO> database;

    public InventoryItemDeactivatedListViewEventHandler(Database<InventoryItemListDTO, InventoryItemDetailsDTO> database)
    {
        this.database = database;
    }

    @Override
    public Class<InventoryItemDeactivated> getType()
    {
        return InventoryItemDeactivated.class;
    }

    @Override
    public void handle(InventoryItemDeactivated message)
    {
        database.remove(message.id);
    }
}
