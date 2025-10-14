package org.example.command.pharmacy;

import org.example.ConsoleApp;
import org.example.command.Command;
import org.example.menu.Menu;
import org.example.menu.impl.PharmacyMenu;
import org.example.model.Pharmacy;

public class UpdateCommand implements Command {

    @Override
    public Menu execute(ConsoleApp consoleApp) {
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
            System.out.println("Сущность обновлена");
        } catch (IndexOutOfBoundsException e) {
            System.out.println(
                    "Ошибка: Введите следующие поля: name, address, phone, working_hours, way_from_center");
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
        return new PharmacyMenu();
    }
}
