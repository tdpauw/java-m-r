package simplecqrs.inventoryitem;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author thipau
 */
public interface ReadModelFacade
{
    List<InventoryItemListDTO> getInventoryItems();

    Optional<InventoryItemDetailsDTO> getInventoryItemDetails(UUID id);
}
