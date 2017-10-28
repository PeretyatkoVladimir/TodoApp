package ua.artcode.todo;

import com.mashape.unirest.http.Unirest;
import org.eclipse.jetty.server.*;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ErrorHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import ua.artcode.todo.dao.TodoDaoImp;
import ua.artcode.todo.server.AddTodoHandler;
import ua.artcode.todo.server.HelloHandler;
import ua.artcode.todo.service.MainService;
import ua.artcode.todo.service.MainServiceImpl;
import ua.artcode.todo.utils.JsonUtils;

import java.io.File;

/**
 * Created by serhii on 22.10.17.
 */
public class RunServer {

    public static void main(String[] args) throws Exception {

        MainService mainService = new MainServiceImpl(new TodoDaoImp());

        String SERVER_PORT = System.getenv("PORT");
        Server server = new Server(Integer.parseInt(SERVER_PORT));
        server.setRequestLog(new RequestLog() {
            @Override
            public void log(Request request, Response response) {
                System.out.println(request.toString() + "\n" + response);
            }
        });


        server.setErrorHandler(new ErrorHandler());

        HandlerList handlers = new HandlerList();

        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setDirectoriesListed(true);
        resource_handler.setWelcomeFiles(new String[]{ "todo.html" });

        File resourceBase = new File(RunServer.class.getResource("/view").getFile());
        resource_handler.setResourceBase(resourceBase.getAbsolutePath());


        ContextHandler contextHandler1 = new ContextHandler();
        contextHandler1.setContextPath("/add-todo");
        contextHandler1.setHandler(new AddTodoHandler(mainService));
        contextHandler1.setAllowNullPathInfo(true);

        ContextHandler contextHandler2 = new ContextHandler();
        contextHandler1.setContextPath("/hello");
        contextHandler1.setHandler(new HelloHandler());
        contextHandler1.setAllowNullPathInfo(true);

        handlers.setHandlers(new Handler[]{resource_handler, contextHandler1});


        server.setHandler(handlers);

        server.start();
        server.join();
    }

}
