package ua.artcode.todo;

import com.google.gson.Gson;
import org.eclipse.jetty.server.*;
import org.eclipse.jetty.server.handler.*;
import ua.artcode.todo.dao.TodoDaoImp;
import ua.artcode.todo.server.AddTodoHandler;
import ua.artcode.todo.server.GetAllTodoHandler;
import ua.artcode.todo.server.HelloHandler;
import ua.artcode.todo.server.RemoveTodoHandler;
import ua.artcode.todo.service.MainService;
import ua.artcode.todo.service.MainServiceImpl;

import java.io.File;

public class RunServer {

    public static void main(String[] args) throws Exception {

        Gson gson = new Gson();
        MainService mainService = new MainServiceImpl(new TodoDaoImp());

        Server server = new Server(getServerPort());

        server.setRequestLog(
                (request, response) ->
                        System.out.println(request.toString() + "\n" + response));

        server.setErrorHandler(new ErrorHandler());

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{createResourceHandler("/view", "/todo.html" ),
                createContHandler("/add-todo", new AddTodoHandler(mainService, gson)),
                createContHandler("/hello",    new HelloHandler()),
                createContHandler("/getall",   new GetAllTodoHandler(mainService, gson)),
                createContHandler("/rem-todo", new RemoveTodoHandler(mainService, gson))});

        server.setHandler(handlers);

        server.start();
        server.join();
    }

    static private int getServerPort(){

        String SERVER_PORT = System.getenv("PORT");
        if(SERVER_PORT == null){
            SERVER_PORT = "5000";
        }
        return Integer.parseInt(SERVER_PORT);
    }

    static private ResourceHandler createResourceHandler(String resourcePath, String welcomeFile){

        ResourceHandler resHandler = new ResourceHandler();
        resHandler.setDirectoriesListed(true);
        resHandler.setWelcomeFiles(new String[]{ welcomeFile });

        File resourceBase = new File(RunServer.class.getResource(resourcePath).getFile());
        resHandler.setResourceBase(resourceBase.getAbsolutePath());

        return resHandler;
    }

    static private ContextHandler createContHandler(String contextPath, AbstractHandler handler){

        ContextHandler contextHandler = new ContextHandler();
        contextHandler.setContextPath(contextPath);
        contextHandler.setHandler(handler);
        contextHandler.setAllowNullPathInfo(true);

        return contextHandler;
    }

}
