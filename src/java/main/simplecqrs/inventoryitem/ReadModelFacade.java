package simplecqrs.inventoryitem;

import java.util.List;
import java.util.UUID;

/**
 * @author thipau
 */
public interface ReadModelFacade
{
    List<InventoryItemListDTO> getInventoryItems();

    InventoryItemDetailsDTO getInventoryItemDetails(UUID id);
}
