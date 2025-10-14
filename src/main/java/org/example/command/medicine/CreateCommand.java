package org.example.command.medicine;

import org.example.ConsoleApp;
import org.example.command.Command;
import org.example.command.validator.Validator;
import org.example.menu.Menu;
import org.example.menu.impl.MedicineMenu;
import org.example.model.Medicine;

public class CreateCommand implements Command {

    @Override
    public Menu execute(ConsoleApp consoleApp) {
        try {
            System.out.println("""
                    Введите данные о препарате через запятую:
                    [trade_name, inn, dosage, form, producer_id].
                    Пример ввода: Лекарство, инн, 20мг, форма, 0
                    """);
            String[] entityArgs = consoleApp.getScanner().nextLine().split(",");

            if (entityArgs.length != 5) {
                throw new IllegalArgumentException(
                        "Введите следующие поля: trade_name, inn, dosage, form, producer_id");
            }
            Medicine entity = new Medicine(
                    Validator.requireNotBlank(entityArgs[0], "Введите trade_name"),
                    Validator.requireNotBlank(entityArgs[1], "Введите inn"),
                    Validator.requireNotBlank(entityArgs[2], "Введите dosage"),
                    Validator.requireNotBlank(entityArgs[3], "Введите form"),
                    Integer.parseInt(Validator.requireNotBlank(entityArgs[4], "Введите producer_id"))
            );
            consoleApp.getMedicineService().save(entity);
            System.out.println("Сущность сохранена с id: " + entity.getId());
        } catch (IndexOutOfBoundsException e) {
            System.out.println(
                    "Ошибка: Введите следующие поля: trade_name, inn, dosage, form, producer_id");
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
        return new MedicineMenu();
    }
}
