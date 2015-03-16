package simplecqrs.inventoryitem;

import java.util.*;

import simplecqrs.Database;

/**
 * @author thipau
 */
public class BullShitDatabase implements Database<InventoryItemListDTO, InventoryItemDetailsDTO>
{

    private final List<InventoryItemListDTO> list = new ArrayList<>();
    private final Map<UUID, InventoryItemDetailsDTO> details = new HashMap<>();


    @Override
    public List<InventoryItemListDTO> get()
    {
        return Collections.unmodifiableList(this.list);
    }

    @Override
    public Optional<InventoryItemListDTO> get(UUID id)
    {
        return this.list.stream().filter(item -> item.id == id).findFirst();
    }

    @Override
    public void add(InventoryItemListDTO listDTO)
    {
        this.list.add(listDTO);
    }

    @Override
    public InventoryItemDetailsDTO getDetails(UUID id)
    {
        return this.details.get(id);
    }

    @Override
    public void put(UUID id, InventoryItemDetailsDTO detailsDTO)
    {
        this.details.put(id, detailsDTO);
    }

    @Override
    public void remove(UUID id)
    {
        list.removeIf(item -> item.id == id);
    }
}
