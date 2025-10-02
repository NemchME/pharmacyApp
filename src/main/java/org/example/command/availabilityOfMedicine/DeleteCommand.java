package org.example.command.availabilityOfMedicine;

import org.example.ConsoleApp;
import org.example.command.Command;
import org.example.menu.Menu;
import org.example.menu.impl.AvailabilityOfMedicineMenu;

public class DeleteCommand implements Command {

    @Override
    public Menu execute(ConsoleApp consoleApp) {
        try {
            System.out.println("Введите id данных о наличии препарата: ");
            int id = Integer.parseInt(consoleApp.getScanner().nextLine().trim());
            consoleApp.getAvailabilityOfMedicineService().delete(id);
            System.out.println("Сущность с id '" + id + "' удалена!");
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
        return new AvailabilityOfMedicineMenu();

    }
}
