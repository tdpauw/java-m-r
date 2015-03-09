package simplecqrs;

/**
 * The purpose of a Bus is to dispatch incoming Commands to CommandHandlers and to dispatch published
 * events to EventHandlers.
 *
 * The UI sends commands as messages to the Bus.
 * The EventStore sends events as messages to the Bus.
 *
 * @author thipau
 */
public interface Bus extends CommandSender, EventPublisher
{
}
