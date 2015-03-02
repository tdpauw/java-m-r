package simplecqrs;

import java.util.List;
import java.util.UUID;

/**
 * @author thipau
 */
public class EventBasedRepository<T extends AggregateRoot> implements Repository<T>
{
    private final EventStore storage;
    private final AggregateRoot.Factory<T> factory;

    public EventBasedRepository(EventStore storage, AggregateRoot.Factory<T> factory)
    {
        this.storage = storage;
        this.factory = factory;
    }

    @Override
    public void save(T aggregate, int expectedVersion)
    {
        storage.saveEvents(aggregate.getId(), aggregate.getUncommittedChanges(), expectedVersion);
    }

    @Override
    public T getById(UUID id)
    {
        T result = factory.newInstance();
        List<Event> events = storage.getEventsForAggregate(id);
        result.loadFromHistory(events);
        return result;
    }

}
