package org.example.menu.impl;

import org.example.ConsoleApp;
import org.example.exception.EntityNotFoundException;
import org.example.menu.CrudMenu;
import org.example.menu.Menu;
import org.example.model.Producer;

public class ProducerMenu implements Menu, CrudMenu {

    @Override
    public Menu execute(ConsoleApp consoleApp) {
        while (true) {
            try {
                System.out.println("""
                        Выберите действие:
                        1. Получить всех поставщиков
                        2. Сохранить поставщика
                        3. Найти поставщика
                        4. Обновить поставщика
                        5. Удалить поставщика
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
        System.out.println(consoleApp.getProducerService().findAll());
        return new ProducerMenu();
    }

    @Override
    public Menu createEntity(ConsoleApp consoleApp) {
        try {
            System.out.println("""
                    Введите данные о пользователе через запятую:
                    [name, country].
                    Пример ввода: Фарма, Германия
                    """);
            String[] entityArgs = consoleApp.getScanner().nextLine().split(",");

            if (entityArgs.length != 2) {
                throw new IllegalArgumentException(
                        "Ошибка: Введите следующие поля: name, country");
            }
            Producer entity = new Producer(
                    entityArgs[0].trim(),
                    entityArgs[1].trim()
            );
            consoleApp.getProducerService().save(entity);
            System.out.println("Сущность сохранена с id: " + entity.getId());
        } catch (IndexOutOfBoundsException e) {
            System.out.println(
                    "Ошибка: Введите следующие поля: name, country");
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
        return new ProducerMenu();
    }

    @Override
    public Menu readEntity(ConsoleApp consoleApp) {
        try {
            System.out.println("Введите id производителя: ");
            int id = Integer.parseInt(consoleApp.getScanner().nextLine().trim());
            System.out.println(consoleApp.getProducerService().findById(id));
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());

        } catch (NumberFormatException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
        return new ProducerMenu();
    }

    @Override
    public Menu updateEntity(ConsoleApp consoleApp) {
        try {
            System.out.println("""
                    Введите обновленные данные через запятую:
                    [id, name, country].
                    Пример ввода: 1, Фарма, Германия
                    """);
            String[] entityArgs = consoleApp.getScanner().nextLine().split(",");
            if (entityArgs.length != 3) {
                throw new IllegalArgumentException(
                        "Введите следующие поля: id, name, country");
            }
            Producer entity = new Producer(
                    Integer.parseInt(entityArgs[0].trim()),
                    entityArgs[1].trim(),
                    entityArgs[2].trim()
            );
            consoleApp.getProducerService().update(entity);
            System.out.println("Сущность обновлена с id: " + entity.getId());
        } catch (IndexOutOfBoundsException e) {
            System.out.println(
                    "Ошибка: Введите следующие поля: name, country");
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
        return new ProducerMenu();
    }

    @Override
    public Menu deleteEntity(ConsoleApp consoleApp) {
        try {
            System.out.println("Введите id производителя: ");
            int id = Integer.parseInt(consoleApp.getScanner().nextLine().trim());
            consoleApp.getProducerService().delete(id);
            System.out.println("Сущность с id '" + id + "' удалена!");
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
        return new ProducerMenu();
    }
}
