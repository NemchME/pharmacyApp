package org.example.menu.impl;

import org.example.ConsoleApp;
import org.example.exception.EntityNotFoundException;
import org.example.menu.CrudMenu;
import org.example.menu.Menu;
import org.example.model.Medicine;

public class MedicineMenu implements Menu, CrudMenu {

    @Override
    public Menu execute(ConsoleApp consoleApp) {
        while (true) {
            try {
                System.out.println("""
                    Выберите действие:
                    1. Получить все препараты
                    2. Сохранить препарат
                    3. Найти препарат
                    4. Обновить препарат
                    5. Удалить препарат
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
        System.out.println(consoleApp.getMedicineService().findAll());
        return new MedicineMenu();
    }

    @Override
    public Menu createEntity(ConsoleApp consoleApp) {
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
                    entityArgs[0].trim(),
                    entityArgs[1].trim(),
                    entityArgs[2].trim(),
                    entityArgs[3].trim(),
                    Integer.parseInt(entityArgs[4].trim())
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

    @Override
    public Menu readEntity(ConsoleApp consoleApp) {
        try {
            System.out.println("Введите id препарата: ");
            int id = Integer.parseInt(consoleApp.getScanner().nextLine().trim());
            System.out.println(consoleApp.getMedicineService().findById(id));
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());

        } catch (NumberFormatException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
        return new MedicineMenu();
    }

    @Override
    public Menu updateEntity(ConsoleApp consoleApp) {
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
            System.out.println("Сущность обновлена с id: " + entity.getId());
        } catch (IndexOutOfBoundsException e) {
            System.out.println(
                    "Ошибка: Введите следующие поля: id, trade_name, inn, dosage, form, producer_id");
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
        return new MedicineMenu();
    }

    @Override
    public Menu deleteEntity(ConsoleApp consoleApp) {
        try {
            System.out.println("Введите id препарата: ");
            int id = Integer.parseInt(consoleApp.getScanner().nextLine().trim());
            consoleApp.getMedicineService().delete(id);
            System.out.println("Сущность с id '" + id + "' удалена!");
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
        return new MedicineMenu();
    }
}

