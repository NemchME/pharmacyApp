package org.example.command.order;

import org.example.ConsoleApp;
import org.example.command.Command;
import org.example.command.validator.Validator;
import org.example.menu.Menu;
import org.example.menu.impl.OrderMenu;
import org.example.model.Order;

public class CreateCommand implements Command {

    @Override
    public Menu execute(ConsoleApp consoleApp) {
        try {
            System.out.println("""
                    Введите данные о заказе через запятую:
                    [user_id, medicine_id, pharmacy_id, quantily, status].
                    Пример ввода: 0, 0, 0, 10, Создан
                    """);
            String[] entityArgs = consoleApp.getScanner().nextLine().split(",");

            if (entityArgs.length != 5) {
                throw new IllegalArgumentException(
                        "Введите следующие поля: user_id, medicine_id, pharmacy_id, quantily, status");
            }
            Order entity = new Order(
                    Integer.parseInt(Validator.requireNotBlank(entityArgs[0], "Введите user_id")),
                    Integer.parseInt(Validator.requireNotBlank(entityArgs[1], "Введите medicine_id")),
                    Integer.parseInt(Validator.requireNotBlank(entityArgs[2], "Введите pharmacy_id")),
                    Integer.parseInt(Validator.requireNotBlank(entityArgs[3], "Введите quantily")),
                    Validator.requireNotBlank(entityArgs[4], "Введите status")
            );
            consoleApp.getOrderService().save(entity);
            System.out.println("Сущность сохранена");
        } catch (IndexOutOfBoundsException e) {
            System.out.println(
                    "Ошибка: Введите следующие поля: user_id, medicine_id, pharmacy_id, quantily, status");
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
        return new OrderMenu();
    }
}
