package com.epam.finaltack.ivanvertylo.web.command;

import com.epam.finaltack.ivanvertylo.Constant;
import com.epam.finaltack.ivanvertylo.db.service.impl.UserServiceImpl;
import org.apache.log4j.Logger;

import java.util.Map;
import java.util.TreeMap;

public class CommandContainer {
    private final Map<String, Command> commands = new TreeMap<>();
    private static final Logger LOG = Logger.getLogger(CommandContainer.class);

    public CommandContainer() {
        commands.put(Constant.COMMAND_LOGIN, new LoginReg());
        commands.put(Constant.COMMAND_LOGOUT, new Logout());
        commands.put(Constant.COMMAND_ADMIN, new Admin());
        commands.put(Constant.COMMAND_CREATE_TEST, new CreateTest());
        commands.put(Constant.COMMAND_FIND_LOGIN, new FindLogin(new UserServiceImpl()));
        commands.put(Constant.COMMAND_BLOCK_USER, new BlockUser(new UserServiceImpl()));
        commands.put(Constant.COMMAND_SAVE_QUESTION, new SaveQuestion());
        commands.put(Constant.COMMAND_UPDATE_QUESTION, new UpdateQuestion());
        commands.put(Constant.COMMAND_UPDATE_TEST, new UpdateTestInfo());
        commands.put(Constant.COMMAND_CHECK_TEST, new CheckTest());
        commands.put(Constant.COMMAND_FIND_SUBJECT, new FindSubject());
        commands.put(Constant.COMMAND_LEAVE_TEST, new LeaveTest());
        commands.put(Constant.COMMAND_CHANGE_USERNAME, new ChangeUsername());
        commands.put(Constant.COMMAND_DELETE_POINTS, new DeletePoints());
        commands.put(Constant.COMMAND_DELETE_QUESTION, new DeleteQuestion());
        commands.put(Constant.COMMAND_CHANGE_PASSWORD, new ChangePassword());
        commands.put(Constant.COMMAND_CHANGE_LOCALE, new ChangeLocale());
        commands.put(Constant.COMMAND_DELETE_TEST, new DeleteTest());
        commands.put(Constant.COMMAND_FIND_TEST_BY_NAME, new FindTestByName());
    }

    public Command get(String commandName) {
        if (commandName == null || !commands.containsKey(commandName)) {
            LOG.info("Command: null");
            return null;
        }
        LOG.info("Command: " + commands.get(commandName));
        return commands.get(commandName);
    }
}
