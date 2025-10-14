package org.example.command;

import org.example.ConsoleApp;
import org.example.menu.Menu;

public interface Command {
    Menu execute(ConsoleApp consoleApp);
}
