package org.example.command.order;

import org.example.ConsoleApp;
import org.example.command.Command;
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
                    Integer.parseInt(entityArgs[0].trim()),
                    Integer.parseInt(entityArgs[1].trim()),
                    Integer.parseInt(entityArgs[2].trim()),
                    Integer.parseInt(entityArgs[3].trim()),
                    entityArgs[4].trim()
            );
            consoleApp.getOrderService().save(entity);
            System.out.println("Сущность сохранена с id: " + entity.getId());
        } catch (IndexOutOfBoundsException e) {
            System.out.println(
                    "Ошибка: Введите следующие поля: user_id, medicine_id, pharmacy_id, quantily, status");
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
        return new OrderMenu();
    }
}
