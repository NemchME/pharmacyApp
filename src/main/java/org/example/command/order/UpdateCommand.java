package org.example.command.order;

import org.example.ConsoleApp;
import org.example.command.Command;
import org.example.menu.Menu;
import org.example.menu.impl.OrderMenu;
import org.example.model.Order;

public class UpdateCommand implements Command {

    @Override
    public Menu execute(ConsoleApp consoleApp) {
        try {
            System.out.println("""
                    Введите обновленные данные через запятую:
                    [id, user_id, medicine_id, pharmacy_id, quantily, status].
                    Пример ввода: 0, 0, 0, 0, 10, Создан
                    """);
            String[] entityArgs = consoleApp.getScanner().nextLine().split(",");
            if (entityArgs.length != 6) {
                throw new IllegalArgumentException(
                        "Ошибка: Введите следующие поля: id, user_id, medicine_id, pharmacy_id, quantily, status");
            }
            Order entity = new Order(
                    Integer.parseInt(entityArgs[0].trim()),
                    Integer.parseInt(entityArgs[1].trim()),
                    Integer.parseInt(entityArgs[2].trim()),
                    Integer.parseInt(entityArgs[3].trim()),
                    Integer.parseInt(entityArgs[4].trim()),
                    entityArgs[5].trim()
            );
            consoleApp.getOrderService().update(entity);
            System.out.println("Сущность обновлена с id: " + entity.getId());
        } catch (IndexOutOfBoundsException e) {
            System.out.println(
                    "Ошибка: Введите следующие поля: id, user_id, medicine_id, pharmacy_id, quantily, status");
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
        return new OrderMenu();
    }
}
