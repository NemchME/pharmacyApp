package org.example.command.user;

import org.example.ConsoleApp;
import org.example.command.Command;
import org.example.menu.Menu;
import org.example.menu.impl.UserMenu;

public class FindAllCommand implements Command {

    @Override
    public Menu execute(ConsoleApp consoleApp) {
        System.out.println(consoleApp.getUserService().findAll());
        return new UserMenu();
    }
}
