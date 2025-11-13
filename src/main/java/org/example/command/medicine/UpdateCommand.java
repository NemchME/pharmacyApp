package org.example.command.medicine;

import org.example.ConsoleApp;
import org.example.command.Command;
import org.example.menu.Menu;
import org.example.menu.impl.MedicineMenu;
import org.example.model.Medicine;

public class UpdateCommand implements Command {

    @Override
    public Menu execute(ConsoleApp consoleApp) {
        try {
            System.out.println("""
                    Введите обновленные данные через запятую:
                    [id, trade_name, inn, dosage, form, producer_id].
                    Пример ввода: 0, Лекарство, инн, 20мг, форма, 0
                    """);
            String[] entityArgs = consoleApp.getScanner().nextLine().split(",");
            if (entityArgs.length != 6) {
                throw new IllegalArgumentException(
                        "Ошибка: Введите следующие поля: id, trade_name, inn, dosage, form, producer_id");
            }
            Medicine entity = new Medicine(
                    Integer.parseInt(entityArgs[0].trim()),
                    entityArgs[1].trim(),
                    entityArgs[2].trim(),
                    entityArgs[3].trim(),
                    entityArgs[4].trim(),
                    Integer.parseInt(entityArgs[5].trim())
            );
            consoleApp.getMedicineService().update(entity);
            System.out.println("Сущность обновлена");
        } catch (IndexOutOfBoundsException e) {
            System.out.println(
                    "Ошибка: Введите следующие поля: id, trade_name, inn, dosage, form, producer_id");
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
        return new MedicineMenu();
    }
}
