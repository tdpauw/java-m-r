package simplecqrs.inventoryitem;

import java.util.*;

import simplecqrs.Database;

/**
 * @author thipau
 */
public class BullShitDatabase implements Database<InventoryItemListDTO, InventoryItemDetailsDTO>
{
    private static class FieldHolder
    {
        static final List<InventoryItemListDTO> list = new ArrayList<>();
        static final Map<UUID, InventoryItemDetailsDTO> details = new HashMap<>();
    }

    @Override
    public List<InventoryItemListDTO> get()
    {
        return Collections.unmodifiableList(FieldHolder.list);
    }

    @Override
    public InventoryItemDetailsDTO get(UUID id)
    {
        return FieldHolder.details.get(id);
    }

    @Override
    public void add(InventoryItemListDTO listDTO)
    {
        FieldHolder.list.add(listDTO);
    }
}
