import org.apache.log4j.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;


@WebServlet("/controller")
public class Controller extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(Controller.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        process(req, resp);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) {
        String commandName = request.getParameter("command");
        CommandContainer commandContainer = new CommandContainer();
        Command command = commandContainer.get(commandName);
        try{
            String forward = command.execute(request, response);
            request.getRequestDispatcher(forward).forward(request, response);
        }
        catch (Exception e){
            LOGGER.error(e.getMessage());
        }
    }
    public static String getUTF8(HttpServletRequest request, String parameter){
        String item = request.getParameter(parameter);
        byte[] bytes = item.getBytes(StandardCharsets.ISO_8859_1);
        return new String(bytes, StandardCharsets.UTF_8);
    }
}
