package simplecqrs;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;

/**
 * @author thipau
 */
public class InMemoryEventStore implements EventStore
{
    private static class EventDescriptor
    {

        public final Event eventData;
        public final UUID id;
        public final int version;

        public EventDescriptor(UUID id, Event eventData, int version)
        {
            this.id = id;
            this.eventData = eventData;
            this.version = version;
        }
    }

    private final ListMultimap<UUID, EventDescriptor> store =  ArrayListMultimap.create();

    @Override
    public void saveEvents(UUID aggregateId, List<Event> events, int expectedVersion)
    {

        // try to get event descriptors list for given aggregate id
        if (store.containsKey(aggregateId)) {
            List<EventDescriptor> eventDescriptors = store.get(aggregateId);

            // check whether latest event version matches current aggregate version
            // otherwise -> throw exception
            EventDescriptor lastestEvent = eventDescriptors.get(eventDescriptors.size() - 1);
            if(lastestEvent.version != expectedVersion && expectedVersion != -1)
            {
                throw new ConcurrencyException();
            }
        }

        int i = expectedVersion;

        // iterate through current aggregate events increasing version with each processed event
        for(Event event : events)
        {
            i++;
            event.version = i;

            // push event to the event descriptors list for current aggregate
            store.put(aggregateId, new EventDescriptor(aggregateId, event, i));

            // publish current event to the bus for further processing by subscribers
            //_publisher.Publish(@event);
        }
    }

    @Override
    public List<Event> getEventsForAggregate(UUID aggregateId)
    {
        List<EventDescriptor> eventDescriptors = store.get(aggregateId);
        if (eventDescriptors.isEmpty())
        {
            throw new AggregateNotFoundException();
        }
        return eventDescriptors.stream().map(eventDescriptor -> eventDescriptor.eventData).collect(Collectors.toList());
    }
}
