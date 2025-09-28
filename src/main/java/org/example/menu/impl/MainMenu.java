package org.example.menu.impl;

import org.example.ConsoleApp;
import org.example.menu.Menu;

public class MainMenu implements Menu {

    @Override
    public Menu execute(ConsoleApp consoleApp) {
        while (true) {
            System.out.println("""
                     Выберите действие:
                     1. Наличие препарата (AvailabilityOfMedicine)
                     2. Препарат (Medicine)
                     3. Заказ (Order)
                     4. Аптека (Pharmacy)
                     5. Производитель (Producer)
                     6. Пользователь (user)
                     7. Выход из программы
                    """);
            String choice = consoleApp.getScanner().nextLine();

            switch (choice) {
                case "1" -> {
                    return new AvailabilityOfMedicineMenu();
                }
                case "2" -> {
                    return new MedicineMenu();
                }
                case "3" -> {
                    return new OrderMenu();
                }
                case "4" -> {
                    return new PharmacyMenu();
                }
                case "5" -> {
                    return new ProducerMenu();
                }
                case "6" -> {
                    return new UserMenu();
                }
                case "7" -> {
                    System.out.println("Выход...");
                    System.exit(0);
                }
                default -> System.out.println("Неверный ввод! Введите число от 1 до 7.");
            };
        }
    }
}
