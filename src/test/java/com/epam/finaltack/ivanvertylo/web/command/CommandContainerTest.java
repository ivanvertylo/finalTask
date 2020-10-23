package com.epam.finaltack.ivanvertylo.web.command;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CommandContainerTest {
    private CommandContainer commandContainer;
    @Before
    public void before(){
        commandContainer = new CommandContainer();
    }
    @Test
    public void test(){
        commandContainer.get("");
    }
}
