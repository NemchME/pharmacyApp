package org.example.command.availabilityOfMedicine;

import org.example.ConsoleApp;
import org.example.command.Command;
import org.example.menu.Menu;
import org.example.menu.impl.AvailabilityOfMedicineMenu;
import org.example.model.AvailabilityOfMedicine;

public class CreateCommand implements Command {

    @Override
    public Menu execute(ConsoleApp consoleApp) {
        try {
            System.out.println("""
                        Введите данные о наличии препарата через запятую:
                        [pharmacy_id, medicine_id, price, quantily].
                        Пример ввода: 0, 0, 10.0, 2
                        """);
            String[] entityArgs = consoleApp.getScanner().nextLine().split(",");

            if (entityArgs.length != 4) {
                throw new IllegalArgumentException(
                        "Введите следующие поля: pharmacy_id, medicine_id, price, quantily");
            }
            AvailabilityOfMedicine entity = new AvailabilityOfMedicine(
                    Integer.parseInt(entityArgs[0].trim()),
                    Integer.parseInt(entityArgs[1].trim()),
                    Float.parseFloat(entityArgs[2].trim()),
                    Integer.parseInt(entityArgs[3].trim())
            );
            consoleApp.getAvailabilityOfMedicineService().save(entity);
            System.out.println("Сущность сохранена с id: " + entity.getId());
        } catch (IndexOutOfBoundsException e) {
            System.out.println(
                    "Ошибка: Введите следующие поля: pharmacy_id, medicine_id, price, quantily");
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
        return new AvailabilityOfMedicineMenu();
    }
}
