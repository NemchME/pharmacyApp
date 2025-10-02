package org.example.menu.impl;

import org.example.ConsoleApp;
import org.example.command.Command;
import org.example.command.mainMenu.MainMenuCommand;
import org.example.command.availabilityOfMedicine.*;
import org.example.menu.Menu;
;
import java.util.LinkedHashMap;
import java.util.Map;

public class AvailabilityOfMedicineMenu implements Menu {

    private final Map<String, Command> commandsMap = new LinkedHashMap<>();
    private final Map<String, String> descriptionsMap = new LinkedHashMap<>();

    public AvailabilityOfMedicineMenu() {
        commandsMap.put("1", new FindAllCommand());
        commandsMap.put("2", new CreateCommand());
        commandsMap.put("3", new ReadCommand());
        commandsMap.put("4", new UpdateCommand());
        commandsMap.put("5", new DeleteCommand());
        commandsMap.put("6", new MainMenuCommand());
        descriptionsMap.put("1", "Получить все данные о наличии препарата");
        descriptionsMap.put("2", "Сохранить данные о наличии препарата");
        descriptionsMap.put("3", "Найти данные о наличии препарата");
        descriptionsMap.put("4", "Обновить данные о наличии препарата");
        descriptionsMap.put("5", "Удалить данные о наличии препарата");
        descriptionsMap.put("6", "Перейти в главное меню");

    }

    @Override
    public Menu show(ConsoleApp consoleApp) {
        while (true) {
            try {
                printMenu();
                String choice = consoleApp.getScanner().nextLine().trim();

                Command command = commandsMap.get(choice);

                if (command == null) {
                    throw new IllegalArgumentException("Неверный ввод! Введите число от 1 до " + commandsMap.size() +
                            ".");
                }
            return command.execute(consoleApp);
            } catch (Exception e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }
    }

    public void printMenu() {
        System.out.println("Выберите действие:");
        descriptionsMap.forEach((key, value) ->
                System.out.println(key + ". " + value));
    }
}
