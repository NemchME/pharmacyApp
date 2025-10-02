package org.example.command.producer;

import org.example.ConsoleApp;
import org.example.command.Command;
import org.example.menu.Menu;
import org.example.menu.impl.ProducerMenu;
import org.example.model.Producer;

public class UpdateCommand implements Command {

    @Override
    public Menu execute(ConsoleApp consoleApp) {
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
}
