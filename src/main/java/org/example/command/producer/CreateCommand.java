package org.example.command.producer;

import org.example.ConsoleApp;
import org.example.command.Command;
import org.example.menu.Menu;
import org.example.menu.impl.ProducerMenu;
import org.example.model.Producer;

public class CreateCommand implements Command {

    @Override
    public Menu execute(ConsoleApp consoleApp) {
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
}
