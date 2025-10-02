package org.example.command.mainMenu;

import org.example.ConsoleApp;
import org.example.command.Command;
import org.example.menu.Menu;
import org.example.menu.impl.ProducerMenu;

public class ProducerCommand implements Command {

    @Override
    public Menu execute(ConsoleApp consoleApp) {
        return new ProducerMenu();
    }
}
