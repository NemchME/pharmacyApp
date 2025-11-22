package org.example.command.availabilityOfMedicine;

import org.example.ConsoleApp;
import org.example.command.Command;
import org.example.command.validator.Validator;
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
                    Integer.parseInt(Validator.requireNotBlank(entityArgs[0], "Введите pharmacy_id")),
                    Integer.parseInt(Validator.requireNotBlank(entityArgs[1], "Введите medicine_id")),
                    Float.parseFloat(Validator.requireNotBlank(entityArgs[2], "Введите medicine_id")),
                    Integer.parseInt(Validator.requireNotBlank(entityArgs[3], "Введите quantily"))
            );
            consoleApp.getAvailabilityOfMedicineService().save(entity);
            System.out.println("Сущность сохранена!");
        } catch (IndexOutOfBoundsException e) {
            System.out.println(
                    "Ошибка: Введите следующие поля: pharmacy_id, medicine_id, price, quantily");
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
        return new AvailabilityOfMedicineMenu();
    }
}
