import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Logger;

@WebServlet("/controller")
public class Controller extends HttpServlet {
    private static final Logger log = Logger.getLogger(Controller.class.getName());
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp){
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp){
        process(req, resp);
    }

    private void process(HttpServletRequest request, HttpServletResponse response){
        String commandName = request.getParameter("command");
        Command command = CommandContainer.get(commandName);
        try{
            String forward = command.execute(request, response);
            request.getRequestDispatcher(forward).forward(request, response);
        }
        catch (Exception e){
            log.info(e.getMessage());
        }
    }
}
