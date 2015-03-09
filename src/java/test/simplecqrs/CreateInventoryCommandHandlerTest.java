package simplecqrs;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class CreateInventoryCommandHandlerTest
{

    @SuppressWarnings("unchecked")
    private final Repository<InventoryItem> repository = mock(Repository.class);
    private final CreateInventoryItemCommandHandler sut = new CreateInventoryItemCommandHandler(repository);

    @Test
    public void getType() throws Exception
    {
        assertThat(sut.getType(), is(equalTo(CreateInventoryItem.class)));
    }

    @Test
    public void handle() throws Exception
    {
        final CreateInventoryItem anInventoryItem = new CreateInventoryItem(AggregateIds.anAggregateId(), "anInventoryItem");
        sut.handle(anInventoryItem);

        verify(repository).save(new InventoryItem(AggregateIds.anAggregateId(), "anInventoryItem"), -1);
    }
}