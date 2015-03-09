package simplecqrs;

/**
 * The purpose of an EventPublisher is to publish Events to the read model.
 *
 * @author thipau
 */
public interface EventPublisher<T extends Event>
{
    void publish(T event);
}
