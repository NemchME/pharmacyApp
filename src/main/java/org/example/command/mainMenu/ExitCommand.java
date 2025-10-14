package org.example.command.mainMenu;

import org.example.ConsoleApp;
import org.example.command.Command;
import org.example.menu.Menu;

public class ExitCommand implements Command {
    @Override
    public Menu execute(ConsoleApp consoleApp) {
        System.out.println("Выход...");
        System.exit(1);
        return null;
    }
}
