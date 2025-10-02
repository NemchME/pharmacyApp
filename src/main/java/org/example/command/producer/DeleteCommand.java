package org.example.command.producer;

import org.example.ConsoleApp;
import org.example.command.Command;
import org.example.menu.Menu;
import org.example.menu.impl.ProducerMenu;

public class DeleteCommand implements Command {

    @Override
    public Menu execute(ConsoleApp consoleApp) {
        try {
            System.out.println("Введите id производителя: ");
            int id = Integer.parseInt(consoleApp.getScanner().nextLine().trim());
            consoleApp.getProducerService().delete(id);
            System.out.println("Сущность с id '" + id + "' удалена!");
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
        return new ProducerMenu();
    }
}
