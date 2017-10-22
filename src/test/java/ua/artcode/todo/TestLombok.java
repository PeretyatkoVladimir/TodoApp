package ua.artcode.todo;

import org.junit.Assert;
import org.junit.Test;
import ua.artcode.todo.model.Todo;

/**
 * Created by serhii on 22.10.17.
 */
public class TestLombok {

    @Test
    public void test(){
        Todo todo = new Todo();
        todo.setId(1);

        Assert.assertEquals(1,todo.getId());
    }
}
