package org.example.command.producer;

import org.example.ConsoleApp;
import org.example.command.Command;
import org.example.exception.EntityNotFoundException;
import org.example.menu.Menu;
import org.example.menu.impl.ProducerMenu;

public class ReadCommand implements Command {

    @Override
    public Menu execute(ConsoleApp consoleApp) {
        try {
            System.out.println("Введите id производителя: ");
            int id = Integer.parseInt(consoleApp.getScanner().nextLine().trim());
            System.out.println(consoleApp.getProducerService().findById(id));
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());

        } catch (NumberFormatException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
        return new ProducerMenu();
    }
}
