package org.example.command.user;

import org.example.ConsoleApp;
import org.example.command.Command;
import org.example.exception.EntityNotFoundException;
import org.example.menu.Menu;
import org.example.menu.impl.UserMenu;

public class ReadCommand implements Command {

    @Override
    public Menu execute(ConsoleApp consoleApp) {
        try {
            System.out.println("Введите id пользователя: ");
            int id = Integer.parseInt(consoleApp.getScanner().nextLine().trim());
            System.out.println(consoleApp.getUserService().findById(id));
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());

        } catch (NumberFormatException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
        return new UserMenu();
    }
}
