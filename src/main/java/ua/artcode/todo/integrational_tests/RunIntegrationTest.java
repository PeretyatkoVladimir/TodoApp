package ua.artcode.todo.integrational_tests;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.body.Body;
import org.junit.Assert;
import org.junit.Test;
import ua.artcode.todo.model.Todo;
import ua.artcode.todo.utils.JsonUtils;

/**
 * Created by serhii on 22.10.17.
 */
public class RunIntegrationTest {


    static {
        Unirest.setObjectMapper(JsonUtils.getObjectMapper());
    }

    @Test
    public void testAddTodo() throws UnirestException {
        Todo forSend = new Todo(1, "testTitle", "testBody", false);

        HttpResponse<Todo> response = Unirest.post("http://localhost:8080/add-todo")
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .body(forSend)
                .asObject(Todo.class);

        Todo todoReturned = response.getBody();

        Assert.assertEquals(201, response.getStatus());
        Assert.assertEquals(forSend.getId(),todoReturned.getId());
    }


}
