package simplecqrs;

import java.util.UUID;

/**
 * @author thipau
 */
public class InventoryItem extends AggregateRoot
{
    private UUID id;
    private String name;
    private boolean activated;

    public InventoryItem(UUID id, String name)
    {
        if (id == null) {
            throw new IllegalArgumentException("id cannot be null");
        }
        if (name == null) {
            throw new IllegalArgumentException("name cannot be null");
        }
        applyChange(new InventoryItemCreated(id, name));
    }

    @Override
    protected void apply(Event e)
    {
        if (e instanceof InventoryItemCreated) {
            apply((InventoryItemCreated) e);
        }
        else
            throw new UnsupportedOperationException(e + " is not supported " + this);
    }

    private void apply(InventoryItemCreated e)
    {
        id = e.id;
        name = e.name;
        activated = true;
    }
}
