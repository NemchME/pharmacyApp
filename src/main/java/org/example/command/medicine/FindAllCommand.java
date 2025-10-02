package org.example.command.medicine;

import org.example.ConsoleApp;
import org.example.command.Command;
import org.example.menu.Menu;
import org.example.menu.impl.MedicineMenu;

public class FindAllCommand implements Command {

    @Override
    public Menu execute(ConsoleApp consoleApp) {
        System.out.println(consoleApp.getMedicineService().findAll());
        return new MedicineMenu();
    }
}
