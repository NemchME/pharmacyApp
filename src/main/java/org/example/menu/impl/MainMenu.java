package org.example.menu.impl;

import org.example.ConsoleApp;
import org.example.command.Command;
import org.example.command.mainMenu.ExitCommand;
import org.example.command.mainMenu.*;
import org.example.menu.Menu;
import java.util.LinkedHashMap;
import java.util.Map;

public class MainMenu implements Menu {

    private final Map<String, Command> commandsMap = new LinkedHashMap<>();
    private final Map<String, String> descriptionsMap = new LinkedHashMap<>();

    public MainMenu() {
        commandsMap.put("1", new AvailabiltyOfMedicineCommand());
        commandsMap.put("2", new MedicineCommand());
        commandsMap.put("3", new OrderCommand());
        commandsMap.put("4", new PharmacyCommand());
        commandsMap.put("5", new ProducerCommand());
        commandsMap.put("6", new UserCommand());
        commandsMap.put("7", new ExitCommand());
        descriptionsMap.put("1", "Наличие препарата (AvailabilityOfMedicine)");
        descriptionsMap.put("2", "Препарат (Medicine)");
        descriptionsMap.put("3", "Заказ (Order)");
        descriptionsMap.put("4", "Аптека (Pharmacy)");
        descriptionsMap.put("5", "Производитель (Producer)");
        descriptionsMap.put("6", "Пользователь (User)");
        descriptionsMap.put("7", "Выход из программы");
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

