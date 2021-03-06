package ua.artcode.todo.server;

import com.google.gson.Gson;
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

public class RemoveTodoHandler extends AbstractHandler {
    private MainService mainService;
    private Gson gson;

    public RemoveTodoHandler(MainService mainService, Gson gson) {
        this.mainService = mainService;
        this.gson = gson;
    }

    @Override
    public void handle(String target, Request baseRequest,
                       HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        response.setContentType("application/json; charset=utf-8");

        Todo todo = gson.fromJson(new InputStreamReader(request.getInputStream()), Todo.class);

        boolean result = mainService.remove(todo.getId());

        PrintWriter out = response.getWriter();

        out.println(result);

        out.flush();

        baseRequest.setHandled(true);


    }

}
