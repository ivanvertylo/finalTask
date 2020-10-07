package com.epam.finaltack.ivanvertylo.web.command;

import com.epam.finaltack.ivanvertylo.Constant;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class CommandContainer {
    private final Map<String, Command> commands = new TreeMap<>();

    public CommandContainer(){
        commands.put(Constant.COMMAND_LOGIN, new LoginReg());
        commands.put(Constant.COMMAND_LOGOUT, new Logout());
        commands.put(Constant.COMMAND_ADMIN, new Admin());
        commands.put(Constant.COMMAND_CREATE_TEST, new CreateTest());
        commands.put(Constant.COMMAND_FIND_LOGIN, new FindLogin());
        commands.put(Constant.COMMAND_BLOCK_USER, new BlockUser());
    }

    public Command get(String commandName) {
        if (commandName == null || !commands.containsKey(commandName)) {
            return null;
        }
        return commands.get(commandName);
    }
}
