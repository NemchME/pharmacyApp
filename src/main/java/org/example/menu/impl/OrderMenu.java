package org.example.menu.impl;

import org.example.ConsoleApp;
import org.example.exception.EntityNotFoundException;
import org.example.menu.CrudMenu;
import org.example.menu.Menu;
import org.example.model.Order;


public class OrderMenu implements Menu, CrudMenu {

    @Override
    public Menu execute(ConsoleApp consoleApp) {
        while (true) {
            try {
                System.out.println("""
                    Выберите действие:
                    1. Получить все заказы
                    2. Сохранить заказ
                    3. Найти заказ
                    4. Обновить заказ
                    5. Удалить заказ
                    6. Перейти в главное меню
                    """);

                String choice = consoleApp.getScanner().nextLine();
                return switch (choice) {
                    case "1" -> findAllEntities(consoleApp);
                    case "2" -> createEntity(consoleApp);
                    case "3" -> readEntity(consoleApp);
                    case "4" -> updateEntity(consoleApp);
                    case "5" -> deleteEntity(consoleApp);
                    case "6" -> new MainMenu();
                    default -> throw new IllegalArgumentException("Неверный ввод! Введите число от 1 до 6.");
                };
            } catch (Exception e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }
    }

    @Override
    public Menu findAllEntities(ConsoleApp consoleApp) {
        System.out.println(consoleApp.getOrderService().findAll());
        return new OrderMenu();
    }

    @Override
    public Menu createEntity(ConsoleApp consoleApp) {
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

    @Override
    public Menu readEntity(ConsoleApp consoleApp) {
        try {
            System.out.println("Введите id заказа: ");
            int id = Integer.parseInt(consoleApp.getScanner().nextLine().trim());
            System.out.println(consoleApp.getOrderService().findById(id));
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());

        } catch (NumberFormatException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
        return new OrderMenu();
    }

    @Override
    public Menu updateEntity(ConsoleApp consoleApp) {
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

    @Override
    public Menu deleteEntity(ConsoleApp consoleApp) {
        try {
            System.out.println("Введите id заказа: ");
            int id = Integer.parseInt(consoleApp.getScanner().nextLine().trim());
            consoleApp.getOrderService().delete(id);
            System.out.println("Сущность с id '" + id + "' удалена!");
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
        return new OrderMenu();
    }
}
