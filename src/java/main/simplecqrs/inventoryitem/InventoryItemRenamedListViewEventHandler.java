package simplecqrs.inventoryitem;

import simplecqrs.Database;
import simplecqrs.Handler;

/**
 * @author thipau
 */
public class InventoryItemRenamedListViewEventHandler implements Handler<InventoryItemRenamed>
{
    private final Database<InventoryItemListDTO, InventoryItemDetailsDTO> database;

    public InventoryItemRenamedListViewEventHandler(Database<InventoryItemListDTO, InventoryItemDetailsDTO> database)
    {
        this.database = database;
    }

    @Override
    public Class<InventoryItemRenamed> getType()
    {
        return InventoryItemRenamed.class;
    }

    @Override
    public void handle(InventoryItemRenamed message)
    {
        InventoryItemListDTO item = database.get(message.id);
        item.name = message.newName;
    }
}
