package simplecqrs;

/**
 * The purpose of the CommandSender is to send a command to the write model.
 * @author thipau
 */
public interface CommandSender<T extends Command>
{
    void send(T command);
}
