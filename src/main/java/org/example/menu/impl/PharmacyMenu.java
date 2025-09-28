package org.example.menu.impl;

import org.example.ConsoleApp;
import org.example.exception.EntityNotFoundException;
import org.example.menu.CrudMenu;
import org.example.menu.Menu;
import org.example.model.Pharmacy;



public class PharmacyMenu implements Menu, CrudMenu {

    @Override
    public Menu execute(ConsoleApp consoleApp) {
        while (true) {
            try {
                System.out.println("""
                        Выберите действие:
                        1. Получить все аптеки
                        2. Сохранить аптеку
                        3. Найти аптеку
                        4. Обновить аптеку
                        5. Удалить аптеку
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
        System.out.println(consoleApp.getPharmacyService().findAll());
        return new PharmacyMenu();
    }

    @Override
    public Menu createEntity(ConsoleApp consoleApp) {
        try {
            System.out.println("""
                    Введите данные об аптеке через запятую:
                    [name, address, phone, working_hours, way_from_center].
                    Пример ввода: Аптека, Университетская пл. 1, +88005553535, 9-18, С ул. Ленина езжайте в сторону ВГУ.
                    """);
            String[] entityArgs = consoleApp.getScanner().nextLine().split(",");

            if (entityArgs.length != 5) {
                throw new IllegalArgumentException(
                        "Введите следующие поля: name, address, phone, working_hours, way_from_center");
            }
            Pharmacy entity = new Pharmacy(
                    entityArgs[0].trim(),
                    entityArgs[1].trim(),
                    entityArgs[2].trim(),
                    entityArgs[3].trim(),
                    entityArgs[4].trim()
            );
            consoleApp.getPharmacyService().save(entity);
            System.out.println("Сущность сохранена с id: " + entity.getId());
        } catch (IndexOutOfBoundsException e) {
            System.out.println(
                    "Ошибка: Введите следующие поля: name, address, phone, working_hours, way_from_center");
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
        return new PharmacyMenu();
    }

    @Override
    public Menu readEntity(ConsoleApp consoleApp) {
        try {
            System.out.println("Введите id аптеки: ");
            int id = Integer.parseInt(consoleApp.getScanner().nextLine().trim());
            System.out.println(consoleApp.getPharmacyService().findById(id));
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());

        } catch (NumberFormatException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
        return new PharmacyMenu();
    }

    @Override
    public Menu updateEntity(ConsoleApp consoleApp) {
        try {
            System.out.println("""
                    Введите обновленные данные через запятую:
                    [id, name, address, phone, working_hours, way_from_center].
                    Пример ввода: 1, Аптека, Университетская пл. 1, +88005553535, 9-18, С ул. Ленина езжайте в сторону ВГУ.
                    """);
            String[] entityArgs = consoleApp.getScanner().nextLine().split(",");
            if (entityArgs.length != 6) {
                throw new IllegalArgumentException(
                        "Ошибка: Введите следующие поля: id, name, address, phone, working_hours, way_from_center");
            }
            Pharmacy entity = new Pharmacy(
                    Integer.parseInt(entityArgs[0].trim()),
                    entityArgs[1].trim(),
                    entityArgs[2].trim(),
                    entityArgs[3].trim(),
                    entityArgs[4].trim(),
                    entityArgs[5].trim()
            );
            consoleApp.getPharmacyService().update(entity);
            System.out.println("Сущность обновлена с id: " + entity.getId());
        } catch (IndexOutOfBoundsException e) {
            System.out.println(
                    "Ошибка: Введите следующие поля: name, address, phone, working_hours, way_from_center");
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
        return new PharmacyMenu();
    }

    @Override
    public Menu deleteEntity(ConsoleApp consoleApp) {
        try {
            System.out.println("Введите id аптеки: ");
            int id = Integer.parseInt(consoleApp.getScanner().nextLine().trim());
            consoleApp.getPharmacyService().delete(id);
            System.out.println("Сущность с id '" + id + "' удалена!");
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
        return new PharmacyMenu();
    }
}
