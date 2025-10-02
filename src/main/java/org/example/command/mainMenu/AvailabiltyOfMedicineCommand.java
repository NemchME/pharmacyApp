package org.example.command.mainMenu;

import org.example.ConsoleApp;
import org.example.command.Command;
import org.example.menu.Menu;
import org.example.menu.impl.AvailabilityOfMedicineMenu;

public class AvailabiltyOfMedicineCommand implements Command {

    @Override
    public Menu execute(ConsoleApp consoleApp) {
        return new AvailabilityOfMedicineMenu();
    }
}
