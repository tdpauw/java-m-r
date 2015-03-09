package simplecqrs;

import java.util.List;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;

/**
 * @author thipau
 */
public class FakeBus implements Bus
{

    private final ListMultimap<Class, Handler> routes = ArrayListMultimap.create();

    public <T extends Message> void registerHandler(Handler<T> handler) {
        routes.put(handler.getType(), handler);
    }

    public void send(Command command)
    {
        List<Handler> handlers = routes.get(command.getClass());
        if (handlers.size() > 1)
        {
            throw new UnsupportedOperationException("Cannot send a command to more than one handler.");
        }
        handlers.get(0).handle(command);
    }

    public void publish(Event event)
    {
        List<Handler> handlers = routes.get(event.getClass());

        for(Handler handler : handlers)
        {
            handler.handle(event);
        }
    }
}
