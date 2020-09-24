package com.epam.finaltack.ivanvertylo.web.command;

import com.epam.finaltack.ivanvertylo.Constant;

import java.util.Map;
import java.util.TreeMap;

public class CommandContainer {
    private static final Map<String, Command> commands = new TreeMap<>();

    public CommandContainer(){
        commands.put(Constant.COMMAND_LOGIN, new LoginReg());
        commands.put(Constant.COMMAND_LOGOUT, new Logout());
    }

    public Command get(String commandName) {
        if (commandName == null || !commands.containsKey(commandName)) {
            return null;
        }
        return commands.get(commandName);
    }
}
