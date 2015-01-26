package simplecqrs;

import java.util.ArrayList;
import java.util.List;

/**
 * @author thipau
 */
public abstract class AggregateRoot
{

    private List<Event> changes = new ArrayList<Event>();

    protected abstract void apply(Event e);

    protected void applyChange(Event e)
    {
        applyChange(e, true);
    }

    private void applyChange(Event e, boolean isNew)
    {
        apply(e);
        if (isNew)
        {
            changes.add(e);
        }
    }

    public List<Event> getUncommittedChanges()
    {
        return changes;
    }
}
