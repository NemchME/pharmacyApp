package org.example.menu.impl;

import org.example.ConsoleApp;
import org.example.exception.EntityNotFoundException;
import org.example.menu.CrudMenu;
import org.example.menu.Menu;
import org.example.model.User;

public class UserMenu implements Menu, CrudMenu {

    @Override
    public Menu execute(ConsoleApp consoleApp) {
        while (true) {
            try {
                System.out.println("""
                        Выберите действие:
                        1. Получить всех пользователей
                        2. Сохранить пользователя
                        3. Найти пользователя
                        4. Обновить пользователя
                        5. Удалить пользователя
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
        System.out.println(consoleApp.getUserService().findAll());
        return new UserMenu();
    }

    @Override
    public Menu createEntity(ConsoleApp consoleApp) {
        try {
            System.out.println("""
                    Введите данные о пользователе через запятую:
                    [username, password_hash, email, role].
                    Пример ввода: Пользователь, 1234@, test@test.com, админ
                    """);
            String[] entityArgs = consoleApp.getScanner().nextLine().split(",");

            if (entityArgs.length != 4) {
                throw new IllegalArgumentException(
                        "Введите следующие поля: username, password_hash, email, role");
            }
            User entity = new User(
                    entityArgs[0].trim(),
                    entityArgs[1].trim(),
                    entityArgs[2].trim(),
                    entityArgs[3].trim()
            );
            consoleApp.getUserService().save(entity);
            System.out.println("Сущность сохранена с id: " + entity.getId());
        } catch (IndexOutOfBoundsException e) {
            System.out.println(
                    "Ошибка: Введите следующие поля: username, password_hash, email, role");
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
        return new UserMenu();
    }

    @Override
    public Menu readEntity(ConsoleApp consoleApp) {
        try {
            System.out.println("Введите id пользователя: ");
            int id = Integer.parseInt(consoleApp.getScanner().nextLine().trim());
            System.out.println(consoleApp.getUserService().findById(id));
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());

        } catch (NumberFormatException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
        return new UserMenu();
    }

    @Override
    public Menu updateEntity(ConsoleApp consoleApp) {
        try {
            System.out.println("""
                    Введите обновленные данные через запятую:
                    [id, username, password_hash, email, role].
                    Пример ввода: 1, Пользователь, 1234@, test@test.com, админ.
                    """);
            String[] entityArgs = consoleApp.getScanner().nextLine().split(",");
            if (entityArgs.length != 5) {
                throw new IllegalArgumentException(
                        "Ошибка: Введите следующие поля: id, username, password_hash, email, role");
            }
            User entity = new User(
                    Integer.parseInt(entityArgs[0].trim()),
                    entityArgs[1].trim(),
                    entityArgs[2].trim(),
                    entityArgs[3].trim(),
                    entityArgs[4].trim()
            );
            consoleApp.getUserService().update(entity);
            System.out.println("Сущность обновлена с id: " + entity.getId());
        } catch (IndexOutOfBoundsException e) {
            System.out.println(
                    "Ошибка: Введите следующие поля: username, password_hash, email, role");
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
        return new UserMenu();
    }

    @Override
    public Menu deleteEntity(ConsoleApp consoleApp) {
        try {
            System.out.println("Введите id пользователя: ");
            int id = Integer.parseInt(consoleApp.getScanner().nextLine().trim());
            consoleApp.getUserService().delete(id);
            System.out.println("Сущность с id '" + id + "' удалена!");
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
        return new UserMenu();
    }
}
