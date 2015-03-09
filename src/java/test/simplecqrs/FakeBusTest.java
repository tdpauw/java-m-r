package simplecqrs;

import org.junit.Test;

import static org.mockito.Mockito.*;

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


    @Test(expected=UnsupportedOperationException.class)
    public void sendMessageWithTwoHandlers()
    {
        @SuppressWarnings("unchecked")
        final Handler<CreateInventoryItem> handler1 = mock(Handler.class);
        when(handler1.getType()).thenReturn(CreateInventoryItem.class);
        sut.registerHandler(handler1);

        @SuppressWarnings("unchecked")
        final Handler<CreateInventoryItem> handler2 = mock(Handler.class);
        when(handler2.getType()).thenReturn(CreateInventoryItem.class);
        sut.registerHandler(handler2);

        CreateInventoryItem message = new CreateInventoryItem(AggregateIds.anAggregateId(), "anInventoryItem");
        sut.send(message);
    }

    @Test
    public void publish()
    {
        @SuppressWarnings("unchecked")
        final Handler<InventoryItemCreated> handler = (Handler<InventoryItemCreated>) mock(Handler.class);
        when(handler.getType()).thenReturn(InventoryItemCreated.class);

        sut.registerHandler(handler);
        sut.registerHandler(handler);

        InventoryItemCreated message = new InventoryItemCreated(AggregateIds.anAggregateId(), "anInventoryItem");
        sut.publish(message);

        verify(handler, times(2)).handle(message);
    }
}