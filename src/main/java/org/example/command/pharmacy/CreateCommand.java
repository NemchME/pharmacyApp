package org.example.command.pharmacy;

import org.example.ConsoleApp;
import org.example.command.Command;
import org.example.menu.Menu;
import org.example.menu.impl.PharmacyMenu;
import org.example.model.Pharmacy;

public class CreateCommand implements Command {

    @Override
    public Menu execute(ConsoleApp consoleApp) {
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
}
