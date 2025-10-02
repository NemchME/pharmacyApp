package org.example.command.user;

import org.example.ConsoleApp;
import org.example.command.Command;
import org.example.command.validator.Validator;
import org.example.menu.Menu;
import org.example.menu.impl.UserMenu;
import org.example.model.User;

public class CreateCommand implements Command {

    @Override
    public Menu execute(ConsoleApp consoleApp) {
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
                    Validator.requireNotBlank(entityArgs[0], "Введите username"),
                    Validator.requireNotBlank(entityArgs[1], "Введите password_hash"),
                    Validator.requireNotBlank(entityArgs[2], "Введите email"),
                    Validator.requireNotBlank(entityArgs[3], "Введите role")
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
}
