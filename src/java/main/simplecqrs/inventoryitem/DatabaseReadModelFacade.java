package simplecqrs.inventoryitem;

import java.util.List;
import java.util.UUID;

import simplecqrs.Database;

/**
 * @author thipau
 */
public class DatabaseReadModelFacade implements ReadModelFacade
{
    private final Database<InventoryItemListDTO, InventoryItemDetailsDTO> database;

    public DatabaseReadModelFacade(Database<InventoryItemListDTO, InventoryItemDetailsDTO> database)
    {
        this.database = database;
    }

    @Override
    public List<InventoryItemListDTO> getInventoryItems()
    {
        return database.get();
    }

    @Override
    public InventoryItemDetailsDTO getInventoryItemDetails(UUID id)
    {
        return database.get(id);
    }
}
