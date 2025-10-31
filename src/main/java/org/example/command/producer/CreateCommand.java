package org.example.command.producer;

import org.example.ConsoleApp;
import org.example.command.Command;
import org.example.command.validator.Validator;
import org.example.menu.Menu;
import org.example.menu.impl.ProducerMenu;
import org.example.model.Producer;

public class CreateCommand implements Command {

    @Override
    public Menu execute(ConsoleApp consoleApp) {
        try {
            System.out.println("""
                    Введите данные о производителе через запятую:
                    [name, country].
                    Пример ввода: Фарма, Германия
                    """);
            String[] entityArgs = consoleApp.getScanner().nextLine().split(",");

            if (entityArgs.length != 2) {
                throw new IllegalArgumentException(
                        "Ошибка: Введите следующие поля: name, country");
            }
            Producer entity = new Producer(
                    Validator.requireNotBlank(entityArgs[0], "Введите name"),
                    Validator.requireNotBlank(entityArgs[1], "Введите country")
            );
            consoleApp.getProducerService().save(entity);
            System.out.println("Сущность сохранена");
        } catch (IndexOutOfBoundsException e) {
            System.out.println(
                    "Ошибка: Введите следующие поля: name, country");
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
        return new ProducerMenu();
    }
}
