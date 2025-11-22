package org.example.command.user;

import org.example.ConsoleApp;
import org.example.command.Command;
import org.example.menu.Menu;
import org.example.menu.impl.UserMenu;
import org.example.model.User;

public class UpdateCommand implements Command {

    @Override
    public Menu execute(ConsoleApp consoleApp) {
        try {
            System.out.println("""
                    Введите обновленные данные через запятую:
                    [id, username, password_hash, email, role].
                    Пример ввода: 1, Пользователь, 1234@, test@test.com, админ.
                    """);
            String[] entityArgs = consoleApp.getScanner().nextLine().split(",");
            if (entityArgs.length != 5) {
                throw new IllegalArgumentException(
                        "Ошибка: Введите следующие поля: id, username, password_hash, email, role");
            }
            User entity = new User(
                    Integer.parseInt(entityArgs[0].trim()),
                    entityArgs[1].trim(),
                    entityArgs[2].trim(),
                    entityArgs[3].trim(),
                    entityArgs[4].trim()
            );
            consoleApp.getUserService().update(entity);
            System.out.println("Сущность обновлена");
        } catch (IndexOutOfBoundsException e) {
            System.out.println(
                    "Ошибка: Введите следующие поля: username, password_hash, email, role");
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
        return new UserMenu();
    }
}
