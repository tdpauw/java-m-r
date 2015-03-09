package simplecqrs;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class FakeBusTest
{
    private final FakeBus sut = new FakeBus();

    @Test
    public void send()
    {
        @SuppressWarnings("unchecked")
        final Handler<CreateInventoryItem> handler = (Handler<CreateInventoryItem>) mock(Handler.class);
        when(handler.getType()).thenReturn(CreateInventoryItem.class);

        sut.registerHandler(handler);

        CreateInventoryItem message = new CreateInventoryItem(AggregateIds.anAggregateId(), "anInventoryItem");
        sut.send(message);

        verify(handler).handle(message);
    }

}