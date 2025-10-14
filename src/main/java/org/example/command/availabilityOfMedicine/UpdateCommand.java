package org.example.command.availabilityOfMedicine;

import org.example.ConsoleApp;
import org.example.command.Command;
import org.example.menu.Menu;
import org.example.menu.impl.AvailabilityOfMedicineMenu;
import org.example.model.AvailabilityOfMedicine;

public class UpdateCommand implements Command {

    @Override
    public Menu execute(ConsoleApp consoleApp) {
        try {
            System.out.println("""
                    Введите обновленные данные через запятую:
                    [id, pharmacy_id, medicine_id, price, quantily].
                    Пример ввода: 0, 0, 0, 10.0, 2
                    """);
            String[] entityArgs = consoleApp.getScanner().nextLine().split(",");
            if (entityArgs.length != 5) {
                throw new IllegalArgumentException(
                        "Ошибка: Введите следующие поля: id, pharmacy_id, medicine_id, price, quantily");
            }
            AvailabilityOfMedicine entity = new AvailabilityOfMedicine(
                    Integer.parseInt(entityArgs[0].trim()),
                    Integer.parseInt(entityArgs[1].trim()),
                    Integer.parseInt(entityArgs[2].trim()),
                    Float.parseFloat(entityArgs[3].trim()),
                    Integer.parseInt(entityArgs[4].trim())
            );
            consoleApp.getAvailabilityOfMedicineService().update(entity);
            System.out.println("Сущность обновлена");
        } catch (IndexOutOfBoundsException e) {
            System.out.println(
                    "Ошибка: Введите следующие поля: id, pharmacy_id, medicine_id, price, quantily");
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
        return new AvailabilityOfMedicineMenu();
    }
}
