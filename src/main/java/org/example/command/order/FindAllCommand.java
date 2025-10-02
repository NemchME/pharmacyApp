package org.example.command.order;

import org.example.ConsoleApp;
import org.example.command.Command;
import org.example.menu.Menu;
import org.example.menu.impl.OrderMenu;

public class FindAllCommand implements Command {

    @Override
    public Menu execute(ConsoleApp consoleApp) {
        System.out.println(consoleApp.getOrderService().findAll());
        return new OrderMenu();
    }
}
