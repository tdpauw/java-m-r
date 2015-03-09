package simplecqrs;

/**
 * @author thipau
 */
public interface Handler<T extends Message>
{
    Class<T> getType();

    void handle(T message);
}
