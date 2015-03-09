package simplecqrs;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;

public class CreateInventoryCommandHandlerTest
{

    @SuppressWarnings("unchecked")
    private final Repository<InventoryItem> repository = mock(Repository.class);
    private final CreateInventoryCommandHandler sut = new CreateInventoryCommandHandler(repository);

    @Test
    public void getType() throws Exception
    {
        assertThat(sut.getType(), is(equalTo(CreateInventoryItem.class)));
    }

    @Test
    public void handle() throws Exception
    {

    }
}