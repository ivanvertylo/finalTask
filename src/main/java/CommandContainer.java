import java.util.Map;
import java.util.TreeMap;

public class CommandContainer {
    private static final Map<String, Command> commands = new TreeMap<String, Command>();

    public CommandContainer(){
        //commands.put("register", new RegisterCommand());
    }

    public static Command get(String commandName) {
        if (commandName == null || !commands.containsKey(commandName)) {
            return null;
        }
        return commands.get(commandName);
    }
}
