package org.example.command.user;

import org.example.ConsoleApp;
import org.example.command.Command;
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
                    entityArgs[0].trim(),
                    entityArgs[1].trim(),
                    entityArgs[2].trim(),
                    entityArgs[3].trim()
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
