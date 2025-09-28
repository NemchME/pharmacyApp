package org.example;

import org.example.menu.Menu;
import org.example.menu.impl.MainMenu;
import org.example.model.*;
import org.example.repository.impl.inmemory.*;
import org.example.service.*;

import java.util.Scanner;

public class ConsoleApp {

    private final AvailabilityOfMedicineService availabilityOfMedicineService;
    private final MedicineService medicineService;
    private final OrderService orderService;
    private final PharmacyService pharmacyService;
    private final ProducerService producerService;
    private final UserService userService;
    private final Scanner scanner = new Scanner(System.in);
    private Menu menu = new MainMenu();

    public ConsoleApp() {
        this.pharmacyService = new PharmacyService(new InMemoryPharmacyRepository());
        this.producerService = new ProducerService(new InMemoryProducerRepository());
        this.userService = new UserService(new InMemoryUserRepository());
        this.medicineService = new MedicineService(new InMemoryMedicineRepository(), this.producerService);
        this.availabilityOfMedicineService = new AvailabilityOfMedicineService(
                new InMemoryAvailabilityRepository(), this.pharmacyService, this.medicineService);
        this.orderService = new OrderService(
                new InMemoryOrderRepository(), this.userService, this.medicineService, this.pharmacyService);

    }

    public void run() {
        inputTestData();
        while (true) {
        menu = menu.execute(this);
        }
    }

    public AvailabilityOfMedicineService getAvailabilityOfMedicineService() {
        return availabilityOfMedicineService;
    }

    public MedicineService getMedicineService() {
        return medicineService;
    }

    public OrderService getOrderService() {
        return orderService;
    }

    public PharmacyService getPharmacyService() {
        return pharmacyService;
    }

    public ProducerService getProducerService() {
        return producerService;
    }

    public UserService getUserService() {
        return userService;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public Menu getMenu() {
        return menu;
    }


    private void inputTestData() {
        Pharmacy pharmacy = new Pharmacy(
                "Аптека", "ул. Пушкина", "220-98-29", "9-12",
                "Поверните направо");
        Producer producer = new Producer("Производитель №1", "Россия");
        User user = new User("Пользователь", "Пароль", "mail@mail.ru", "юзер");
        Medicine medicine = new Medicine("лекарство", "инн", "20 мг", "форма", 0);
        AvailabilityOfMedicine availabilityOfMedicine = new AvailabilityOfMedicine(0,0,100.0f,5);
        Order order = new Order(0,0,0,50,"Создан");
        pharmacyService.save(pharmacy);
        producerService.save(producer);
        userService.save(user);
        medicineService.save(medicine);
        availabilityOfMedicineService.save(availabilityOfMedicine);
        orderService.save(order);
    }

//    private void chooseVersion() {
//        while (true) {
//            System.out.println("""
//                                Выберите реализацию БД:
//                                1. In-memory
//                                2. jdbc
//                                """);
//
//        }
//    }

    public static void main(String[] args) {
        new ConsoleApp().run();
    }
}
