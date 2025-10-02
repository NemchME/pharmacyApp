package org.example.command.availabilityOfMedicine;

import org.example.ConsoleApp;
import org.example.command.Command;
import org.example.menu.Menu;
import org.example.menu.impl.AvailabilityOfMedicineMenu;

public class FindAllCommand implements Command {

    @Override
    public Menu execute(ConsoleApp consoleApp) {
        System.out.println(consoleApp.getAvailabilityOfMedicineService().findAll());
        return new AvailabilityOfMedicineMenu();
    }
}
