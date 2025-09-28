package org.example.menu.impl;

import org.example.ConsoleApp;
import org.example.exception.EntityNotFoundException;
import org.example.menu.CrudMenu;
import org.example.menu.Menu;
import org.example.model.AvailabilityOfMedicine;

public class AvailabilityOfMedicineMenu implements Menu, CrudMenu {
    
    @Override
    public Menu execute(ConsoleApp consoleApp) {
        while (true) {
            try {
                System.out.println("""
                    Выберите действие:
                    1. Получить все данные о наличии препарата
                    2. Сохранить данные о наличии препарата
                    3. Найти данные о наличии препарата
                    4. Обновить данные о наличии препарата
                    5. Удалить данные о наличии препарата
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
        System.out.println(consoleApp.getAvailabilityOfMedicineService().findAll());
        return new AvailabilityOfMedicineMenu();
    }

    @Override
    public Menu createEntity(ConsoleApp consoleApp) {
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
                    Integer.parseInt(entityArgs[0].trim()),
                    Integer.parseInt(entityArgs[1].trim()),
                    Float.parseFloat(entityArgs[2].trim()),
                    Integer.parseInt(entityArgs[3].trim())
            );
            consoleApp.getAvailabilityOfMedicineService().save(entity);
            System.out.println("Сущность сохранена с id: " + entity.getId());
        } catch (IndexOutOfBoundsException e) {
            System.out.println(
                    "Ошибка: Введите следующие поля: pharmacy_id, medicine_id, price, quantily");
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
        return new AvailabilityOfMedicineMenu();
    }

    @Override
    public Menu readEntity(ConsoleApp consoleApp) {
        try {
            System.out.println("Введите id данных о наличии препарата: ");
            int id = Integer.parseInt(consoleApp.getScanner().nextLine().trim());
            System.out.println(consoleApp.getAvailabilityOfMedicineService().findById(id));
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());

        } catch (NumberFormatException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
        return new AvailabilityOfMedicineMenu();
    }

    @Override
    public Menu updateEntity(ConsoleApp consoleApp) {
        try {
            System.out.println("""
                    Введите обновленные данные через запятую:
                    [id, pharmacy_id, medicine_id, price, quantily].
                    Пример ввода: 0, 0, 0, 10.0, 2
                    """);
            String[] entityArgs = consoleApp.getScanner().nextLine().split(",");
            if (entityArgs.length != 5) {
                throw new IllegalArgumentException(
                        "Ошибка: Введите следующие поля: id, pharmacy_id, medicine_id, price, quantily");
            }
            AvailabilityOfMedicine entity = new AvailabilityOfMedicine(
                    Integer.parseInt(entityArgs[0].trim()),
                    Integer.parseInt(entityArgs[1].trim()),
                    Integer.parseInt(entityArgs[2].trim()),
                    Float.parseFloat(entityArgs[3].trim()),
                    Integer.parseInt(entityArgs[4].trim())
            );
            consoleApp.getAvailabilityOfMedicineService().update(entity);
            System.out.println("Сущность обновлена с id: " + entity.getId());
        } catch (IndexOutOfBoundsException e) {
            System.out.println(
                    "Ошибка: Введите следующие поля: id, pharmacy_id, medicine_id, price, quantily");
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
        return new AvailabilityOfMedicineMenu();
    }

    @Override
    public Menu deleteEntity(ConsoleApp consoleApp) {
        try {
            System.out.println("Введите id данных о наличии препарата: ");
            int id = Integer.parseInt(consoleApp.getScanner().nextLine().trim());
            consoleApp.getAvailabilityOfMedicineService().delete(id);
            System.out.println("Сущность с id '" + id + "' удалена!");
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
        return new AvailabilityOfMedicineMenu();
    }
}
