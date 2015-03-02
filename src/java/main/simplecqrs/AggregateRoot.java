package simplecqrs;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author thipau
 */
public abstract class AggregateRoot
{

    /**
     * This classed is used for instantiating Generic types of the DefaultRepository.
     * @see <a href="http://stackoverflow.com/questions/1090458/instantiating-a-generic-class-in-java">http://stackoverflow.com/questions/1090458/instantiating-a-generic-class-in-java</a>
     */
    public static class Factory<T>
    {
        private Class<T> clazz;

        public Factory(Class<T> clazz)
        {
            this.clazz = clazz;
        }

        public T newInstance()
        {
            try
            {
                return clazz.newInstance();
            } catch (InstantiationException | IllegalAccessException e)
            {
                throw new RuntimeException("Unable to create an instance of " + clazz.getName(), e);
            }
        }
    }

    private List<Event> changes = new ArrayList<>();
    public abstract UUID getId();

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

    public void loadFromHistory(List<Event> history)
    {
        for(Event e: history) applyChange(e, false);
    }
}
