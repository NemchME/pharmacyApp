package org.example.command.mainMenu;

import org.example.ConsoleApp;
import org.example.command.Command;
import org.example.menu.Menu;
import org.example.menu.impl.MainMenu;

public class MainMenuCommand implements Command {

    @Override
    public Menu execute(ConsoleApp consoleApp) {
        return new MainMenu();
    }
}
