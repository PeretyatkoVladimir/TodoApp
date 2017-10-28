package ua.artcode.todo.server;

import com.google.gson.Gson;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import ua.artcode.todo.model.Todo;
import ua.artcode.todo.service.MainService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * Created by serhii on 22.10.17.
 */
public class AddTodoHandler extends AbstractHandler {

    private MainService mainService;
    private Gson gson;

    public AddTodoHandler(MainService mainService) {
        this.mainService = mainService;
        this.gson = new Gson();
    }

    @Override
    public void handle(String target, Request baseRequest,
                       HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        response.setContentType("application/json; charset=utf-8");
        response.setStatus(HttpServletResponse.SC_CREATED);

        Todo todo = gson.fromJson(new InputStreamReader(request.getInputStream()),Todo.class);

        Todo ret = mainService.create(todo);

        PrintWriter out = response.getWriter();

        out.println(gson.toJson(ret));

        out.flush();

        // todo
        baseRequest.setHandled(true);

    }
}
