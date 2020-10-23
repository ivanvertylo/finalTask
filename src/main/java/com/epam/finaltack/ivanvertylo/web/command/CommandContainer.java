package com.epam.finaltack.ivanvertylo.web.command;

import com.epam.finaltack.ivanvertylo.Constant;
import com.epam.finaltack.ivanvertylo.db.service.impl.QuestionServiceImpl;
import com.epam.finaltack.ivanvertylo.db.service.impl.TestServiceImpl;
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
        commands.put(Constant.COMMAND_CREATE_TEST, new CreateTest(new TestServiceImpl()));
        commands.put(Constant.COMMAND_FIND_LOGIN, new FindLogin(new UserServiceImpl()));
        commands.put(Constant.COMMAND_BLOCK_USER, new BlockUser(new UserServiceImpl()));
        commands.put(Constant.COMMAND_SAVE_QUESTION, new SaveQuestion());
        commands.put(Constant.COMMAND_UPDATE_QUESTION, new UpdateQuestion(new QuestionServiceImpl()));
        commands.put(Constant.COMMAND_UPDATE_TEST, new UpdateTestInfo(new TestServiceImpl()));
        commands.put(Constant.COMMAND_CHECK_TEST, new CheckTest(new TestServiceImpl(),new QuestionServiceImpl()));
        commands.put(Constant.COMMAND_FIND_SUBJECT, new FindSubject(new TestServiceImpl()));
        commands.put(Constant.COMMAND_LEAVE_TEST, new LeaveTest(new TestServiceImpl()));
        commands.put(Constant.COMMAND_CHANGE_USERNAME, new ChangeUsername(new UserServiceImpl()));
        commands.put(Constant.COMMAND_DELETE_POINTS, new DeletePoints(new TestServiceImpl()));
        commands.put(Constant.COMMAND_DELETE_QUESTION, new DeleteQuestion(new QuestionServiceImpl()));
        commands.put(Constant.COMMAND_CHANGE_PASSWORD, new ChangePassword(new UserServiceImpl()));
        commands.put(Constant.COMMAND_CHANGE_LOCALE, new ChangeLocale());
        commands.put(Constant.COMMAND_DELETE_TEST, new DeleteTest(new TestServiceImpl()));
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
